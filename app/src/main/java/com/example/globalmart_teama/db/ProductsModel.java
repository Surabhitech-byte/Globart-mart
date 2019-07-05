package com.example.globalmart_teama.db;

public class ProductsModel {

    public int productID;
    public String productName;
    public String productDesc;
    public int productPrice;
    public String productImageID;
    public String productCountryName;
    public String productCategoryName;

    public ProductsModel(int id, String productName, String productDesc, int productPrice,
                         String productImageID, String productCountryName, String  productCategoryName) {
        this.productID = id;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productPrice = productPrice;
        this.productImageID = productImageID;
        this.productCountryName = productCountryName;
        this.productCategoryName = productCategoryName;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImageID() {
        return productImageID;
    }

    public void setProductImageID(String productImageID) {
        this.productImageID = productImageID;
    }

    public String getProductCountryName() {
        return productCountryName;
    }

    public void setProductCountryName(String productCountryName) {
        this.productCountryName = productCountryName;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }
}
