package com.example.mvvm.model.data;

/**
 * Created by Mon7 on 9/30/2021.
 */
public class OrderData{
    private String Title;
    private int number;
    private int TYPE;

    public OrderData() {
    }

    public OrderData(String title, int number) {
        Title = title;
        this.number = number;
    }

    public OrderData(String title, int number, int TYPE) {
        Title = title;
        this.number = number;
        this.TYPE = TYPE;
    }

    public String getTitle() {
        return Title;
    }

    public int getTYPE() {
        return TYPE;
    }

    public void setTYPE(int TYPE) {
        this.TYPE = TYPE;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
