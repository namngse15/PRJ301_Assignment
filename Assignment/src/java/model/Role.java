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
public class Role {
    private int rid;
    private String rname;

    public Role() {
    }
    
    public Role(int id, String name) {
        this.rid = id;
        this.rname = name;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    @Override
    public String toString() {
        return "Role{" + "id=" + rid + ", name=" + rname + '}';
    }
}
