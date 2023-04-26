/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PropertyServices;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import PropertyBeans.CompneyBean;
import PropertyBeans.SinkiBean;
import PropertyConns.JdbcConn;

/**
 * ユーザー新規
 *
 * @author CHAOY
 */
public class SinkiService {

    public boolean login(String name, String password) throws SQLException {
        //JDBC調用
        JdbcConn jc = new JdbcConn();

        try {
            jc.getDbcom();

            String sql = "select * from sinki where name = '"
                    + name + "' and password = '"
                    + password + "';";

            try {
                ResultSet resultSet = jc.tt(sql);
                if (resultSet.next()) {
                    return true;
                }

            } catch (SQLException ex) {
                Logger.getLogger(SinkiService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SinkiService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }
}
