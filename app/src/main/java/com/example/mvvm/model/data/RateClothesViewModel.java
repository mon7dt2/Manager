package com.example.mvvm.model.data;

/**
 * Created by Mon7 on 10/10/2021.
 */
public class RateClothesViewModel{
    private String customerName;
    private String logoUrl;
    private long rateDate;
    private String message;
    private int rating;

    public RateClothesViewModel(String customerName, String logoUrl, long rateDate, String message, int rating) {
        this.customerName = customerName;
        this.logoUrl = logoUrl;
        this.rateDate = rateDate;
        this.message = message;
        this.rating = rating;
    }

    public RateClothesViewModel() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public long getRateDate() {
        return rateDate;
    }

    public void setRateDate(long rateDate) {
        this.rateDate = rateDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
