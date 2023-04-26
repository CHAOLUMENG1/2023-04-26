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
import PropertyBeans.HouseJoinBean;

/**
 * 部屋詳細一覧
 *
 * @author CHAOY
 */
public class HouseYichiranService {

    public List<HouseJoinBean> listInfo() throws SQLException {
        //JDBC調用
        PropertyConns.JdbcConn jc = new PropertyConns.JdbcConn();
        List<HouseJoinBean> list = new ArrayList<HouseJoinBean>();

        try {
            jc.getDbcom();

            String sql = "SELECT p.property_id, h.room_id,p.property_name,\n"
                    + "h.room_num, h.pattern, h.area\n"
                    + "FROM Property p\n"
                    + "LEFT JOIN House h ON p.property_id = h.property_id;";

            try {
                ResultSet resultSet = jc.tt(sql);
                while (resultSet.next()) {
                    HouseJoinBean joinBean2 = new HouseJoinBean();
                    joinBean2.setPropertyId((resultSet.getInt("property_id")));
                    joinBean2.setRoomId(resultSet.getInt("room_id"));
                    joinBean2.setPropertyName(resultSet.getString("property_name"));
                    joinBean2.setRoomNum(resultSet.getString("room_num"));
                    joinBean2.setPattern(resultSet.getString("pattern"));
                    joinBean2.setArea(resultSet.getString("area"));
                    list.add(joinBean2);

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
}
