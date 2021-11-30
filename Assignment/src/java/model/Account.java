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
public class Account {

    private int id;
    private int roleId;
    private String name;
    private String phone;
    private String email;
    private String address;
    private Date dob;
    private String username;
    private String password;
    private int status;

    public Account() {
    }
//when user create acount

    public Account(int id, String name, String phone, String email,
            String address, Date dob, String username) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.dob = dob;
        this.username = username;
    }

    public Account(int roleId, String name, String phone, String email,
            String address, Date dob, String username, String password) {
        this.roleId = roleId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.dob = dob;
        this.username = username;
        this.password = password;
    }

    public Account(int id, int roleId, String name, String phone, String email,
            String address, Date dob, String username, String password) {
        this.id = id;
        this.roleId = roleId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.dob = dob;
        this.username = username;
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", roleId=" + roleId + ", name="
                + name + ", phone=" + phone + ", email=" + email + ", address="
                + address + ", dob=" + dob + ", username=" + username
                + ", password=" + password + '}';
    }

}
