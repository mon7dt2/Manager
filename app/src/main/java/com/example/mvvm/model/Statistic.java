package com.example.mvvm.model;

import java.util.Map;

/**
 * Created by Mon7 on 10/27/2021.
 */
public class Statistic{
    private int totalProduct;
    private double totalInCome;
    private int totalOrder;
    private int orderChecked;
    private int orderUnchecked;
    Map<String, Integer> listSubCate;

    public Statistic() {
    }

    public Statistic(int totalProduct, double totalInCome, int totalOrder, int orderChecked, int orderUnchecked, Map<String, Integer> listSubCate) {
        this.totalProduct = totalProduct;
        this.totalInCome = totalInCome;
        this.totalOrder = totalOrder;
        this.orderChecked = orderChecked;
        this.orderUnchecked = orderUnchecked;
        this.listSubCate = listSubCate;
    }

    public int getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(int totalProduct) {
        this.totalProduct = totalProduct;
    }

    public double getTotalInCome() {
        return totalInCome;
    }

    public void setTotalInCome(double totalInCome) {
        this.totalInCome = totalInCome;
    }

    public int getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(int totalOrder) {
        this.totalOrder = totalOrder;
    }

    public int getOrderChecked() {
        return orderChecked;
    }

    public void setOrderChecked(int orderChecked) {
        this.orderChecked = orderChecked;
    }

    public int getOrderUnchecked() {
        return orderUnchecked;
    }

    public void setOrderUnchecked(int orderUnchecked) {
        this.orderUnchecked = orderUnchecked;
    }

    public Map<String, Integer> getListSubCate() {
        return listSubCate;
    }

    public void setListSubCate(Map<String, Integer> listSubCate) {
        this.listSubCate = listSubCate;
    }
}
