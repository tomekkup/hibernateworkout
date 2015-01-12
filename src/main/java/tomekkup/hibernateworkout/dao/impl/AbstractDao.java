/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tomekkup.hibernateworkout.dao.impl;

import java.io.Serializable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tomekkup.hibernateworkout.dao.Dao;

abstract class AbstractDao implements Dao {
    
    protected Logger logger = Logger.getLogger(this.getClass());
    private SessionFactory sessionFactory;

    @Autowired(required=true)
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    protected final Session getSession() {
        return SessionFactoryUtils.getSession(sessionFactory, true);
    }
    
    @Override
    public void evict(Object obj) {
        getSession().evict(obj);
    }
    
    @Transactional(readOnly=false, propagation= Propagation.REQUIRED)
    @Override
    public Serializable save(Object obj) {
        return getSession().save(obj);
    }
    
    @Transactional(readOnly=false, propagation= Propagation.REQUIRED)
    @Override
    public void persist(Object obj) {
        getSession().persist(obj);
    }
}
