package com.example.globalmart_teama.db;

import android.content.Context;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    private List<ProductsModel> productsModelList = new ArrayList<>();
    private List<OrderModel> ordersModelList;
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
        PRODUCT_NAMES.put(11, "Aashirvaad Atta,  1 kg Pouch");
        PRODUCT_NAMES.put(12, "Chilli - Powder Red, 500 g");
        PRODUCT_NAMES.put(13, "Sona Masoori Raw Rice, 25 kg Bag");
        PRODUCT_NAMES.put(14, "Cardamom/Elaichi - Green, 50 g");
        PRODUCT_NAMES.put(15, "Cloves/Launga, 20 g");
        PRODUCT_NAMES.put(16, "Tamarind/Imli - Deseeded, 200 g");
        PRODUCT_NAMES.put(17, "Phool Makhana, 100 g");
        PRODUCT_NAMES.put(18, "Turmeric, 100 g Pouch");
        PRODUCT_NAMES.put(19, "Asafoetida, 50 g Bottle");
        PRODUCT_NAMES.put(20, " Green Coriander, 200 g Pouch");
        PRODUCT_NAMES.put(21, "Organic - Cinnamon/Dalchini, 100 g");
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
        PRODUCT_DESC.put(11, "Aashirvaad Whole Wheat Flour is finished from the best accepted ingredients that help recover digestion and offer good number of well nutrients to the body. Aashirvaad Whole Grain Atta does not hold any extra preservatives or flavours. 100% full Wheat Chapati Flour and no Maida added. ");
        PRODUCT_DESC.put(12, "This chili powder is dried, pulverized fruit of one or more varieties of chili pepper, sometimes with the addition of other spices. It is used as a spice to add pungency or piquancy and flavour to dishes.");
        PRODUCT_DESC.put(13, "Sona masonry rice is softer that other assortment white rice and has got a distinctive flavor and taste. It will have more delectable and yummy element in it. It makes the meal yummy and healthy for all age group. It has an exceptional source of protein and high in the nutrients. ");
        PRODUCT_DESC.put(14, "Cardamom is considered one of the most valuable spices in the world due to its rich aroma and therapeutic properties. It adds a rich flavour and aroma to the dishes. I is widely used in India.");
        PRODUCT_DESC.put(15, "Cloves are the dried, flower bud of the evergreen tree, Eugenia aromatica. Cloves are an incredibly common type of spices that are broadly used for cooking and other purposes. It is also highly used in Cigarettes to add taste. It is used to create oil, as an antiseptic and even for other medicinal purposes. Cloves are also famous for their health benefits and have been usually used as a component in preparing remedies for several diseases.");
        PRODUCT_DESC.put(16, "This tamarind/Imli is sweeter and less sour. It is used in desserts as a jam, blended into juices or sweetened drinks, ice creams and other snacks.\n" +
                " Tamarind extract is used to flavour foods ranging from meals to snacks. Notable meat-based stews, it is often combined with dried fruits to achieve a sweet-sour tang.\n" +
                " It is included in daily diet as it has many fitness benefits that are sure to maintain you strong or recover your overall health. It is a necessary ingredient in many south Indian food preparations such as tamarind rice, rasam, sambar and tamarind chutney.");
        PRODUCT_DESC.put(17, "Phool Makhana or popped lotus seeds is an organic non-cereal nutritious food which is normally found in the stagnant water of lakes, tanks, ponds and wetlands. This is normally used to make makhana kheer, a sweet dish which is commonly consumed after fasting. Although some prefer to eat it raw or boiled, mostly it is used as an ingredient in Indian recipes.\n");
        PRODUCT_DESC.put(18, "MTR turmeric powder is one of the most reliable spices as the powder is extracted from high quality turmeric known as Sangali. They are packed with care so that the spice retains its freshness, colour and flavour till it reaches your home. Turmeric is one of the most-used spices in Indian household, and there is probably no dish that does not contain this spice. Adding this spice to the food gives it a bright yellow colour.");
        PRODUCT_DESC.put(19, "LG Powder - Asafoetida taste is bitter and acrid; however, when fried lightly, it adds amazing smell and taste. It makes the dishes easier to digest.");
        PRODUCT_DESC.put(20, "Everest Coriander Powder is vastly used in Indian cuisine. This powder is very much desirable and it also acts as a thickening cause. It presents a humid and gentle flavor to curry dishes and acts as a thickener for gravies.");
        PRODUCT_DESC.put(21, "These cinnamons have a sweet and penetrating flavor. These are purely cultivated and come filled with freshness, fitness and a delicious aroma. It is free from synthetic chemicals and pesticides.");
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
        PRODUCT_PRICES.put(11, 5);
        PRODUCT_PRICES.put(12, 15);
        PRODUCT_PRICES.put(13, 52);
        PRODUCT_PRICES.put(14, 15);
        PRODUCT_PRICES.put(15, 25);
        PRODUCT_PRICES.put(16, 35);
        PRODUCT_PRICES.put(17, 54);
        PRODUCT_PRICES.put(18, 55);
        PRODUCT_PRICES.put(19, 56);
        PRODUCT_PRICES.put(20, 45);
        PRODUCT_PRICES.put(21, 45);
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
        PRODUCT_IMAGE_IDS.put(11, "g1");
        PRODUCT_IMAGE_IDS.put(12, "g2");
        PRODUCT_IMAGE_IDS.put(13, "g3");
        PRODUCT_IMAGE_IDS.put(14, "g4");
        PRODUCT_IMAGE_IDS.put(15, "g5");
        PRODUCT_IMAGE_IDS.put(16, "g6");
        PRODUCT_IMAGE_IDS.put(17, "g7");
        PRODUCT_IMAGE_IDS.put(18, "g8");
        PRODUCT_IMAGE_IDS.put(19, "g9");
        PRODUCT_IMAGE_IDS.put(20, "g10");
        PRODUCT_IMAGE_IDS.put(21, "g11");
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
        PRODUCT_CATEGORY_NAMES.put(11, "GROCERIES");
        PRODUCT_CATEGORY_NAMES.put(12, "GROCERIES");
        PRODUCT_CATEGORY_NAMES.put(13, "GROCERIES");
        PRODUCT_CATEGORY_NAMES.put(14, "GROCERIES");
        PRODUCT_CATEGORY_NAMES.put(15, "GROCERIES");
        PRODUCT_CATEGORY_NAMES.put(16, "GROCERIES");
        PRODUCT_CATEGORY_NAMES.put(17, "GROCERIES");
        PRODUCT_CATEGORY_NAMES.put(18, "GROCERIES");
        PRODUCT_CATEGORY_NAMES.put(19, "GROCERIES");
        PRODUCT_CATEGORY_NAMES.put(20, "GROCERIES");
        PRODUCT_CATEGORY_NAMES.put(21, "GROCERIES");
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
        PRODUCT_COUNTRY_NAMES.put(11, "INDIA");
        PRODUCT_COUNTRY_NAMES.put(12, "INDIA");
        PRODUCT_COUNTRY_NAMES.put(13, "INDIA");
        PRODUCT_COUNTRY_NAMES.put(14, "INDIA");
        PRODUCT_COUNTRY_NAMES.put(15, "INDIA");
        PRODUCT_COUNTRY_NAMES.put(16, "INDIA");
        PRODUCT_COUNTRY_NAMES.put(17, "INDIA");
        PRODUCT_COUNTRY_NAMES.put(18, "INDIA");
        PRODUCT_COUNTRY_NAMES.put(19, "INDIA");
        PRODUCT_COUNTRY_NAMES.put(20, "INDIA");
        PRODUCT_COUNTRY_NAMES.put(21, "INDIA");
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
        PRODUCT_CODES.put(11, "97801243243456786");
        PRODUCT_CODES.put(12, "12345");
        PRODUCT_CODES.put(13, "1522352");
        PRODUCT_CODES.put(14, "153565");
        PRODUCT_CODES.put(15, "25546456");
        PRODUCT_CODES.put(16, "3346345");
        PRODUCT_CODES.put(17, "xxx656");
        PRODUCT_CODES.put(18, "124363565");
        PRODUCT_CODES.put(19, "43445365");
        PRODUCT_CODES.put(20, "432565365");
        PRODUCT_CODES.put(21, "43453452565365");
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

    public void createOrder(String orderID, int productID, double unitPrice, int quantity
            , double totalPrice, int customerID) {
        dataQueries.createOrder(
                new OrderModel(orderID, productID, unitPrice, quantity, totalPrice, customerID));
    }

    public List<OrderModel> getAllOrders() {
        return dataQueries.getAllOrders();
    }

    public List<ProductsModel> getProductsByCategory(String categoryType) {
        List<ProductsModel> productsByCategoryList = new ArrayList<>();
        this.productsModelList = getProductsModels();

        for (ProductsModel curr : productsModelList) {
            if (curr.getProductCategoryName().toUpperCase().equals(categoryType.toUpperCase())) {
                productsByCategoryList.add(curr);
            }
        }

        return productsByCategoryList;
    }
}

