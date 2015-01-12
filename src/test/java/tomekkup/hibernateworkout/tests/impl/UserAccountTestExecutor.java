package tomekkup.hibernateworkout.tests.impl;

import tomekkup.hibernateworkout.model.Permission;
import java.util.Date;
import java.util.List;
import tomekkup.hibernateworkout.tests.AbstractTestExecutor;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tomekkup.hibernateworkout.dao.UserAccountDao;
import tomekkup.hibernateworkout.dao.impl.UserAccountDaoImpl;
import tomekkup.hibernateworkout.mappers.UserAccountRowMapper;
import tomekkup.hibernateworkout.model.PermissionType;
import tomekkup.hibernateworkout.model.Role;
import tomekkup.hibernateworkout.model.RoleType;
import tomekkup.hibernateworkout.model.UserAccount;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // aby odpalaly sie w kolejnosci jak napisany kod, wazne bo najpierw wstawianie potem ladowanie
@org.springframework.test.context.ContextConfiguration("classpath:/context/applicationContext.xml")
@Transactional(readOnly=true, propagation= Propagation.SUPPORTS)
public class UserAccountTestExecutor extends AbstractTestExecutor {
    
    private UserAccountDao userAccountDao;
    
    @Autowired(required=true)
    public void setUserAccountDao(UserAccountDao userAccountDao) {
        this.userAccountDao = userAccountDao;
    }
    
    @Rule public final TestWatcher testLogger = new TestWatcher() {

        @Override
        protected void starting(Description description) {
            info("Starting test: " + description.getMethodName());
        }

        @Override
        protected void finished(Description description) {
            info("Finished test: " + description.getMethodName());
            printTableContent("USERACCOUNT");
            printTableContent("ROLES");
            printTableContent("ACCOUNT_DETAILS");
            printTableContent("PERMISSIONS");
        }
    };
    
    @Rollback(false)
    @Test public void testA_InsertNew() {
        userAccountDao.insertNew(1, "jan", "haslo1234");
        userAccountDao.insertNew(2, "zbigniew", "Zosia2007");
        userAccountDao.insertNew(3, "kazimierz", "warszawa");
    }
    
    /**
     * Pobieramy metodą 'get' obiekt nieistniejący w bazie
     */
    @Test public void testB_Get() {
        UserAccount user = userAccountDao.get(654321);
        Assert.assertNull("nie jest null ???", user);
    }
    
    @Test public void testC_Load() {
        UserAccount user = userAccountDao.load(1);
        Assert.assertNotNull("napewno jest w bazie", user);
    }

    @Rollback(false)
    @Test public void testD_Update() {
        userAccountDao.update(1, "mietek");
    }
    
    @Test public void testE_TransCommitted() {
        UserAccount modifiedUa = jdbcTemplate.queryForObject("SELECT * FROM USERACCOUNT WHERE ID = 1", new UserAccountRowMapper());
        Assert.assertEquals("nie zapisala sie transakcja", "mietek", modifiedUa.getName());
    }
    
    // test z PROPAGATION nie REQUIRED
    @Rollback(false)
    @Test public void testF_UpdateRollbacked() {
        userAccountDao.updateRollbacked(1, "kazik");
    }
    
    @Test public void testG_TransRollbacked() {
        UserAccount modifiedUa = jdbcTemplate.queryForObject("SELECT * FROM USERACCOUNT WHERE ID = 1", new UserAccountRowMapper());
        Assert.assertEquals("nie bylo rollback-u", "kazik", modifiedUa.getName());
    }
    
    @Rollback(false)
    @Test public void testH_saveOrUpdateOnNew() {
        UserAccount obj = new UserAccount(4, "wladyslaw", "pipilangsztrung");
        userAccountDao.saveOrUpdate(obj);
    }
    
    @Rollback(false)
    @Test public void testI_saveOrUpdateOnExisting() {
        info("Calling 'get'");
        UserAccount obj = userAccountDao.get(3);
        obj.setPassword("X7hj2!jg*");
        info("Calling 'saveOrUpdate'");
        userAccountDao.saveOrUpdate(obj);
    }
    
    /**
     * Pokazuje jak get odwola sie do bazy
     */
    @Test public void testJ_multipleGets() {
        for (int i = 0; i < 5; i++) {
            userAccountDao.get(1);
        }
    }
    
    /**
     * Prezentuje jak load(..) pobiera obiekty z sesji
     */
    @Test public void testK_multipleLoads() {
        for (int i = 0; i < 5; i++) {
            userAccountDao.load(1);
        }
    }
    
    /**
     * Prezentuje jak evict(..) usunie z sesji obiekt i get(..) pobierze go ponownie z bazy
     */
    @Test public void testM_Multi1() {
        info("Calling first get(..)");
        UserAccount obj = userAccountDao.get(1);
        info("Calling second get(..)");
        obj = userAccountDao.get(1);
        info("Calling evict(..)");
        userAccountDao.evict(obj);
        info("Calling third get(..)");
        obj = userAccountDao.get(1);
    }
    
    @Rollback(false)
    @Test public void testN_addCollection() {
        UserAccount ua = new UserAccount(44, "albercik", "katakumba");
        Role role = new Role(RoleType.AUDITOR, new Byte("1"), new Date());
        role.setId(10);

        ua.getRoles().add(role);

        userAccountDao.save(role);
        userAccountDao.saveOrUpdate(ua);
    }
    
    @Test public void testO_lazyLoading() {
        UserAccount ua = userAccountDao.get(44);
        info("Object retrieved from db. Now accessing collection item...");
        ua.getRoles().get(0);
    }
    
    @Test public void testP_addCollectionBidirectional() {
        UserAccount ua = new UserAccount(44, "albercik", "katakumba");
        Permission perm = new Permission(PermissionType.CREATION);
        perm.setAccount(ua);
        ua.getPermissions().add(perm);

        userAccountDao.saveOrUpdate(ua);
    }
    
    @Test public void testP_cachedQuery() {
        List<Object> list = userAccountDao.createCachedQuery("From UserAccount", "user_accounts");
        info("results stored in 2L cache. Now executing next time");
        list = userAccountDao.createCachedQuery("From UserAccount", "user_accounts");
        
        //TODO evict. dodanie czegos i odczytanie - lub sam evict ! :)
        
    }
}
