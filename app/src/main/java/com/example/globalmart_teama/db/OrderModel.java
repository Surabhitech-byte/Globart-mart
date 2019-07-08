package com.example.globalmart_teama.db;

public class OrderModel {

    public int OrderID;
    public int ProductID;
    public int Quantity;
    public int CustomerID;

    public OrderModel(int orderID, int productID, int quantity, int customerID) {
        OrderID = orderID;
        ProductID = productID;
        Quantity = quantity;
        CustomerID = customerID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        this.OrderID = orderID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        this.ProductID = productID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        this.Quantity = quantity;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        this.CustomerID = customerID;
    }
}
