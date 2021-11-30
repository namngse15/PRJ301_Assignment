/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author tenhik
 */
public class Product {

    private String id;
    private String name;
    private int cateId;
    private int brandId;
    //software
    private String os;
    //hardware    
    private int displaySize;
    private String display;
    private int processor;
    private String processDetail;
    private String gpu;
    private String ram;
    private String harddrive;
    private String port;
    private String battery;
    //information
    private int priceId;
    private int price;
    private int quantity;
    private String note;
    private String color;
    private int rate ;

    public int getPriceId() {
        return priceId;
    }

    public Product(String id, String name, int cateId, 
            int brandId, String os, int displaySize, String display, 
            int processor, String processDetail, String gpu, String ram, 
            String harddrive, String port, String battery, int priceId, 
            int price, int quantity, String note, String color) {
        this.id = id;
        this.name = name;
        this.cateId = cateId;
        this.brandId = brandId;
        this.os = os;
        this.displaySize = displaySize;
        this.display = display;
        this.processor = processor;
        this.processDetail = processDetail;
        this.gpu = gpu;
        this.ram = ram;
        this.harddrive = harddrive;
        this.port = port;
        this.battery = battery;
        this.priceId = priceId;
        this.price = price;
        this.quantity = quantity;
        this.note = note;
        this.color = color;
    }
    

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }
    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
    private List<ProductImg> listImage;

    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public int getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(int displaySize) {
        this.displaySize = displaySize;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public int getProcessor() {
        return processor;
    }

    public void setProcessor(int processor) {
        this.processor = processor;
    }

    public String getProcessDetail() {
        return processDetail;
    }

    public void setProcessDetail(String processDetail) {
        this.processDetail = processDetail;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getHarddrive() {
        return harddrive;
    }

    public void setHarddrive(String harddrive) {
        this.harddrive = harddrive;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
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
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", cateId=" + cateId + ", brandId=" + brandId +  ", os=" + os + ", displaySize=" + displaySize + ", display=" + display + ", processor=" + processor + ", processDetail=" + processDetail + ", gpu=" + gpu + ", ram=" + ram + ", harddrive=" + harddrive + ", port=" + port + ", battery=" + battery + ", price=" + price + ", quantity=" + quantity + "note=" + note + ", color=" + color + ", listImage=" + listImage + '}';
    }

 

}
