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
import PropertyBeans.BukenBean;
import PropertyBeans.KeyakuBean;
import PropertyBeans.KeyakuJoinBean;
import PropertyConns.JdbcConn;

/**
 * 契約一覧
 *
 * @author CHAOY
 */
public class KeyakuService {

    public List<KeyakuBean> listInfo() {
        //JDBC調用
        PropertyConns.JdbcConn jc = new PropertyConns.JdbcConn();
        List<KeyakuBean> list = new ArrayList<KeyakuBean>();

        try {
            jc.getDbcom();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KeyakuService.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "select * from SaleContract order by contract_id";

        ResultSet resultSet;
        try {
            resultSet = jc.tt(sql);
            while (resultSet.next()) {
                KeyakuBean BuyerBean = new KeyakuBean();
                BuyerBean.setContractId((resultSet.getInt("contract_id")));
                BuyerBean.setPropertyId(resultSet.getInt("property_id"));
                BuyerBean.setBuyerName(resultSet.getString("buyer_name"));
                BuyerBean.setContractDate(resultSet.getString("contract_date"));
                BuyerBean.setPrice(resultSet.getInt("price"));
                list.add(BuyerBean);
            }
            jc.closeDbcom();
        } catch (SQLException ex) {
            Logger.getLogger(KeyakuService.class.getName()).log(Level.SEVERE, null, ex);
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
    public void BuyerInsert(String contract_id, String property_id, String buyer_name, String contract_date, String price) throws SQLException {
        LocalDateTime date = LocalDateTime.now();
        //JDBC調用
        JdbcConn jc = new JdbcConn();
        LocalDateTime createDateTime = LocalDateTime.now();
        LocalDateTime updateDateTime = createDateTime.plusYears(1);
        try {
            jc.getDbcom();

            String sql = "insert into SaleContract( contract_id , property_id , buyer_name , contract_date , price,create_date,modify_date,del_flag) "
                    + "values(" + contract_id + ",'" + property_id + "','" + buyer_name + "','" + contract_date + "','" + price + "','" + createDateTime
                    + "','" + updateDateTime + "'," + 0 + ")";
            System.out.print(sql);
            jc.cud(sql);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KeyakuService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 情報更新
     *
     * @param bb
     * @throws SQLException
     */
    public void BuyerUpdate(KeyakuBean bb) throws SQLException {
        //JDBC調用
        JdbcConn jc = new JdbcConn();

        try {
            jc.getDbcom();

            StringBuffer sb = new StringBuffer();
            sb.append("update SaleContract set ");
            sb.append("property_id = '" + bb.getPropertyId() + "',");
            sb.append("buyer_name = '" + bb.getBuyerName() + "',");
            sb.append("contract_date = '" + bb.getContractDate() + "',");
            sb.append("price = " + bb.getPrice() + "");
            sb.append(" where ");
            sb.append(" contract_id = " + bb.getContractId());
            sb.append(";");
            System.out.println(sb.toString());
            jc.cud(sb.toString());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KeyakuService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 情報削除
     *
     * @param
     * @throws SQLException
     */
    public void BuyerDelete(KeyakuBean bb) throws SQLException {
        //JDBC調用
        JdbcConn jc = new JdbcConn();
        try {
            jc.getDbcom();
            StringBuffer sb = new StringBuffer();
            sb.append("delete from SaleContract ");
            sb.append(" where ");
            sb.append(" contract_id = '" + bb.getContractId() + "'");
            sb.append(";");
            System.out.println(sb.toString());
            jc.cud(sb.toString());

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KeyakuService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
