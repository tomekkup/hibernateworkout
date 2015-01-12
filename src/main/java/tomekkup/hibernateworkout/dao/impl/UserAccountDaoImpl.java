package tomekkup.hibernateworkout.dao.impl;

import org.springframework.stereotype.Repository;
import tomekkup.hibernateworkout.dao.UserAccountDao;
import tomekkup.hibernateworkout.model.UserAccount;

@Repository("userAccountDao")
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
    public void update(Integer id, String newUsername) {
        UserAccount ua = this.load(id);
        ua.setName(newUsername);
        getSession().update(ua);
    }
    
    @Override
    public void insertNew(Integer id, String name, String password) {
        UserAccount ua = new UserAccount();
        ua.setId(id);
        ua.setName(name);
        ua.setPassword(password);
        getSession().persist(ua);
    }
}
