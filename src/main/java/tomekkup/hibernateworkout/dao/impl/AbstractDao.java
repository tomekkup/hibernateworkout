package tomekkup.hibernateworkout.dao.impl;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.Query;
import org.hibernate.stat.Statistics;
import tomekkup.hibernateworkout.dao.Dao;

abstract class AbstractDao implements Dao {
    
    protected Logger logger = Logger.getLogger(this.getClass());
    private SessionFactory sessionFactory;

    @Autowired(required=true)
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    protected final Session getSession() {
        return sessionFactory.getCurrentSession();//SessionFactoryUtils..getSession(sessionFactory, true);
    }
    
    @Override
    public void evict(Object obj) {
        getSession().evict(obj);
    }
    
    @Override
    public Serializable save(Object obj) {
        return getSession().save(obj);
    }
    
    @Override
    public void saveOrUpdate(Object obj) {
        getSession().saveOrUpdate(obj);
    }
    
    @Override
    public void persist(Object obj) {
        getSession().persist(obj);
    }
    
    @Override
    public void printCacheStats() {
        Statistics stats = sessionFactory.getStatistics();
        stats.setStatisticsEnabled(true);
        
        logger.debug(String.format("CACHE STATS::: Fetch=%d|Hit=%d|Miss=%d|Put=%d", stats.getEntityFetchCount(), stats.getSecondLevelCacheHitCount(), 
                                    stats.getSecondLevelCacheMissCount(), stats.getSecondLevelCachePutCount()));
    }
    
    @Override
    public List<Object> createCachedQuery(String queryStr, String cacheRegion) {
        Query query = getSession().createQuery(queryStr);
        query = query.setCacheable(true);
        query.setCacheMode(CacheMode.NORMAL);
        query.setCacheRegion(cacheRegion);
        return query.list();
    }
    
    @Override
    public <T extends Object> T merge(T obj) {
        return (T) getSession().merge(obj);
    }
}