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
    private String fullname;
    private String email;
    private int rate;
    private String message;
    private int accountId;
    private String ProductId;

    public Review() {
    }

    public Review(String fullname, int rate, String message, String ProductId) {
        this.fullname = fullname;
        this.rate = rate;
        this.message = message;
        this.ProductId = ProductId;
    }
        public Review(String fullname, String email, int rate, String message, int accountId, String ProductId) {
        this.fullname = fullname;
        this.email = email;
        this.rate = rate;
        this.message = message;
        this.accountId = accountId;
        this.ProductId = ProductId;
    }

    public Review(int id, String fullname, String email, int rate, String message, int accountId, String ProductId) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
