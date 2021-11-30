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
import model.Brand;
import model.Category;
import model.DisplaySize;
import model.Price;
import model.Processor;

/**
 *
 * @author tenhik
 */
public class CategoryDAO extends BaseDAO {

    public List<Brand> getAllBrand() {
        List<Brand> listBrand = new ArrayList<>();
        try {
            String sql = "select *\n"
                    + "from Brand";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Brand b = new Brand();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                listBrand.add(b);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listBrand;
    }

    public boolean addBrand(String brand) {
        int check = 0;
        try {
            String sql = "INSERT INTO Brand(name)\n"
                    + "VALUES(?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, brand);
            check = stm.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }

    public boolean editBrand(int id, String brand) {
        int check = 0;
        try {
            String sql = "update Brand\n"
                    + "set name=?\n"
                    + "where id =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, brand);
            stm.setInt(2, id);
            check = stm.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }

    public boolean removeBrand(int id) {
        int check = 0;
        try {
            String sql = "delete Brand\n"
                    + "where id=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            check = stm.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }

    public int getCountBrand() {
        try {
            String sql = "select COUNT(*)\n"
                    + "from Brand";
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

    public String getBrandById(int id) {
        String result = "";
        try {
            String sql = "select *\n"
                    + "from [Brand]"
                    + "where id =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result = rs.getString("name");
                return result;
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public List<DisplaySize> getAllDisplaySize() {
        List<DisplaySize> listDSize = new ArrayList<>();
        try {
            String sql = "select *\n"
                    + "from DisplaySize";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                //category    
                DisplaySize d = new DisplaySize();
                d.setSize(rs.getInt("size"));
                listDSize.add(d);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listDSize;
    }

    public boolean addDisplay(int display) {
        int check = 0;
        try {
            String sql = "INSERT INTO DisplaySize(size)\n"
                    + "VALUES(?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, display);
            check = stm.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }

    public boolean removeDisplay(int display) {
        int check = 0;
        try {
            String sql = "delete DisplaySize\n"
                    + "where size=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, display);
            check = stm.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }

    public int getCountDisplay() {
        try {
            String sql = "select COUNT(*)\n"
                    + "from DisplaySize";
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

    public List<Processor> getAllProcessor() {
        List<Processor> listP = new ArrayList<>();
        try {
            String sql = "select *\n"
                    + "from Processor";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                //category    
                Processor p = new Processor();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                listP.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listP;
    }

    public String getProcessorById(int id) {
        String result = "";
        try {
            String sql = "  select*\n"
                    + "  from Processor\n"
                    + "  where id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result = rs.getString("name");
                return result;
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public boolean addProcessor(String processor) {
        int check = 0;
        try {
            String sql = "INSERT INTO Processor(name)\n"
                    + "VALUES(?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, processor);
            check = stm.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }

    public boolean editProcessor(int id, String processor) {
        int check = 0;
        try {
            String sql = "update Processor\n"
                    + "set name=?\n"
                    + "where id =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, processor);
            stm.setInt(2, id);
            check = stm.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }

    public boolean removeProcessor(int id) {
        int check = 0;
        try {
            String sql = "delete  Processor\n"
                    + "where id=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            check = stm.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }

    public int getCountProcessor() {
        try {
            String sql = "select COUNT(*)\n"
                    + "from Processor";
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

    public List<Price> getAllPrice() {
        List<Price> listP = new ArrayList<>();
        try {
            String sql = "select *\n"
                    + "from Price";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                //category    
                Price p = new Price();
                p.setId(rs.getInt("id"));
                p.setAmount(rs.getString("amount"));
                listP.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listP;
    }

    public String getPriceById(int id) {
        String result = "";
        try {
            String sql = "  select*\n"
                    + "  from Price\n"
                    + "  where id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result = rs.getString("amount");
                return result;
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public boolean addPrice(String price) {
        int check = 0;
        try {
            String sql = "INSERT INTO Price(amount)\n"
                    + "VALUES(?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, price);
            check = stm.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }

    public boolean editPrice(int id, String price) {
        int check = 0;
        try {
            String sql = "update Price\n"
                    + "set amount=?\n"
                    + "where id =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, price);
            stm.setInt(2, id);
            check = stm.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }

    public boolean removePrice(int id) {
        int check = 0;
        try {
            String sql = "delete  Price\n"
                    + "where id=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            check = stm.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }

    public int getCountPrice() {
        try {
            String sql = "select COUNT(*)\n"
                    + "from Price";
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

    public List<Category> getAllCategory() {
        List<Category> listC = new ArrayList<>();
        try {
            String sql = "select *\n"
                    + "from [Category]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                //category    
                Category c = new Category();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                listC.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listC;
    }

    public boolean addCategory(String category) {
        int check = 0;
        try {
            String sql = "INSERT INTO Category(name)\n"
                    + "VALUES(?);";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, category);
            check = stm.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }

    public boolean editCategory(int id, String category) {
        int check = 0;
        try {
            String sql = "UPDATE Category\n"
                    + "set name=?\n"
                    + "where id=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, category);
            stm.setInt(2, id);
            check = stm.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }

    public boolean removeCategory(int id) {
        int check = 0;
        try {
            String sql = "delete Category\n"
                    + "where id=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            check = stm.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }

    public int getCountCategory() {
        try {
            String sql = "select COUNT(*)\n"
                    + "from [Category]";
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

    public String getCategoryById(int id) {
        String result = "";
        try {
            String sql = "select *\n"
                    + "from [Category]"
                    + "where id =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result = rs.getString("name");
                return result;
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public static void main(String[] args) {
        CategoryDAO c = new CategoryDAO();
        List<Category> lists = c.getAllCategory();
        String r = c.getProcessorById(7);
        System.out.println(r);
    }
}
