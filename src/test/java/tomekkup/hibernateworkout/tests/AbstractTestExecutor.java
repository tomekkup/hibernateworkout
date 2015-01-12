package tomekkup.hibernateworkout.tests;

import org.apache.log4j.Logger;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import tomekkup.hibernateworkout.tests.impl.utils.TableContentLogger;

/**
 *
 * @author tkuprowski
 */
public abstract class AbstractTestExecutor extends AbstractTransactionalJUnit4SpringContextTests {
    private final Logger logger = Logger.getLogger(this.getClass());
    
    protected void info(String msg) {
        logger.info(msg);
    }
    
    protected void printTableContent(String tableName) {
        TableContentLogger.list(tableName, jdbcTemplate);
    }
}
