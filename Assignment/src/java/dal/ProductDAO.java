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
import model.Product;
import model.ProductImg;

/**
 *
 * @author tenhik
 */
public class ProductDAO extends BaseDAO<Product> {

    public List<ProductImg> getListImageByPid(String pid) {
        List<ProductImg> listImages = new ArrayList<>();
        try {
            String sql = "select i.*\n"
                    + "from [ProductImg] i \n"
                    + "join [Product] p\n"
                    + "on i.pId=p.id\n"
                    + "where p.id=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, pid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ProductImg i = new ProductImg();
                i.setId(rs.getString("pId"));
                i.setImg1(rs.getString("img1"));
                i.setImg2(rs.getString("img2"));
                listImages.add(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listImages;
    }
//get one product detail and product related

    public Product getOneProduct(String id) {
        try {
            String sql = "select p.*\n"
                    + "from [Product] p \n"
                    + "where p.id =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getString("id"));
                p.setName(rs.getString("name"));
                p.setCateId(rs.getInt("cateId"));
                p.setBrandId(rs.getInt("brandId"));
                p.setPriceId(rs.getInt("priceId"));
                p.setPrice(rs.getInt("price"));
                p.setOsId(rs.getInt("osId"));
                p.setOs(rs.getString("os"));
                p.setDisplayId(rs.getInt("displayId"));
                p.setDisplay(rs.getString("display"));
                p.setProcessorId(rs.getInt("processorId"));
                p.setProcessor(rs.getString("processor"));
                p.setGpuId(rs.getInt("gpuId"));
                p.setGpu(rs.getString("gpu"));
                p.setRamId(rs.getInt("ramId"));
                p.setRam(rs.getString("ram"));
                p.setHarddriveId(rs.getInt("harddriveId"));
                p.setHarddrive(rs.getString("harddrive"));
                p.setPort(rs.getString("port"));
                p.setBattery(rs.getString("battery"));
                p.setColor(rs.getString("color"));
                p.setQuantity(rs.getInt("quantity"));
                p.setNote(rs.getString("note"));
                List<ProductImg> listImages = getListImageByPid(rs.getString("id"));
                p.setListImage(listImages);
                return p;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//get product by id related
    public List<Product> getProductRelatedById(String id) {
        List<Product> listProducts = new ArrayList<>();
        try {
            String sql = "select p.id, p.name, p.processor ,p.ram,p.harddrive\n"
                    + "from Product p\n"
                    + "where id like ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getString("id"));
                p.setName(rs.getString("name"));
                p.setProcessor(rs.getString("processor"));
                p.setRam(rs.getString("ram"));
                p.setHarddrive(rs.getString("harddrive"));
                List<ProductImg> listImages = getListImageByPid(rs.getString("id"));
                p.setListImage(listImages);
                listProducts.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProducts;
    }

//get product without filter: home
    public List<Product> getAllProductPaggingandSorting(
            int pageIndex, int pageSize, int sId) {
        List<Product> listProducts = new ArrayList<>();
        try {
            String sqlFirst = "select * from(\n"
                    + "select ROW_NUMBER() over (order by";

            switch (sId) {
                case 0:
                    sqlFirst += " cateId";
                    break;
                case 1:
                    sqlFirst += " name asc ";
                    break;
                case 2:
                    sqlFirst += " name desc ";
                    break;
                case 3:
                    sqlFirst += " price asc ";
                    break;
                case 4:
                    sqlFirst += " price desc ";
                    break;
            }
            String sqlSecond = ")as r,*\n"
                    + "from Product\n"
                    + ") as t\n"
                    + "where r between(?-1)*?+1\n"
                    + "and ?*?";
            String sql = sqlFirst + sqlSecond;
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageIndex);
            stm.setInt(2, pageSize);
            stm.setInt(3, pageIndex);
            stm.setInt(4, pageSize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getString("id"));
                p.setName(rs.getString("name"));
                p.setCateId(rs.getInt("cateId"));
                p.setBrandId(rs.getInt("brandId"));
                p.setPriceId(rs.getInt("priceId"));
                p.setPrice(rs.getInt("price"));
                p.setOsId(rs.getInt("osId"));
                p.setOs(rs.getString("os"));
                p.setDisplayId(rs.getInt("displayId"));
                p.setDisplay(rs.getString("display"));
                p.setProcessorId(rs.getInt("processorId"));
                p.setProcessor(rs.getString("processor"));
                p.setGpuId(rs.getInt("gpuId"));
                p.setGpu(rs.getString("gpu"));
                p.setRamId(rs.getInt("ramId"));
                p.setRam(rs.getString("ram"));
                p.setHarddriveId(rs.getInt("harddriveId"));
                p.setHarddrive(rs.getString("harddrive"));
                p.setPort(rs.getString("port"));
                p.setBattery(rs.getString("battery"));
                p.setColor(rs.getString("color"));
                p.setQuantity(rs.getInt("quantity"));
                p.setNote(rs.getString("note"));
                List<ProductImg> listImages = getListImageByPid(rs.getString("id"));
                p.setListImage(listImages);
                listProducts.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProducts;
    }

//filter product: get product by all category
    public int getCountTotalProductByAllCate(int cateId, int brandId, int displayId,
            int processId, int ramId, int harddriveId, int priceId) {
        try {
            String sql = "select COUNT(*) \n"
                    + "from Product ";
            if (cateId != 0) {
                sql += " where cateId=? ";
            }
            if (brandId != 0 && cateId == 0) {
                sql += " where brandId=? ";
            } else if (brandId != 0 && cateId != 0) {
                sql += " and brandId=? ";
            }
            if (displayId != 0 && cateId == 0 && brandId == 0) {
                sql += " where displayId=? ";
            } else if (cateId != 0 || brandId != 0) {
                if (displayId != 0) {
                    sql += " and displayId=? ";
                }
            }
            if (processId != 0 && displayId == 0 && cateId == 0 && brandId == 0) {
                sql += " where processorId=? ";
            } else if (displayId != 0 || cateId != 0 || brandId != 0) {
                if (processId != 0) {
                    sql += " and processorId=? ";
                }
            }
            if (ramId != 0 && processId == 0 && displayId == 0 && cateId == 0 && brandId == 0) {
                sql += " where ramId=? ";
            } else if (processId != 0 || displayId != 0 || cateId != 0 || brandId != 0) {
                if (ramId != 0) {
                    sql += " and ramId=? ";
                }
            }
            if (harddriveId != 0 && ramId == 0 && processId == 0 && displayId == 0 && cateId == 0 && brandId == 0) {
                sql += " where harddriveId=? ";
            } else if (ramId != 0 || processId != 0 || displayId != 0 || cateId != 0 || brandId != 0) {
                if (harddriveId != 0) {
                    sql += " and harddriveId=? ";
                }
            }
            if (priceId != 0 && harddriveId == 0 && ramId == 0 && processId == 0 && displayId == 0 && cateId == 0 && brandId == 0) {
                sql += " where priceId=? ";
            } else if (harddriveId != 0 || ramId != 0 || processId != 0 || displayId != 0 || cateId != 0 || brandId != 0) {
                if (priceId != 0) {
                    sql += " and priceId=? ";
                }
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            int count = 1;
            if (cateId != 0) {
                stm.setInt(count++, cateId);
            }
            if (brandId != 0) {
                stm.setInt(count++, brandId);
            }
            if (displayId != 0) {
                stm.setInt(count++, displayId);
            }
            if (processId != 0) {
                stm.setInt(count++, processId);
            }
            if (ramId != 0) {
                stm.setInt(count++, ramId);
            }
            if (harddriveId != 0) {
                stm.setInt(count++, harddriveId);
            }
            if (priceId != 0) {
                stm.setInt(count++, priceId);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public List<Product> getAllProductByAllCategory(int cateId,
            int brandId, int displayId, int processId, int ramId, int harddriveId, int priceId,
            int pageIndex, int pageSize, int sortId) {
        List<Product> listProducts = new ArrayList<>();
        try {
            String sqlFirst = "select * from(\n"
                    + "select ROW_NUMBER() over (order by";

            switch (sortId) {
                case 0:
                    sqlFirst += " cateId";
                    break;
                case 1:
                    sqlFirst += " name asc ";
                    break;
                case 2:
                    sqlFirst += " name desc ";
                    break;
                case 3:
                    sqlFirst += " price asc ";
                    break;
                case 4:
                    sqlFirst += " price desc ";
                    break;
            }
            String sqlSecond = ") as r,*\n"
                    + "from Product\n"
                    + "where ";
            if (cateId != 0) {
                sqlSecond += " cateId=?\n";
            }
            if (brandId != 0 && cateId == 0) {
                sqlSecond += " brandId=?\n";
            } else if (cateId != 0) {
                if (brandId != 0) {
                    sqlSecond += "and brandId=?\n";
                }
            }
            if (displayId != 0 && brandId == 0 && cateId == 0) {
                sqlSecond += " displayId=?\n";
            } else if (brandId != 0 || cateId != 0) {
                if (displayId != 0) {
                    sqlSecond += "and displayId=?\n";
                }
            }
            if (processId != 0 && displayId == 0 && cateId == 0 && brandId == 0) {
                sqlSecond += " processorId=? ";
            } else if (displayId != 0 || cateId != 0 || brandId != 0) {
                if (processId != 0) {
                    sqlSecond += " and processorId=? ";
                }
            }
            if (ramId != 0 && processId == 0 && displayId == 0 && cateId == 0 && brandId == 0) {
                sqlSecond += " ramId=? ";
            } else if (processId != 0 || displayId != 0 || cateId != 0 || brandId != 0) {
                if (ramId != 0) {
                    sqlSecond += " and ramId=? ";
                }
            }
            if (harddriveId != 0 && ramId == 0 && processId == 0 && displayId == 0 && cateId == 0 && brandId == 0) {
                sqlSecond += " harddriveId=? ";
            } else if (ramId != 0 || processId != 0 || displayId != 0 || cateId != 0 || brandId != 0) {
                if (harddriveId != 0) {
                    sqlSecond += " and harddriveId=? ";
                }
            }
            if (priceId != 0 && harddriveId == 0 && ramId == 0 && processId == 0 && displayId == 0 && cateId == 0 && brandId == 0) {
                sqlSecond += " priceId=? ";
            } else if (harddriveId != 0 || ramId != 0 || processId != 0 || displayId != 0 || cateId != 0 || brandId != 0) {
                if (priceId != 0) {
                    sqlSecond += " and priceId=? ";
                }
            }
            String sqlThird = ") as t\n"
                    + "where r between(?-1)*?+1\n"
                    + "and ?*?";

            String sql = sqlFirst + sqlSecond + sqlThird;
            PreparedStatement stm = connection.prepareStatement(sql);
            int count = 1;
            if (cateId != 0) {
                stm.setInt(count++, cateId);
            }
            if (brandId != 0) {
                stm.setInt(count++, brandId);
            }
            if (displayId != 0) {
                stm.setInt(count++, displayId);
            }
            if (processId != 0) {
                stm.setInt(count++, processId);
            }
            if (ramId != 0) {
                stm.setInt(count++, ramId);
            }
            if (harddriveId != 0) {
                stm.setInt(count++, harddriveId);
            }
            if (priceId != 0) {
                stm.setInt(count++, priceId);
            }
            stm.setInt(count++, pageIndex);
            stm.setInt(count++, pageSize);
            stm.setInt(count++, pageIndex);
            stm.setInt(count, pageSize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getString("id"));
                p.setName(rs.getString("name"));
                p.setCateId(rs.getInt("cateId"));
                p.setBrandId(rs.getInt("brandId"));
                p.setPriceId(rs.getInt("priceId"));
                p.setPrice(rs.getInt("price"));
                p.setOsId(rs.getInt("osId"));
                p.setOs(rs.getString("os"));
                p.setDisplayId(rs.getInt("displayId"));
                p.setDisplay(rs.getString("display"));
                p.setProcessorId(rs.getInt("processorId"));
                p.setProcessor(rs.getString("processor"));
                p.setGpuId(rs.getInt("gpuId"));
                p.setGpu(rs.getString("gpu"));
                p.setRamId(rs.getInt("ramId"));
                p.setRam(rs.getString("ram"));
                p.setHarddriveId(rs.getInt("harddriveId"));
                p.setHarddrive(rs.getString("harddrive"));
                p.setPort(rs.getString("port"));
                p.setBattery(rs.getString("battery"));
                p.setColor(rs.getString("color"));
                p.setQuantity(rs.getInt("quantity"));
                p.setNote(rs.getString("note"));
                List<ProductImg> listImages = getListImageByPid(rs.getString("id"));
                p.setListImage(listImages);
                listProducts.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProducts;
    }

//get product by name
    public int getCountTotalProductBySearch(String text) {
        try {
            String sql = "select COUNT(*)\n"
                    + "from Product\n"
                    + "where name like ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + text + "%");
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

    public List<Product> getAllProductPaggingBySearch(
            String text, int pageIndex, int pageSize, int sId) {
        List<Product> listProducts = new ArrayList<>();
        try {
            String sqlFirst = "select * from(\n"
                    + "select ROW_NUMBER() over (order by";

            switch (sId) {
                case 0:
                    sqlFirst += " cateId";
                    break;
                case 1:
                    sqlFirst += " name asc ";
                    break;
                case 2:
                    sqlFirst += " name desc ";
                    break;
                case 3:
                    sqlFirst += " price asc ";
                    break;
                case 4:
                    sqlFirst += " price desc ";
                    break;
            }
            String sqlSecond = ")as r,*\n"
                    + "from Product\n"
                    + "where name like ?\n"
                    + ") as t\n"
                    + "where r between(?-1)*?+1\n"
                    + "and ?*?";
            String sql = sqlFirst + sqlSecond;

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + text + "%");
            stm.setInt(2, pageIndex);
            stm.setInt(3, pageSize);
            stm.setInt(4, pageIndex);
            stm.setInt(5, pageSize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getString("id"));
                p.setName(rs.getString("name"));
                p.setCateId(rs.getInt("cateId"));
                p.setBrandId(rs.getInt("brandId"));
                p.setPriceId(rs.getInt("priceId"));
                p.setPrice(rs.getInt("price"));
                p.setOsId(rs.getInt("osId"));
                p.setOs(rs.getString("os"));
                p.setDisplayId(rs.getInt("displayId"));
                p.setDisplay(rs.getString("display"));
                p.setProcessorId(rs.getInt("processorId"));
                p.setProcessor(rs.getString("processor"));
                p.setGpuId(rs.getInt("gpuId"));
                p.setGpu(rs.getString("gpu"));
                p.setRamId(rs.getInt("ramId"));
                p.setRam(rs.getString("ram"));
                p.setHarddriveId(rs.getInt("harddriveId"));
                p.setHarddrive(rs.getString("harddrive"));
                p.setPort(rs.getString("port"));
                p.setBattery(rs.getString("battery"));
                p.setColor(rs.getString("color"));
                p.setQuantity(rs.getInt("quantity"));
                p.setNote(rs.getString("note"));
                List<ProductImg> listImages = getListImageByPid(rs.getString("id"));
                p.setListImage(listImages);
                listProducts.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProducts;
    }

//count product by product entity    
    public int getCountTotalProductByCate(int cateId) {
        try {
            String sql = "select COUNT(*) \n"
                    + "from Product "
                    + " where cateId=? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cateId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getCountTotalProductByBrand(int brandId) {
        try {
            String sql = "select COUNT(*) \n"
                    + "from Product "
                    + " where brandId=? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, brandId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getCountTotalProductByDisplay(int displaySize) {
        try {
            String sql = "select COUNT(*) \n"
                    + "from Product "
                    + " where displayId=? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, displaySize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getCountTotalProductByProcessor(int processor) {
        try {
            String sql = "select COUNT(*) \n"
                    + "from Product "
                    + " where processorId=? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, processor);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getCountTotalProductByPrice(int priceId) {
        try {
            String sql = "select COUNT(*) \n"
                    + "from Product "
                    + " where priceId=? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, priceId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public List<Product> getAllProductByCategory(int cateId,
            int brandId, int displayId, int processId, int priceId) {
        List<Product> listProducts = new ArrayList<>();
        try {

            String sql = "select p.*\n"
                    + "from Product p\n";
            if (cateId != 0) {
                sql += "where p.cateId=?";
            } else if (brandId != 0) {
                sql += "where p.brandId=?";
            } else if (displayId != 0) {
                sql += "where p.displaySize=?";
            } else if (processId != 0) {
                sql += "where p.processor=?";
            } else if (priceId != 0) {
                sql += "where p.priceId=?";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            if (cateId != 0) {
                stm.setInt(1, cateId);
            }
            if (brandId != 0) {
                stm.setInt(1, brandId);
            }
            if (displayId != 0) {
                stm.setInt(1, displayId);
            }
            if (processId != 0) {
                stm.setInt(1, processId);
            }
            if (priceId != 0) {
                stm.setInt(1, priceId);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getString("id"));
                p.setName(rs.getString("name"));
                p.setCateId(rs.getInt("cateId"));
                p.setBrandId(rs.getInt("brandId"));
                p.setPriceId(rs.getInt("priceId"));
                p.setPrice(rs.getInt("price"));
                p.setOsId(rs.getInt("osId"));
                p.setOs(rs.getString("os"));
                p.setDisplayId(rs.getInt("displayId"));
                p.setDisplay(rs.getString("display"));
                p.setProcessorId(rs.getInt("processorId"));
                p.setProcessor(rs.getString("processor"));
                p.setGpuId(rs.getInt("gpuId"));
                p.setGpu(rs.getString("gpu"));
                p.setRamId(rs.getInt("ramId"));
                p.setRam(rs.getString("ram"));
                p.setHarddriveId(rs.getInt("harddriveId"));
                p.setHarddrive(rs.getString("harddrive"));
                p.setPort(rs.getString("port"));
                p.setBattery(rs.getString("battery"));
                p.setColor(rs.getString("color"));
                p.setQuantity(rs.getInt("quantity"));
                p.setNote(rs.getString("note"));
                List<ProductImg> listImages = getListImageByPid(rs.getString("id"));
                p.setListImage(listImages);
                listProducts.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProducts;
    }

//get all product: admin
    public int getCountTotalProduct() {
        try {
            String sql = "select COUNT(*) \n"
                    + "from Product ";
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

    public List<Product> getAllProduct() {
        ReviewDAO rdb = new ReviewDAO();
        List<Product> listProducts = new ArrayList<>();
        try {
            String sql = "select * from\n"
                    + "Product\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getString("id"));
                p.setName(rs.getString("name"));
                p.setCateId(rs.getInt("cateId"));
                p.setBrandId(rs.getInt("brandId"));
                p.setPriceId(rs.getInt("priceId"));
                p.setPrice(rs.getInt("price"));
                p.setOsId(rs.getInt("osId"));
                p.setOs(rs.getString("os"));
                p.setDisplayId(rs.getInt("displayId"));
                p.setDisplay(rs.getString("display"));
                p.setProcessorId(rs.getInt("processorId"));
                p.setProcessor(rs.getString("processor"));
                p.setGpuId(rs.getInt("gpuId"));
                p.setGpu(rs.getString("gpu"));
                p.setRamId(rs.getInt("ramId"));
                p.setRam(rs.getString("ram"));
                p.setHarddriveId(rs.getInt("harddriveId"));
                p.setHarddrive(rs.getString("harddrive"));
                p.setPort(rs.getString("port"));
                p.setBattery(rs.getString("battery"));
                p.setColor(rs.getString("color"));
                p.setQuantity(rs.getInt("quantity"));
                p.setNote(rs.getString("note"));
                int rate = rdb.getAverageProduct(rs.getString("id"));
                List<ProductImg> listImages = getListImageByPid(rs.getString("id"));
                p.setRate(rate);
                p.setListImage(listImages);
                listProducts.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProducts;
    }

//get product: color and quantity
    public String getColorByProductId(String pId) {
        try {
            String sql = "select p.color\n"
                    + "from Product p\n"
                    + "where p.id =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, pId);
            ResultSet rs = stm.executeQuery();
            String color = "";
            while (rs.next()) {
                color = rs.getString(1);
            }
            return color;

        } catch (Exception e) {

        }
        return null;
    }

    public int getQuantityByProductId(String pId) {
        try {
            String sql = "select p.quantity\n"
                    + "from Product p\n"
                    + "where p.id =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, pId);
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

//remove product
    public boolean removeProducImgtbyId(String pid) {
        int check = 0;
        try {
            String sql = "delete ProductImg\n"
                    + "where pId=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, pid);
            check = stm.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }

    public boolean removeProductReivewbyId(String pid) {
        int check = 0;
        try {
            String sql = "delete ReviewProduct\n"
                    + "where productId=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, pid);
            check = stm.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }

//    public boolean removeProductbyId(String productId, List<Review> listReviews) {
//        int check = 0;
//        boolean checkRemoveOrders = false;
//
//        List<Orders> listOrdersByProduct = dao.getOrdersByProductId(productId);
//        int countProductInOrder = dao.getTotalOrderByProductandStatus(productId);
//        boolean checkReview = false;
//        //remove reviews if product have
//        if (listReviews.isEmpty()) {
//            checkReview = true;
//        } else {
//            checkReview = r.deleteReviewByProduct(productId);
//        }
//        //remove orders if product have
//        if (listOrdersByProduct.isEmpty()) {
//            checkRemoveOrders = true;
//        } else {
//            if (countProductInOrder == 0) {
//                for (Orders o : listOrdersByProduct) {
//                    int quantity = 0;
//                    List<OrderDetail> listProductByOrders = odd.getProductIDandQuantityByOrderID(o.getId());
//                    for (OrderDetail p : listProductByOrders) {
//                        if (!p.getProductId().equals(productId) && o.getStatusId() == 4) {
//                            int quantityItem = getQuantityByProductId(p.getProductId());
//                            quantity = quantityItem + p.getProductQuantity();
//                            updateQuantity(quantity, p.getProductId());
//                        }
//                    }
//                    checkRemoveOrders = dao.deleteOrders(o.getId());
//                }
//            }
//        }
//        if (checkRemoveOrders && checkReview) {
//            //remove image if product have
//            boolean checkRemoveImg = removeProducImgtbyId(productId);
//            if (checkRemoveImg) {
//                try {
//                    String sql = "delete Product\n"
//                            + "where id =?;";
//                    PreparedStatement stm = connection.prepareStatement(sql);
//                    stm.setString(1, productId);
//                    check = stm.executeUpdate();
//                } catch (Exception e) {
//                }
//            }
//        }
//        return check > 0;
//    }
//update product entity    
    public boolean updateQuantity(int quantityProduct, String productId) {
        int check = 0;

        try {
            String sql = "update Product\n"
                    + "set quantity = ?\n"
                    + "where id=?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, quantityProduct);
            stm.setString(2, productId);
            check = stm.executeUpdate();

        } catch (Exception e) {
        }
        return check > 0;
    }

    public boolean updateProduct(Product product) {
        int check = 0;

        try {
            String sql = "update Product\n"
                    + "set name=?,\n"
                    + "cateId=?,\n"
                    + "brandId=?,\n"
                    + "priceId=?,\n"
                    + "price=?,\n"
                    + "osId=?,\n"
                    + "os=?,\n"
                    + "displayId=?,\n"
                    + "display=?,\n"
                    + "processorId=?,\n"
                    + "processor=?,\n"
                    + "gpuId=?,\n"
                    + "gpu=?,\n"
                    + "ramId=?,\n"
                    + "ram=?,\n"
                    + "harddriveId=?,\n"
                    + "harddrive=?,\n"
                    + "port=?,\n"
                    + "battery=?,\n"
                    + "color=?,\n"
                    + "quantity=?,\n"
                    + "note=?\n"
                    + "where id=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, product.getName());
            stm.setInt(2, product.getCateId());
            stm.setInt(3, product.getBrandId());
            stm.setInt(4, product.getPriceId());
            stm.setInt(5, product.getPrice());
            stm.setInt(6, product.getOsId());
            stm.setString(7, product.getOs());
            stm.setInt(8, product.getDisplayId());
            stm.setString(9, product.getDisplay());
            stm.setInt(10, product.getProcessorId());
            stm.setString(11, product.getProcessor());
            stm.setInt(12, product.getGpuId());
            stm.setString(13, product.getGpu());
            stm.setInt(14, product.getRamId());
            stm.setString(15, product.getRam());
            stm.setInt(16, product.getHarddriveId());
            stm.setString(17, product.getHarddrive());
            stm.setString(18, product.getPort());
            stm.setString(19, product.getBattery());
            stm.setString(20, product.getColor());
            stm.setInt(21, product.getQuantity());
            stm.setString(22, product.getNote());
            stm.setString(23, product.getId());
            check = stm.executeUpdate();

        } catch (Exception e) {
        }
        return check > 0;
    }

    public boolean updateProductWithImg(String pId, String img1, String img2) {
        int check = 0;
        try {
            String sql = "";
            if (img2 == null) {
                sql = "update ProductImg\n"
                        + "set img1=?\n"
                        + "where pId=?";
            } else {
                sql = "update ProductImg\n"
                        + "set img1=?\n"
                        + ",img2=?\n"
                        + "where pId=?";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            int count = 1;
            stm.setString(count++, img1);
            if (img2 != null) {
                stm.setString(count++, img2);
            }
            stm.setString(count, pId);

            check = stm.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }

//add product    
    public boolean addProduct(Product product) {
        int check = 0;
        try {
            String sql = "INSERT INTO Product(id,name,cateId,brandId,priceId,price,osId,os,displayId,display,processorId,processor,gpuId,\n"
                    + "gpu,ramId,ram,harddriveId,harddrive,port,battery,color,quantity)\n"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, product.getName());
            stm.setInt(2, product.getCateId());
            stm.setInt(3, product.getBrandId());
            stm.setInt(4, product.getPriceId());
            stm.setInt(5, product.getPrice());
            stm.setInt(6, product.getOsId());
            stm.setString(7, product.getOs());
            stm.setInt(8, product.getDisplayId());
            stm.setString(9, product.getDisplay());
            stm.setInt(10, product.getProcessorId());
            stm.setString(11, product.getProcessor());
            stm.setInt(12, product.getGpuId());
            stm.setString(13, product.getGpu());
            stm.setInt(14, product.getRamId());
            stm.setString(15, product.getRam());
            stm.setInt(16, product.getHarddriveId());
            stm.setString(17, product.getHarddrive());
            stm.setString(18, product.getPort());
            stm.setString(19, product.getBattery());
            stm.setString(20, product.getColor());
            stm.setInt(21, product.getQuantity());
            stm.setString(22, product.getNote());
            stm.setString(23, product.getId());
            check = stm.executeUpdate();

        } catch (Exception e) {
        }
        return check > 0;
    }

    public boolean addProductWithImg(String pId, String img1, String img2) {
        int check = 0;
        try {
            String sql = "";
            if (img2 == null) {
                sql = "INSERT INTO ProductImg(pId,img1)\n"
                        + "VALUES(?,?)";
            } else {
                sql = "INSERT INTO ProductImg(pId,img1,img2)\n"
                        + "VALUES(?,?,?)";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, pId);
            stm.setString(2, img1);
            if (img2 != null) {
                stm.setString(3, img2);
            }
            check = stm.executeUpdate();

        } catch (Exception e) {
        }
        return check > 0;
    }
}
