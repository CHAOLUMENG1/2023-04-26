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
import PropertyBeans.KeyakuJoinBean;

/**
 * 契約詳細一覧
 *
 * @author CHAOY
 */
public class KeyakuYichiranService {

    public List<KeyakuJoinBean> listInfo() {
        //JDBC調用
        PropertyConns.JdbcConn jc = new PropertyConns.JdbcConn();
        List<KeyakuJoinBean> list = new ArrayList<KeyakuJoinBean>();

        try {
            jc.getDbcom();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KeyakuYichiranService.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "SELECT s.contract_id,s.property_id,p.property_name,p.address,p.property_type,s.buyer_name, s.contract_date,s.price\n"
                + "FROM SaleContract s\n"
                + "INNER JOIN Property p\n"
                + "ON s.property_id = p.property_id";

        ResultSet resultSet;
        try {
            resultSet = jc.tt(sql);
            while (resultSet.next()) {
                KeyakuJoinBean joinBean1 = new KeyakuJoinBean();
                joinBean1.setContractId((resultSet.getInt("contract_id")));
                joinBean1.setPropertyId(resultSet.getInt("property_id"));
                joinBean1.setPropertyName(resultSet.getString("property_name"));
                joinBean1.setAddress(resultSet.getString("address"));
                joinBean1.setPropertyType(resultSet.getString("property_type"));
                joinBean1.setBuyerName(resultSet.getString("buyer_name"));
                joinBean1.setContractDate(resultSet.getString("contract_date"));
                joinBean1.setPrice(resultSet.getInt("price"));
                list.add(joinBean1);
            }
            jc.closeDbcom();
        } catch (SQLException ex) {
            Logger.getLogger(KeyakuYichiranService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
}
