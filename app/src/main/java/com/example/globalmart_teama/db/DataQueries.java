package com.example.globalmart_teama.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DataQueries {

    private final Context mContext;
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    public static final String SHARED_PREF_BEVERAGE = "shared pref beverage";
    public static final String IS_FIRST_TIME = "is first time";

    private String[] productColumns = {
            DBHelper.ProductsEntry.COLUMN_PRODUCT_ID,
            DBHelper.ProductsEntry.COLUMN_PRODUCT_NAME,
            DBHelper.ProductsEntry.COLUMN_PRODUCT_DESC,
            DBHelper.ProductsEntry.COLUMN_PRODUCT_PRICE,
            DBHelper.ProductsEntry.COLUMN_PRODUCT_IMAGE_ID,
            DBHelper.ProductsEntry.COLUMN_COUNTRY_NAME,
            DBHelper.ProductsEntry.COLUMN_CATEGORY_NAME,
            DBHelper.ProductsEntry.COLUMN_PRODUCT_CODE
    };


    public DataQueries(Context context) {
        mContext = context;
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public ProductsModel createProduct(ProductsModel productsModel) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.ProductsEntry.COLUMN_PRODUCT_ID, productsModel.getProductID());
        values.put(DBHelper.ProductsEntry.COLUMN_PRODUCT_NAME, productsModel.getProductName());
        values.put(DBHelper.ProductsEntry.COLUMN_PRODUCT_DESC, productsModel.getProductDesc());
        values.put(DBHelper.ProductsEntry.COLUMN_PRODUCT_PRICE, productsModel.getProductPrice());
        values.put(DBHelper.ProductsEntry.COLUMN_PRODUCT_IMAGE_ID, productsModel.getProductImageID());
        values.put(DBHelper.ProductsEntry.COLUMN_CATEGORY_NAME, productsModel.getProductCategoryName());
        values.put(DBHelper.ProductsEntry.COLUMN_COUNTRY_NAME, productsModel.getProductCountryName());
        values.put(DBHelper.ProductsEntry.COLUMN_PRODUCT_CODE, productsModel.getProductCode());

        long insertId = database.insert(DBHelper.ProductsEntry.TABLE_NAME, null, values);

        Cursor cursor = database.query(DBHelper.ProductsEntry.TABLE_NAME,
                productColumns, DBHelper.ProductsEntry.COLUMN_PRODUCT_ID + " = " + insertId,
                null, null, null, null);
        ((Cursor) cursor).moveToFirst();
        ProductsModel productDataFromCursor = getProductDataFromCursor(cursor);
        cursor.close();

        return productDataFromCursor;
    }

    public List<ProductsModel> getAllProducts() {
        List<ProductsModel> productModelList = new ArrayList<ProductsModel>();

        Cursor cursor = database.query(DBHelper.ProductsEntry.TABLE_NAME,
                productColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ProductsModel productModel = getProductDataFromCursor(cursor);
            productModelList.add(productModel);
            cursor.moveToNext();
        }

        cursor.close();
        return productModelList;
    }

    private ProductsModel getProductDataFromCursor(Cursor cursor) {
        return new ProductsModel(cursor.getInt(0), cursor.getString(1),
                cursor.getString(2), cursor.getInt(3),
                cursor.getString(4), cursor.getString(5), cursor.getString(6),
                cursor.getString(7));
    }

    public boolean isAppRunningFirstTime() {
        boolean isFirst = mContext.getSharedPreferences(SHARED_PREF_BEVERAGE, Context.MODE_PRIVATE)
                .getBoolean(IS_FIRST_TIME, true);

        if (isFirst) {
            mContext.getSharedPreferences(SHARED_PREF_BEVERAGE, Context.MODE_PRIVATE).edit()
                    .putBoolean(IS_FIRST_TIME, false).apply();
        }

        return isFirst;
    }
}
