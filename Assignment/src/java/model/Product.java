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
    private int osId;
    private String os;
    //hardware    
    private int displayId;
    private String display;
    private int processorId;
    private String processor;
    private int gpuId;
    private String gpu;
    private int ramId;
    private String ram;
    private int harddriveId;
    private String harddrive;
    private String port;
    private String battery;
    //information
    private int priceId;
    private int price;
    private int quantity;
    private String note;
    private String color;
    private int rate;

    public Product(String id, String name, int cateId, int brandId, 
            int osId, String os, int displayId, String display, int processorId, 
            String processor, int gpuId, String gpu, int ramId, String ram, int harddriveId, 
            String harddrive, String port, String battery, int priceId, int price, int quantity, 
            String note, String color, int rate, List<ProductImg> listImage) {
        this.id = id;
        this.name = name;
        this.cateId = cateId;
        this.brandId = brandId;
        this.osId = osId;
        this.os = os;
        this.displayId = displayId;
        this.display = display;
        this.processorId = processorId;
        this.processor = processor;
        this.gpuId = gpuId;
        this.gpu = gpu;
        this.ramId = ramId;
        this.ram = ram;
        this.harddriveId = harddriveId;
        this.harddrive = harddrive;
        this.port = port;
        this.battery = battery;
        this.priceId = priceId;
        this.price = price;
        this.quantity = quantity;
        this.note = note;
        this.color = color;
        this.rate = rate;
        this.listImage = listImage;
    }



    public int getOsId() {
        return osId;
    }

    public void setOsId(int osId) {
        this.osId = osId;
    }

    public int getGpuId() {
        return gpuId;
    }

    public void setGpuId(int gpuId) {
        this.gpuId = gpuId;
    }

    public int getRamId() {
        return ramId;
    }

    public void setRamId(int ramId) {
        this.ramId = ramId;
    }

    public int getHarddriveId() {
        return harddriveId;
    }

    public void setHarddriveId(int harddriveId) {
        this.harddriveId = harddriveId;
    }

    public int getPriceId() {
        return priceId;
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

    public int getDisplayId() {
        return displayId;
    }

    public void setDisplayId(int displayId) {
        this.displayId = displayId;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public int getProcessorId() {
        return processorId;
    }

    public void setProcessorId(int processorId) {
        this.processorId = processorId;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
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
}
