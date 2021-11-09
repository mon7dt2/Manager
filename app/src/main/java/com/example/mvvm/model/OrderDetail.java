package com.example.mvvm.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Mon7 on 10/1/2021.
 */
public class OrderDetail implements Serializable{
    private String id;
    private String orderId;
    private String productId;
    private int quantity;
    private double price;
    private double total;
    private String size;

    @SerializedName("productUrl")
    private String productAvatarUrl;

    private String productName;

    public OrderDetail() {
    }

    public OrderDetail(String id, String orderId, String productId, int quantity, double price, double total, String size, String productAvatarUrl, String productName) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.size = size;
        this.productAvatarUrl = productAvatarUrl;
        this.productName = productName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getProductAvatarUrl() {
        return productAvatarUrl;
    }

    public void setProductAvatarUrl(String productAvatarUrl) {
        this.productAvatarUrl = productAvatarUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
