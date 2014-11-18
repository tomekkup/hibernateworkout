/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tomekkup.hibernateworkout.tests;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import tomekkup.hibernateworkout.tests.impl.utils.TableContentLogger;

/**
 *
 * @author tkuprowski
 */
public abstract class AbstractTestExecutor extends AbstractTransactionalJUnit4SpringContextTests implements InitializingBean {
    private final Logger logger = Logger.getLogger(this.getClass());
    
    protected void info(String msg) {
        logger.info(msg);
    }
    
    protected void printTableContent(String tableName) {
        TableContentLogger.list(tableName, jdbcTemplate);
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        // to byl skrypt ktory sobie elegancko wstawial rekordy i czyscil baze ale 
        // wyglada na to ze odpalaly sie tu kwerendy w trakcie trwania testu i rozwalaly wynik
        executeSqlScript("scripts/userAccount-data.sql", true);
    }
}
