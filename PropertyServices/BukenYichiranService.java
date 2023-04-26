/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PropertyServices;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import PropertyBeans.BukenBean;
import PropertyConns.JdbcConn;

/**
 * 物件一覧
 *
 * @author CHAOY
 */
public class BukenYichiranService {

    public static BukenYichiranService bukenyichiranService;

    public List<BukenBean> listInfo() throws SQLException {
        //JDBC調用
        JdbcConn jc = new JdbcConn();
        List<BukenBean> list = new ArrayList<BukenBean>();

        try {
            jc.getDbcom();

            String sql = "SELECT * FROM property order by property_id;";

            try {
                ResultSet resultSet = jc.tt(sql);
                while (resultSet.next()) {
                    BukenBean bukenBeanlist = new BukenBean();
                    bukenBeanlist.setPropertyId((resultSet.getInt("property_id")));
                    bukenBeanlist.setPropertyName(resultSet.getString("property_name"));
                    bukenBeanlist.setAddress(resultSet.getString("address"));
                    bukenBeanlist.setPropertyType(resultSet.getString("property_type"));
                    bukenBeanlist.setPrice(resultSet.getInt("price"));
                    list.add(bukenBeanlist);

                    jc.closeDbcom();
                }

            } catch (SQLException ex) {
                Logger.getLogger(BukenYichiranService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BukenYichiranService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    /**
     * ユーザー新規
     *
     * @param name
     * @param email
     * @param password
     * @return
     */
    public void Sinki(String name, String email, String password) throws SQLException {
        LocalDateTime date = LocalDateTime.now();
        //JDBC調用
        JdbcConn jc = new JdbcConn();
        LocalDateTime createDateTime = LocalDateTime.now();
        LocalDateTime updateDateTime = createDateTime.plusYears(1);
        try {
            jc.getDbcom();

            String sql = "insert into Sinki(name , email , password,create_date,modify_date,del_flag) values('" + name + "','" + email + "','" + password + "','" + createDateTime
                    + "','" + updateDateTime + "'," + 0 + ")";

            jc.cud(sql);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BukenYichiranService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 物件情報を挿入
     *
     * @param
     * @param
     * @throws SQLException
     */
    public void BukenInsert(String property_id, String property_name, String address, String property_type, String price) throws SQLException {
        LocalDateTime date = LocalDateTime.now();
        //JDBC調用
        JdbcConn jc = new JdbcConn();
        LocalDateTime createDateTime = LocalDateTime.now();
        LocalDateTime updateDateTime = createDateTime.plusYears(1);
        try {
            jc.getDbcom();

            String sql = "insert into property( property_id , property_name , address , property_type , price,create_date,modify_date,del_flag) "
                    + "values(" + property_id + ",'" + property_name + "','" + address + "','" + property_type + "','" + price + "','" + createDateTime
                    + "','" + updateDateTime + "'," + 0 + ")";
            System.out.print(sql);
            jc.cud(sql);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BukenYichiranService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 情報更新
     *
     * @param bb
     * @throws SQLException
     */
    public void bukenUpdate(BukenBean bb) throws SQLException {
        //JDBC調用
        JdbcConn jc = new JdbcConn();

        try {
            jc.getDbcom();

            StringBuffer sb = new StringBuffer();
            sb.append("update property set ");
            sb.append("property_name = '" + bb.getPropertyName() + "',");
            sb.append("address = '" + bb.getAddress() + "',");
            sb.append("property_type = '" + bb.getPropertyType() + "',");
            sb.append("price = " + bb.getPrice() + "");
            sb.append(" where ");
            sb.append(" property_id = " + bb.getPropertyId());
            sb.append(";");
            System.out.println(sb.toString());
            jc.cud(sb.toString());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BukenYichiranService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 物件情報削除
     *
     * @param
     * @throws SQLException
     */
    public void bukenDelete(BukenBean bb) throws SQLException {
        //JDBC調用
        JdbcConn jc = new JdbcConn();
        try {
            jc.getDbcom();
            StringBuffer sb = new StringBuffer();
            sb.append("delete from property ");
            sb.append(" where ");
            sb.append(" property_id = '" + bb.getPropertyId() + "'");
            sb.append(";");
            System.out.println(sb.toString());
            jc.cud(sb.toString());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BukenYichiranService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
