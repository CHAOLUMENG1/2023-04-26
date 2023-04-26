/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PropertyServices;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import PropertyBeans.KeyakuBean;
import PropertyBeans.HouseBean;
import PropertyBeans.HouseJoinBean;
import PropertyConns.JdbcConn;

/**
 * 部屋一覧
 *
 * @author CHAOY
 */
public class HouseService {

    public List<HouseBean> listInfo() throws SQLException {
        //JDBC調用
        PropertyConns.JdbcConn jc = new PropertyConns.JdbcConn();
        List<HouseBean> list = new ArrayList<HouseBean>();

        try {
            jc.getDbcom();

            String sql = "SELECT * from House order by property_id";

            try {
                ResultSet resultSet = jc.tt(sql);
                while (resultSet.next()) {
                    HouseBean HouseBean = new HouseBean();
                    HouseBean.setPropertyId((resultSet.getInt("property_id")));
                    HouseBean.setRoomId(resultSet.getInt("room_id"));
                    HouseBean.setRoomNum(resultSet.getString("room_num"));
                    HouseBean.setPattern(resultSet.getString("pattern"));
                    HouseBean.setArea(resultSet.getString("area"));
                    list.add(HouseBean);

                    jc.closeDbcom();
                }

            } catch (SQLException ex) {
                Logger.getLogger(HouseService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HouseService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    /**
     * 情報を挿入
     *
     * @param
     * @param
     * @throws SQLException
     */
    public void HouseInsert(String property_id, String room_id, String room_num, String pattern, String area) throws SQLException {
        LocalDateTime date = LocalDateTime.now();
        //JDBC調用
        JdbcConn jc = new JdbcConn();
        LocalDateTime createDateTime = LocalDateTime.now();
        LocalDateTime updateDateTime = createDateTime.plusYears(1);
        try {
            jc.getDbcom();

            String sql = "insert into House( property_id , room_id , room_num , pattern , area,create_date,modify_date,del_flag) "
                    + "values(" + property_id + ",'" + room_id + "','" + room_num + "','" + pattern + "','" + area + "','" + createDateTime
                    + "','" + updateDateTime + "'," + 0 + ")";
            System.out.print(sql);
            jc.cud(sql);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HouseService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 情報更新
     *
     * @param bb
     * @throws SQLException
     */
    public void HouseUpdate(HouseBean bb) throws SQLException {
        //JDBC調用
        JdbcConn jc = new JdbcConn();

        try {
            jc.getDbcom();

            StringBuffer sb = new StringBuffer();
            sb.append("update House set ");
            sb.append("room_id = '" + bb.getRoomId() + "',");
            sb.append("room_num = '" + bb.getRoomNum() + "',");
            sb.append("pattern = '" + bb.getPattern() + "',");
            sb.append("area = " + bb.getArea() + "");
            sb.append(" where ");
            sb.append(" property_id = " + bb.getPropertyId());
            sb.append(";");
            System.out.println(sb.toString());
            jc.cud(sb.toString());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HouseService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 情報削除
     *
     * @param
     * @throws SQLException
     */
    public void HouseDelete(HouseBean bb) throws SQLException {
        //JDBC調用
        JdbcConn jc = new JdbcConn();
        try {
            jc.getDbcom();
            StringBuffer sb = new StringBuffer();
            sb.append("delete from House ");
            sb.append(" where ");
            sb.append(" property_id = '" + bb.getPropertyId() + "'");
            sb.append(";");
            System.out.println(sb.toString());
            jc.cud(sb.toString());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HouseService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
