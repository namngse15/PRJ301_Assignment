/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.OrderStatus;

/**
 *
 * @author tenhik
 */
public class OrderStatusDAO extends BaseDAO<OrderStatus> {

    public OrderStatus getOrderStatusById(int statusId) {
        try {
            String sql = "select *\n"
                    + "from OrderStatus\n"
                    + "where id=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, statusId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                OrderStatus os = new OrderStatus();
                os.setId(rs.getInt("id"));
                os.setName(rs.getString("name"));
                return os;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<OrderStatus> getListStatusByRoleId(int roleId) {
        List<OrderStatus> listStatus = new ArrayList<>();
        String sql = "";
        try {
            if (roleId == 1) {
                sql = "select*\n"
                        + "from OrderStatus\n"
                        + "where id=1 or id=2 or id =3 or id=4 or id=5 or 7";
            } else {
                sql = "select*\n"
                        + "from OrderStatus\n"
                        + "where id=6 or id=7";
            }

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                OrderStatus os = new OrderStatus();
                os.setId(rs.getInt("id"));
                os.setName(rs.getString("name"));
                listStatus.add(os);
            }
        } catch (Exception e) {
        }
        return listStatus;
    }

}
