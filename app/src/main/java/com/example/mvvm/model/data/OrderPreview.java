package com.example.mvvm.model.data;

/**
 * Created by Mon7 on 10/22/2021.
 */
public class OrderPreview{
    private String id;
    private int isCheck;
    private long createAt;

    public OrderPreview() {
    }

    public OrderPreview(String id, int isCheck, long createdAt) {
        this.id = id;
        this.isCheck = isCheck;
        this.createAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(int isCheck) {
        this.isCheck = isCheck;
    }

    public long getCreatedAt() {
        return createAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createAt = createdAt;
    }
}
