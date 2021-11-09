package com.example.mvvm.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mon7 on 10/1/2021.
 */
public class Order implements Serializable{
    private String id;
    private String customerName;
    private String phone;
    private String address;
    private double total;

    public Order(String id, String customerName, String phone, String address, double total) {
        this.id = id;
        this.customerName = customerName;
        this.phone = phone;
        this.address = address;
        this.total = total;
    }

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
