/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tomekkup.hibernateworkout.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import tomekkup.hibernateworkout.model.UserAccount;

/**
 *
 * @author tkuprowski
 */
public class UserAccountRowMapper implements RowMapper<UserAccount> {

    @Override
    public UserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserAccount ua = new UserAccount();
        ua.setId(rs.getInt("ID"));
        ua.setName(rs.getString("NAME"));
        ua.setPassword(rs.getString("PASSWORD"));
        return ua;
    }
    
}
