/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tomekkup.hibernateworkout.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;

abstract class AbstractDao {
    
    protected Logger logger = Logger.getLogger(this.getClass());
    private SessionFactory sessionFactory;

    @Autowired(required=true)
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    protected final Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    protected void evict(Object obj) {
        getSession().evict(obj);
    }
}
