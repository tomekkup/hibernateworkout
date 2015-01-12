/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tomekkup.hibernateworkout.dao;

import tomekkup.hibernateworkout.model.UserAccount;

public interface UserAccountDao extends Dao {
    
    void insertNew(Integer id, String name, String password);
    
    UserAccount get(Integer id);
    
    UserAccount load(Integer id);
    
    void update(Integer id, String newUsername);
    
    void updateRollbacked(Integer id, String newUsername);
    
    void saveOrUpdate(UserAccount obj);
    
    void evict(UserAccount obj);
}
