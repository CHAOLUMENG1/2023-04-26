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
import PropertyConns.JdbcConn;

/**
 * 会社一覧
 *
 * @author CHAOY
 */
public class CompneyService {

    public static CompneyService compneyService;

    public List<CompneyBean> listInfo() throws SQLException {
        //JDBC調用
        JdbcConn jc = new JdbcConn();
        List<CompneyBean> list = new ArrayList<CompneyBean>();

        try {
            jc.getDbcom();

            String sql = "SELECT * FROM RealEstateCompany ;";

            try {
                ResultSet resultSet = jc.tt(sql);
                while (resultSet.next()) {
                    CompneyBean kaisyaBean = new CompneyBean();
                    kaisyaBean.setCompanyId((resultSet.getInt("company_id")));
                    kaisyaBean.setCompanyName(resultSet.getString("company_name"));
                    kaisyaBean.setAddress(resultSet.getString("address"));
                    kaisyaBean.setPhoneNumber(resultSet.getString("phone_number"));

                    list.add(kaisyaBean);

                    jc.closeDbcom();
                }

            } catch (SQLException ex) {
                Logger.getLogger(CompneyService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CompneyService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;

    }

}
