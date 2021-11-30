/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Orders;

/**
 *
 * @author tenhik
 */
public class OrdersDAO extends BaseDAO<Orders> {

    public boolean editOrderStatus(int orderId, int statusId) {
        int check = 0;
        try {
            String sql = "update Orders\n"
                    + "set statusId=?\n"
                    + "where id=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, statusId);
            stm.setInt(2, orderId);
            check = stm.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }

    public int saveOrder(Orders order) {
        int orderId = 0;
        try {
            String sql = "INSERT INTO Orders(accountId,phone,email,address,total,statusId,Note)\n"
                    + "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, order.getAccountId());
            stm.setString(2, order.getPhone());
            stm.setString(3, order.getEmail());
            stm.setString(4, order.getAddress());
            stm.setInt(5, order.getTotal());
            stm.setInt(6, order.getStatusId());
            stm.setString(7, order.getNote());
            stm.executeUpdate();
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return orderId;
    }

    public List<Orders> getAllOrders(int accountId) {
        List<Orders> listOrders = new ArrayList<>();
        try {
            String sql = "select *\n"
                    + "from Orders\n"
                    + "where accountId=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, accountId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Orders o = new Orders();
                o.setId(rs.getInt("id"));
                o.setAccountId(rs.getInt("accountId"));
                o.setAddress(rs.getString("address"));
                o.setPhone(rs.getString("phone"));
                o.setEmail(rs.getString("email"));
                o.setTotal(rs.getInt("total"));
                o.setDate(rs.getDate("date"));
                o.setStatusId(rs.getInt("statusId"));
                o.setNote(rs.getString("note"));
                listOrders.add(o);
            }
        } catch (Exception e) {
        }

        return listOrders;
    }

    public List<Orders> getAllUserOrders() {
        List<Orders> listOrders = new ArrayList<>();
        try {
            String sql = "select *\n"
                    + "from Orders\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Orders o = new Orders();
                o.setId(rs.getInt("id"));
                o.setAccountId(rs.getInt("accountId"));
                o.setAddress(rs.getString("address"));
                o.setPhone(rs.getString("phone"));
                o.setEmail(rs.getString("email"));
                o.setTotal(rs.getInt("total"));
                o.setDate(rs.getDate("date"));
                o.setStatusId(rs.getInt("statusId"));
                o.setNote(rs.getString("note"));
                listOrders.add(o);
            }
        } catch (SQLException e) {
        }

        return listOrders;
    }

    public List<Orders> getOrdersByProductId(String productId) {
        List<Orders> listOrders = new ArrayList<>();
        try {
            String sql = "select o.id, o.statusId\n"
                    + "from OrderDetail od join Orders o\n"
                    + "on o.id = od.orderId\n"
                    + "where od.productId=? \n"
                    + "and statusId=4 or statusId=6";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, productId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Orders o = new Orders();
                o.setId(rs.getInt("id"));
                o.setStatusId(rs.getInt("statusId"));
                listOrders.add(o);
            }
        } catch (SQLException e) {
        }

        return listOrders;
    }    
    public int getCountTotalOrder() {
        try {
            String sql = "select COUNT(*) \n"
                    + "from Orders \n";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            int count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getCountTotalOrderByAccount(int accountId) {
        try {
            String sql = "select COUNT(*) \n"
                    + "from Orders \n"
                    + "where accountId=? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, accountId);
            ResultSet rs = stm.executeQuery();
            int count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getTotalOrderPriceByAccount(int accountId) {
        try {
            String sql = "select SUM(total) as [sumorder]\n"
                    + "from Orders\n"
                    + "where accountId =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, accountId);
            ResultSet rs = stm.executeQuery();
            int count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getTotalOrderByAccountandStatus(int accountId) {
        try {
            String sql = "select SUM(countOrders)\n"
                    + "from\n"
                    + "(\n"
                    + "select COUNT(distinct o.id) countOrders,o.accountId\n"
                    + "from Orders o join OrderDetail od\n"
                    + "on o.id = od.orderId\n"
                    + "where o.statusId=6\n"
                    + "group by o.id,o.accountId\n"
                    + "having o.accountId=?\n"
                    + ")d";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, accountId);
            ResultSet rs = stm.executeQuery();
            int count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getTotalOrderByProductandStatus(String pId) {
        try {
            String sql = "select SUM(countOrders)\n"
                    + "from\n"
                    + "(\n"
                    + "select COUNT(distinct o.id) countOrders,od.productId\n"
                    + "from Orders o join OrderDetail od\n"
                    + "on o.id = od.orderId\n"
                    + "where o.statusId=1 or o.statusId=2 or o.statusId=3 or o.statusId=5\n"
                    + "group by o.id,od.productId\n"
                    + "having od.productId=?\n"
                    + ")d ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, pId);
            ResultSet rs = stm.executeQuery();
            int count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public boolean deleteOrders(int id) {
        int check = 0;
            try {
                String sql = "delete Orders\n"
                        + "where id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, id);
                check = statement.executeUpdate();
            } catch (SQLException ex) {
            }
        return check > 0;
    }

}
