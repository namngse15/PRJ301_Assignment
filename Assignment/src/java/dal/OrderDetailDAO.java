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
import model.OrderDetail;
import model.Orders;

/**
 *
 * @author tenhik
 */
public class OrderDetailDAO extends BaseDAO<OrderDetail> {

    int check = 0;

    public boolean addOrderDetail(OrderDetail orderDetail) {
        try {
            String sql = "INSERT INTO OrderDetail(productName,productColor,productQuantity,ProductPrice,productId,orderId)\n"
                    + "VALUES(?,?,?,?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, orderDetail.getProductName());
            stm.setString(2, orderDetail.getProductColor());
            stm.setInt(3, orderDetail.getProductQuantity());
            stm.setInt(4, orderDetail.getProductPrice());
            stm.setString(5, orderDetail.getProductId());
            stm.setInt(6, orderDetail.getOrderId());
            check = stm.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }

    public List<OrderDetail> getOrderDetailByOrderID(int orderId) {
        List<OrderDetail> listOrderDetails = new ArrayList<>();
        try {
            String sql = "select *\n"
                    + "from OrderDetail\n"
                    + "where orderId=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, orderId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail();
                od.setId(rs.getInt("id"));
                od.setProductName(rs.getString("productName"));
                od.setProductColor(rs.getString("productColor"));
                od.setProductQuantity(rs.getInt("productQuantity"));
                od.setProductPrice(rs.getInt("ProductPrice"));
                od.setProductId(rs.getString("productId"));
                od.setOrderId(rs.getInt("orderId"));
                listOrderDetails.add(od);
            }
        } catch (Exception e) {
        }

        return listOrderDetails;
    }

    public List<OrderDetail> getProductIDandQuantityByOrderID(int orderId) {
        List<OrderDetail> listOrderDetails = new ArrayList<>();
        try {
            String sql = "select o.productId,o.productQuantity\n"
                    + "from OrderDetail o\n"
                    + "where o.orderId =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, orderId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail();
                od.setProductId(rs.getString("productId"));
                od.setProductQuantity(rs.getInt("productQuantity"));
                listOrderDetails.add(od);
            }
        } catch (Exception e) {
        }

        return listOrderDetails;
    }

    public boolean deleteOrderDetail(int orderId) {
        int check = 0;
        try {
            String sql = "delete OrderDetail\n"
                    + "where orderId=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            check = statement.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }
    
    public static void main(String[] args) {
        OrderDetailDAO d = new OrderDetailDAO();
        List<OrderDetail> od = d.getProductIDandQuantityByOrderID(14);
        System.out.println(od.size());
        for (OrderDetail or : od) {
            System.out.println(or.getProductId()+" "+ or.getProductQuantity());
        }
    }
}
