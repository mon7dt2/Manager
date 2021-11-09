package com.example.mvvm.model.data;

import java.util.Date;

/**
 * Created by Mon7 on 10/10/2021.
 */
public class ProductDetailData{
    private String id;
    private String name;
    private double price;
    private String description;
    private long createdDate;
    private String avatarUrl;
    private String coverUrl;
    private String categoryTitle;
    private String categoryID;
    private String size;
    private int quantity;
    private int numberSave;
    private boolean isSaved;
    private float avarageOfRate = 0;

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

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getNumberSave() {
        return numberSave;
    }

    public void setNumberSave(int numberSave) {
        this.numberSave = numberSave;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    public float getAvarageOfRate() {
        return avarageOfRate;
    }

    public void setAvarageOfRate(float avarageOfRate) {
        this.avarageOfRate = avarageOfRate;
    }

    public ProductDetailData(String id, String name, double price, String description, long createdDate, String avatarUrl, String coverUrl, String categoryTitle, String categoryID, String size, int quantity, int numberSave, boolean isSaved, float avarageOfRate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.createdDate = createdDate;
        this.avatarUrl = avatarUrl;
        this.coverUrl = coverUrl;
        this.categoryTitle = categoryTitle;
        this.categoryID = categoryID;
        this.size = size;
        this.quantity = quantity;
        this.numberSave = numberSave;
        this.isSaved = isSaved;
        this.avarageOfRate = avarageOfRate;
    }

    public ProductDetailData() {
    }
}
