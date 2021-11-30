/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author tenhik
 */
public class Orders {

    private int id;
    private int accountId;
    private String address;
    private String phone;
    private String email;
    private int total;
    private Date date;
    private int StatusId;
    private String note;

    public Orders() {
    }

    public Orders(int id, int accountId, String address, String phone, String email, 
            int total, Date date, int StatusId, String note) {
        this.id = id;
        this.accountId = accountId;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.total = total;
        this.date = date;
        this.StatusId = StatusId;
        this.note = note;
    }
        public Orders( int accountId, String address, String phone, String email, 
            int total, int StatusId, String note) {
        this.accountId = accountId;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.total = total;
        this.StatusId = StatusId;
        this.note = note;
    }
    
    
    
    
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStatusId() {
        return StatusId;
    }

    public void setStatusId(int StatusId) {
        this.StatusId = StatusId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    

}
