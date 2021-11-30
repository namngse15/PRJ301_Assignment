/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author tenhik
 */
public class OrderDetail {

    private int id;
    private String productName;
    private String productColor;
    private int productQuantity;
    private int productPrice;
    private String productId;
    private int orderId;

    public OrderDetail() {
    }

    public OrderDetail(int id, String productName, String productColor, int productQuantity, int productPrice, String productId, int orderId) {
        this.id = id;
        this.productName = productName;
        this.productColor = productColor;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.productId = productId;
        this.orderId = orderId;
    }

    public OrderDetail(String productName, String productColor, int productQuantity, int productPrice, String productId, int orderId) {
        this.productName = productName;
        this.productColor = productColor;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.productId = productId;
        this.orderId = orderId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

}
