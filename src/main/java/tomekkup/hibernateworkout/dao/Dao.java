package tomekkup.hibernateworkout.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Tomek Kuprowski
 */
public interface Dao {
    
    void persist(Object obj);
    
    void evict(Object obj);
    
    <T extends Object> T merge(T obj);
    
    Serializable save(Object obj);
    
    void saveOrUpdate(Object obj);
    
    void printCacheStats();
    
    List<Object> createCachedQuery(String queryStr, String cacheRegion);
}