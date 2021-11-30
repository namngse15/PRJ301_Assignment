/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Review;

/**
 *
 * @author tenhik
 */
public class ReviewDAO extends BaseDAO<Review> {

    public List<Review> getAllReviewByProductId(String productId) {
        List<Review> listReviews = new ArrayList<>();
        try {
            String sql = "select *\n"
                    + "from ReviewProduct\n"
                    + "where productId=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, productId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Review r = new Review();
                r.setId(rs.getInt("id"));
                r.setAccountId(rs.getInt("accountId"));
                r.setProductId(rs.getString("productId"));
                r.setFullname(rs.getString("fullname"));
                r.setEmail(rs.getString("email"));
                r.setRate(rs.getInt("rate"));
                r.setMessage(rs.getString("message"));
                listReviews.add(r);
            }
        } catch (Exception e) {
        }
        return listReviews;
    }

    public int getNumberReviewByProduct(String productId) {
        try {
            String sql = "select COUNT(*)\n"
                    + "from ReviewProduct\n"
                    + "where productId=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, productId);
            ResultSet rs = stm.executeQuery();
            int count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (Exception e) {
        }
        return 0;
    }

    public int getSumReviewByProduct(String productId, int accountId) {
        try {
            String sql = "select SUM(rate)\n"
                    + "from ReviewProduct\n"
                    + "where productId=?\n"
                    + "and accountId = ?\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, productId);
            stm.setInt(2, accountId);
            ResultSet rs = stm.executeQuery();
            int sum = 0;
            while (rs.next()) {
                sum = rs.getInt(1);
            }
            return sum;
        } catch (Exception e) {
        }
        return 0;
    }

    public int getReviewByProduct(String productId) {
        try {
            String sql = "select SUM(rate)\n"
                    + "from ReviewProduct\n"
                    + "where productId=?\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, productId);
            ResultSet rs = stm.executeQuery();
            int sum = 0;
            while (rs.next()) {
                sum = rs.getInt(1);
            }
            return sum;
        } catch (Exception e) {
        }
        return 0;
    }

    public int getAverageProduct(String productId) {
        ReviewDAO r = new ReviewDAO();
        int average = 0;
        try {
            int sum = r.getReviewByProduct(productId);
            int count = r.getNumberReviewByProduct(productId);
            average = sum / count;
        } catch (Exception e) {
        }
        return average;
    }

    public boolean insertReview(Review review) {
        int check = 0;
        try {
            String sql = "insert into ReviewProduct(accountId,fullname,email,rate,message,productId)\n"
                    + "values(?,?,?,?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, review.getAccountId());
            stm.setString(2, review.getFullname());
            stm.setString(3, review.getEmail());
            stm.setInt(4, review.getRate());
            stm.setString(5, review.getMessage());
            stm.setString(6, review.getProductId());
            check = stm.executeUpdate();
        } catch (SQLException e) {
        }
        return check > 0;
    }

    public boolean deleteReview(int id) {
        int check = 0;
        try {
            String sql = "delete\n"
                    + "from ReviewProduct\n"
                    + "where id =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            check = stm.executeUpdate();
        } catch (SQLException e) {
        }
        return check > 0;
    }

    public boolean deleteReviewByProduct(String id) {
        int check = 0;
        try {
            String sql = "delete ReviewProduct\n"
                    + "where productId=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            check = stm.executeUpdate();
        } catch (SQLException e) {
        }
        return check > 0;
    }

    public boolean updateReview(int id, int rate, String mess) {
        int check = 0;
        try {
            String sql = "UPDATE ReviewProduct\n"
                    + "set\n"
                    + "rate=?,\n"
                    + "message=?\n"
                    + "\n"
                    + "where id=?\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, rate);
            stm.setString(2, mess);
            stm.setInt(3, id);
            check = stm.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }
}
