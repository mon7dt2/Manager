package com.example.mvvm.model;

import java.io.Serializable;

/**
 * Created by Mon7 on 10/8/2021.
 */
public class Product implements Serializable{

    private String id;
    private String name;
    private double price;
    private String description;
    private String createdDate;
    private String logoUrl;
    private String size;
    private int isSale;
    private int quantity;
    private float salePercent;
    private Category category;

    public Product() {
    }

    public Product(String id, String name, double price, String description,
                   String createdDate, String logoUrl, String size, int isSale, int quantity,
                   float salePercent, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.createdDate = createdDate;
        this.logoUrl = logoUrl;
        this.size = size;
        this.isSale = isSale;
        this.salePercent = salePercent;
        this.category = category;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getIsSale() {
        return isSale;
    }

    public void setIsSale(int isSale) {
        this.isSale = isSale;
    }

    public float getSalePercent() {
        return salePercent;
    }

    public void setSalePercent(float salePercent) {
        this.salePercent = salePercent;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
