/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tomekkup.hibernateworkout.dao;

import java.io.Serializable;

/**
 *
 * @author tkuprowski
 */
public interface Dao {
    
    void persist(Object obj);
    
    void evict(Object obj);
    
    Serializable save(Object obj);
}
