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
public class Review {

    private int id;
    private int rate;
    private String message;
    private int accountId;
    private String ProductId;

    public Review() {
    }


        public Review(int rate, String message, int accountId, String ProductId) {
        this.rate = rate;
        this.message = message;
        this.accountId = accountId;
        this.ProductId = ProductId;
    }

    public Review(int id, String fullname, String email, int rate, String message, int accountId, String ProductId) {
        this.id = id;
        this.rate = rate;
        this.message = message;
        this.accountId = accountId;
        this.ProductId = ProductId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String ProductId) {
        this.ProductId = ProductId;
    }
    

}
