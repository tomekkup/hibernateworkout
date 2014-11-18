package tomekkup.hibernateworkout.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tomekkup.hibernateworkout.dao.UserAccountDao;
import tomekkup.hibernateworkout.model.UserAccount;

@Repository("userAccountDao")
@Transactional(readOnly=true, propagation= Propagation.SUPPORTS)
public class UserAccountDaoImpl extends AbstractDao implements UserAccountDao {
    
    @Override
    public UserAccount load(Integer id) {
        return (UserAccount)getSession().load(UserAccount.class, id);
    }
    
    @Override
    public UserAccount get(Integer id) {
        return (UserAccount)getSession().get(UserAccount.class, id);
    }
    
    @Override
    @Transactional(readOnly=false, propagation= Propagation.REQUIRED)
    public void update(Integer id, String newUsername) {
        UserAccount ua = this.load(id);
        ua.setName(newUsername);
        getSession().update(ua);
    }
    
    @Override
    @Transactional(readOnly=false)
    public void updateRollbacked(Integer id, String newUsername) {
        UserAccount ua = this.load(id);
        ua.setName(newUsername);
        getSession().update(ua);
    }
    
    @Override
    @Transactional(readOnly=false, propagation= Propagation.REQUIRED)
    public void insertNew(Integer id, String name, String password) {
        UserAccount ua = new UserAccount();
        ua.setId(id);
        ua.setName(name);
        ua.setPassword(password);
        getSession().persist(ua);
    }
    
    @Override
    @Transactional(readOnly=false, propagation= Propagation.REQUIRED)
    public void saveOrUpdate(UserAccount obj) {
        getSession().saveOrUpdate(obj);
    }
    
    public void evict(UserAccount obj) {
        super.evict(obj);
    }
}
