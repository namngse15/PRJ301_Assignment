/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author tenhik
 */
public class Cart {

    private String productId;
    private String name;
    private int price;
    private int quantity;
    private String color;
    private List<ProductImg> listImage;

    public Cart() {
    }

    public Cart(String productId, String name, int price, int quantity, String color, List<ProductImg> listImage) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.color = color;
        this.listImage = listImage;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<ProductImg> getListImage() {
        return listImage;
    }

    public void setListImage(List<ProductImg> listImage) {
        this.listImage = listImage;
    }
    

}
