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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author tenhik
 */
public class AccountDAO extends BaseDAO<Account> {
    
    OrdersDAO o = new OrdersDAO();
    
    public int getCountTotalUser(int roleId) {
        try {
            String sql = "select COUNT(*) \n"
                    + "from Account \n"
                    + "where roleId =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, roleId);
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
    
    public String getAccountByUserName(String username) {
        String result = "";
        try {
            String sql = "select username,email\n"
                    + "from Account\n"
                    + "where roleId=2"
                    + "and username=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result = rs.getString("username");
                return result;
            }
        } catch (Exception e) {
            
        }
        return null;
    }
    
    public String getAccountByUserEmail(String email) {
        String result = "";
        try {
            String sql = "select username,email\n"
                    + "from Account\n"
                    + "where roleId=2"
                    + "and email=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result = rs.getString("email");
                return result;
            }
        } catch (Exception e) {
            
        }
        return null;
    }
    
    public List<Account> getListUserAccount(int roleId) {
        List<Account> listUsers = new ArrayList<>();
        try {
            String sql = "select id,name,username,dob,phone,email,address,status\n"
                    + "from Account\n"
                    + "where roleId=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, roleId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setId(rs.getInt("id"));
                a.setName(rs.getString("name"));
                a.setUsername(rs.getString("username"));
                a.setDob(rs.getDate("dob"));
                a.setPhone(rs.getString("phone"));
                a.setEmail(rs.getString("email"));
                a.setAddress(rs.getString("address"));
                a.setStatus(rs.getInt("status"));
                listUsers.add(a);
            }
        } catch (SQLException e) {
        }
        return listUsers;
    }
    
    public Account getAccountByUsernameAndPassword(String username,
            String password) {
        try {
            String sql = "select a.*,r.*\n"
                    + "from Account a join Role r\n"
                    + "on a.roleId=r.rid\n"
                    + "where username=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setId(rs.getInt("id"));
                a.setName(rs.getString("name"));
                a.setPhone(rs.getString("phone"));
                a.setEmail(rs.getString("email"));
                a.setAddress(rs.getString("address"));
                a.setDob(rs.getDate("dob"));
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setRoleId(rs.getInt("roleId"));
                a.setStatus(rs.getInt("status"));
                if (a.getPassword().equals(password)) {
                    return a;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Account getAccountById(int accountId) {
        try {
            String sql = "select a.*,r.*\n"
                    + "from Account a join Role r\n"
                    + "on a.roleId=r.rid\n"
                    + "where id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, accountId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setId(rs.getInt("id"));
                a.setName(rs.getString("name"));
                a.setPhone(rs.getString("phone"));
                a.setEmail(rs.getString("email"));
                a.setAddress(rs.getString("address"));
                a.setDob(rs.getDate("dob"));
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setRoleId(rs.getInt("roleId"));
                a.setStatus(rs.getInt("status"));
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean updatePassword(String password, String username, String email) {
        int check = 0;
        try {
            String sql = "update Account\n"
                    + "set password = ?\n"
                    + "where username= ? \n"
                    + "and email= ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, password);
            statement.setString(2, username);
            statement.setString(3, email);
            check = statement.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }
    
    public boolean updateAccount(Account account) {
        int check = 0;
        try {
            String sql = "update Account\n"
                    + "set \n"
                    + "name =?,\n"
                    + "phone=?,\n"
                    + "email=?,\n"
                    + "address=?,\n"
                    + "dob=?\n"
                    + "where username= ?\n"
                    + "and id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, account.getName());
            statement.setString(2, account.getPhone());
            statement.setString(3, account.getEmail());
            statement.setString(4, account.getAddress());
            statement.setDate(5, account.getDob());
            statement.setString(6, account.getUsername());
            statement.setInt(7, account.getId());
            check = statement.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }
    
    public void updateStatus(int status, String username) {
        try {
            String sql = "update Account\n"
                    + "set status=?\n"
                    + "where username=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, status);
            statement.setString(2, username);
            statement.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public boolean insertAccount(Account a) {
        int check = 0;
        try {
            String sql = "INSERT INTO Account(name,phone,email,address,dob,username,password,roleId,status)\n"
                    + "values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,a.getName());
            statement.setString(2,a.getPhone());
            statement.setString(3,a.getEmail());
            statement.setString(4,a.getAddress());
            statement.setDate(5,a.getDob());
            statement.setString(6,a.getUsername());
            statement.setString(7,a.getPassword());
            statement.setInt(8,a.getRoleId());
            statement.setInt(9,a.getStatus());
            check = statement.executeUpdate();
        } catch (SQLException e) {
        }
        return check > 0;
    }
    
    public boolean deleteAccount(int id) {
        int check = 0;
        boolean checkRemoveOrder = false;
        int countOrdersByUser = o.getCountTotalOrderByAccount(id);
        if (countOrdersByUser == 0) {
            checkRemoveOrder = true;
        } else {
            checkRemoveOrder = o.deleteOrders(id);
        }
        if (checkRemoveOrder) {
            try {
                String sql = "delete Account\n"
                        + "where id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, id);
                check = statement.executeUpdate();
            } catch (SQLException ex) {
            }
        }
        return check > 0;
    }
    
    public static void main(String[] args) {
        AccountDAO a = new AccountDAO();
        
    }
}
