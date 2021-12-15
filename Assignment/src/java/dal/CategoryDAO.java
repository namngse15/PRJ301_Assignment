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
import model.Gpu;
import model.Harddrive;
import model.Os;
import model.Price;
import model.Processor;
import model.Ram;

/**
 *
 * @author tenhik
 */
public class CategoryDAO extends BaseDAO {
//category

    public List<Category> getAllCategory() {
        List<Category> listC = new ArrayList<>();
        try {
            String sql = "select *\n"
                    + "from [ProCategory]";
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

    public int getCountCategory() {
        try {
            String sql = "select COUNT(*)\n"
                    + "from [ProCategory]";
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
                    + "from [ProCategory]"
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

//CRUD category
    public boolean addCategory(String category) {
        int check = 0;
        try {
            String sql = "INSERT INTO ProCategory(name)\n"
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
            String sql = "UPDATE ProCategory\n"
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
            String sql = "delete ProCategory\n"
                    + "where id=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            check = stm.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }
//get brand

    public List<Brand> getAllBrand() {
        List<Brand> listBrand = new ArrayList<>();
        try {
            String sql = "select *\n"
                    + "from ProBrand";
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

    public int getCountBrand() {
        try {
            String sql = "select COUNT(*)\n"
                    + "from ProBrand";
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
                    + "from [ProBrand]"
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

//CRUD brand
    public boolean addBrand(String brand) {
        int check = 0;
        try {
            String sql = "INSERT INTO ProBrand(name)\n"
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
            String sql = "update ProBrand\n"
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
            String sql = "delete ProBrand\n"
                    + "where id=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            check = stm.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }
//Display

    public List<DisplaySize> getAllDisplaySize() {
        List<DisplaySize> listDSize = new ArrayList<>();
        try {
            String sql = "select *\n"
                    + "from ProDisplay";
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

    public int getCountDisplay() {
        try {
            String sql = "select COUNT(*)\n"
                    + "from ProDisplay";
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
//CRUD display

    public boolean addDisplay(int display) {
        int check = 0;
        try {
            String sql = "INSERT INTO ProDisplay(size)\n"
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
            String sql = "delete ProDisplay\n"
                    + "where size=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, display);
            check = stm.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }
//Processor

    public List<Processor> getAllProcessor() {
        List<Processor> listP = new ArrayList<>();
        try {
            String sql = "select *\n"
                    + "from ProProcessor";
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
                    + "  from ProProcessor\n"
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

    public int getCountProcessor() {
        try {
            String sql = "select COUNT(*)\n"
                    + "from ProProcessor";
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
//CRUD processor

//Ram

    public List<Ram> getAllRam() {
        List<Ram> listR = new ArrayList<>();
        try {
            String sql = "select *\n"
                    + "from ProRam";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                //category    
                Ram r = new Ram();
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
                listR.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listR;
    }

    public String getRamById(int id) {
        String result = "";
        try {
            String sql = "  select*\n"
                    + "  from ProRam\n"
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

    public int getCountRam() {
        try {
            String sql = "select COUNT(*)\n"
                    + "from ProRam";
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
//CRUD ram

    public boolean addRam(String ram) {
        int check = 0;
        try {
            String sql = "INSERT INTO ProRam(name)\n"
                    + "VALUES(?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, ram);
            check = stm.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }

    public boolean editRam(int id, String ram) {
        int check = 0;
        try {
            String sql = "update ProRam\n"
                    + "set name=?\n"
                    + "where id =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, ram);
            stm.setInt(2, id);
            check = stm.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }

    public boolean removeRam(int id) {
        int check = 0;
        try {
            String sql = "delete ProRam\n"
                    + "where id=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            check = stm.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }
//HDD

    public List<Harddrive> getAllHdd() {
        List<Harddrive> listP = new ArrayList<>();
        try {
            String sql = "select *\n"
                    + "from ProHdd";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                //category    
                Harddrive p = new Harddrive();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                listP.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listP;
    }

    public String getHddById(int id) {
        String result = "";
        try {
            String sql = "  select*\n"
                    + "  from ProHdd\n"
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

    public int getCountHdd() {
        try {
            String sql = "select COUNT(*)\n"
                    + "from ProHdd";
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
//CRUD Hdd

    public boolean addHdd(String hdd) {
        int check = 0;
        try {
            String sql = "INSERT INTO ProHdd(name)\n"
                    + "VALUES(?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, hdd);
            check = stm.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }

    public boolean editHdd(int id, String processor) {
        int check = 0;
        try {
            String sql = "update ProHdd\n"
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

    public boolean removeHdd(int id) {
        int check = 0;
        try {
            String sql = "delete ProHdd\n"
                    + "where id=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            check = stm.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }
//Price

    public List<Price> getAllPrice() {
        List<Price> listP = new ArrayList<>();
        try {
            String sql = "select *\n"
                    + "from ProPrice";
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
                    + "  from ProPrice\n"
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

    public int getCountPrice() {
        try {
            String sql = "select COUNT(*)\n"
                    + "from ProPrice";
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
//CRUD price    

    public boolean addPrice(String price) {
        int check = 0;
        try {
            String sql = "INSERT INTO ProPrice(amount)\n"
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
            String sql = "update ProPrice\n"
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
            String sql = "delete ProPrice\n"
                    + "where id=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            check = stm.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }
//gpu

    public List<Gpu> getAllGpu() {
        List<Gpu> listP = new ArrayList<>();
        try {
            String sql = "select *\n"
                    + "from ProGpu";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                //category    
                Gpu p = new Gpu();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                listP.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listP;
    }

    public String getGpuById(int id) {
        String result = "";
        try {
            String sql = "  select*\n"
                    + "  from ProGpu\n"
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

    public int getCountGpu() {
        try {
            String sql = "select COUNT(*)\n"
                    + "from ProGpu";
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
//CRUD gpu

//os    

    public List<Os> getAllOs() {
        List<Os> listP = new ArrayList<>();
        try {
            String sql = "select *\n"
                    + "from ProOs";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                //category    
                Os p = new Os();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                listP.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listP;
    }

    public String getOsById(int id) {
        String result = "";
        try {
            String sql = "  select*\n"
                    + "  from ProOs\n"
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

    public static void main(String[] args) {
        CategoryDAO c = new CategoryDAO();
        List<Category> lists = c.getAllCategory();
        String r = c.getProcessorById(7);
        System.out.println(r);
    }
}
