package com.example.globalmart_teama.db;

import android.content.Context;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    private List<ProductsModel> productsModelList = new ArrayList<>();
    private final DataQueries dataQueries;

    private static final Map<Integer, String> PRODUCT_NAMES = new HashMap<>();
    private static final Map<Integer, String> PRODUCT_DESC = new HashMap<>();
    private static final Map<Integer, Integer> PRODUCT_PRICES = new HashMap<>();
    private static final Map<Integer, String> PRODUCT_IMAGE_IDS = new HashMap<>();
    private static final Map<Integer, String> PRODUCT_CATEGORY_NAMES = new HashMap<>();
    private static final Map<Integer, String> PRODUCT_COUNTRY_NAMES = new HashMap<>();
    private static final Map<Integer, String> PRODUCT_CODES = new HashMap<>();

    static {
        PRODUCT_NAMES.put(1, "Mango Lassi");
        PRODUCT_NAMES.put(2, "Yakult");
        PRODUCT_NAMES.put(3, "Red Bull");
        PRODUCT_NAMES.put(4, "Glue wine");
        PRODUCT_NAMES.put(5, "Falooda");
        PRODUCT_NAMES.put(6, "Chivas");
        PRODUCT_NAMES.put(7, "Pearl Milk Tea");
        PRODUCT_NAMES.put(8, "Tieguanyin");
        PRODUCT_NAMES.put(9, "C100");
        PRODUCT_NAMES.put(10, "Jiuniang");
    }

    static {
        PRODUCT_DESC.put(1, "Indian summer drink");
        PRODUCT_DESC.put(2, "Japanese summer drink");
        PRODUCT_DESC.put(3, "Russian Energy drink");
        PRODUCT_DESC.put(4, "German special hot wine");
        PRODUCT_DESC.put(5, "Indian summer drink");
        PRODUCT_DESC.put(6, "Chinese popular drink mixed with Green tea");
        PRODUCT_DESC.put(7, "Chinese drink imported from Taiwan");
        PRODUCT_DESC.put(8, "Known as the \"Iron Goddess of Mercy,\" tieguanyin falls somewhere between green and black tea taste-wise, but is yellowish in color. With a fresh floral aroma and a fruity, berry-like sweetness, this premium variety of oolong tea leaves a honey aftertaste.");
        PRODUCT_DESC.put(9, "This is China's version of electrolyte water. From lemon to grapefruit flavors, the vitamin-rich drink has a tangy, sweet and acidic lemonade taste.");
        PRODUCT_DESC.put(10, "This soup-like Chinese dish is actually unfiltered rice wine, but it has a very low alcohol content. Osmanthus flowers bring up the fragrance.");
    }

    static {
        PRODUCT_PRICES.put(1, 5);
        PRODUCT_PRICES.put(2, 15);
        PRODUCT_PRICES.put(3, 52);
        PRODUCT_PRICES.put(4, 15);
        PRODUCT_PRICES.put(5, 25);
        PRODUCT_PRICES.put(6, 35);
        PRODUCT_PRICES.put(7, 54);
        PRODUCT_PRICES.put(8, 55);
        PRODUCT_PRICES.put(9, 56);
        PRODUCT_PRICES.put(10, 45);
    }

    static {
        PRODUCT_IMAGE_IDS.put(1, "b1");
        PRODUCT_IMAGE_IDS.put(2, "b2");
        PRODUCT_IMAGE_IDS.put(3, "b3");
        PRODUCT_IMAGE_IDS.put(4, "b4");
        PRODUCT_IMAGE_IDS.put(5, "b5");
        PRODUCT_IMAGE_IDS.put(6, "b6");
        PRODUCT_IMAGE_IDS.put(7, "b7");
        PRODUCT_IMAGE_IDS.put(8, "b8");
        PRODUCT_IMAGE_IDS.put(9, "b9");
        PRODUCT_IMAGE_IDS.put(10, "b10");
    }

    static {
        PRODUCT_CATEGORY_NAMES.put(1, "BEVERAGES");
        PRODUCT_CATEGORY_NAMES.put(2, "BEVERAGES");
        PRODUCT_CATEGORY_NAMES.put(3, "BEVERAGES");
        PRODUCT_CATEGORY_NAMES.put(4, "BEVERAGES");
        PRODUCT_CATEGORY_NAMES.put(5, "BEVERAGES");
        PRODUCT_CATEGORY_NAMES.put(6, "BEVERAGES");
        PRODUCT_CATEGORY_NAMES.put(7, "BEVERAGES");
        PRODUCT_CATEGORY_NAMES.put(8, "BEVERAGES");
        PRODUCT_CATEGORY_NAMES.put(9, "BEVERAGES");
        PRODUCT_CATEGORY_NAMES.put(10, "BEVERAGES");
    }

    static {
        PRODUCT_COUNTRY_NAMES.put(1, "INDIA");
        PRODUCT_COUNTRY_NAMES.put(2, "JAPAN");
        PRODUCT_COUNTRY_NAMES.put(3, "RUSSIA");
        PRODUCT_COUNTRY_NAMES.put(4, "GERMAN");
        PRODUCT_COUNTRY_NAMES.put(5, "INDIA");
        PRODUCT_COUNTRY_NAMES.put(6, "CHINA");
        PRODUCT_COUNTRY_NAMES.put(7, "CHINA");
        PRODUCT_COUNTRY_NAMES.put(8, "CHINA");
        PRODUCT_COUNTRY_NAMES.put(9, "CHINA");
        PRODUCT_COUNTRY_NAMES.put(10, "CHINA");

    }

    static {
        PRODUCT_CODES.put(1, "9780123456786");
        PRODUCT_CODES.put(2, "123");
        PRODUCT_CODES.put(3, "152");
        PRODUCT_CODES.put(4, "153");
        PRODUCT_CODES.put(5, "25");
        PRODUCT_CODES.put(6, "35");
        PRODUCT_CODES.put(7, "xxx");
        PRODUCT_CODES.put(8, "1243");
        PRODUCT_CODES.put(9, "434");
        PRODUCT_CODES.put(10, "4325");
    }

    public Database(Context context) {
        dataQueries = new DataQueries(context);
        dataQueries.open();

        if (dataQueries.isAppRunningFirstTime()) {
            for (int key : PRODUCT_NAMES.keySet()) {  //NAME and AGE use the same keyset.

                dataQueries.createProduct(
                        new ProductsModel(key, PRODUCT_NAMES.get(key), PRODUCT_DESC.get(key),
                                PRODUCT_PRICES.get(key), PRODUCT_IMAGE_IDS.get(key),
                                PRODUCT_COUNTRY_NAMES.get(key), PRODUCT_CATEGORY_NAMES.get(key),
                                PRODUCT_CODES.get(key)));
            }

        }

        productsModelList = dataQueries.getAllProducts();
    }

    public List<ProductsModel> getProductsModels() {
        return productsModelList;
    }

    public void closeDatabase() {
        dataQueries.close();
    }

    public ProductsModel getProductModelByID(int productID) {
        for (ProductsModel curr : productsModelList) {
            if (curr.getProductID() == productID) {
                return curr;
            }
        }

        return null;
    }
}

