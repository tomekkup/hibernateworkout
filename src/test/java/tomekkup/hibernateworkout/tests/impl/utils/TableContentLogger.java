/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tomekkup.hibernateworkout.tests.impl.utils;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author tkuprowski
 */
public class TableContentLogger {
    
    private static final Logger logger = Logger.getLogger(TableContentLogger.class);
    
    public static void list(String tableName, JdbcTemplate jdbcTemplate) {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM " + tableName);
        logger.debug("-------------------------------------");
        for (Map<String, Object> item : list) {
            logger.debug(tableName + "> " + item.toString());
        }
        logger.debug("-------------------------------------");
    }
}
