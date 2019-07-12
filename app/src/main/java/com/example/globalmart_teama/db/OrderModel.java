package com.example.globalmart_teama.db;

public class OrderModel {

    private String orderID;
    private int productID;
    private int quantity;
    private int customerID;
    private double unitPrice;
    private double totalPrice;

    public OrderModel(String orderID, int productID,double unitPrice, int quantity
            ,double totalPrice, int customerID) {
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.customerID = customerID;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
