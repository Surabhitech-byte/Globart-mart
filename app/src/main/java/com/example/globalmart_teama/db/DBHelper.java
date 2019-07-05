package com.example.globalmart_teama.db;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "globalmart.db";
    public static final int DATABASE_VERSION = 1;

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";


    public static abstract class ProductsEntry implements BaseColumns {
        public static final String TABLE_NAME = "products";
        public static final String COLUMN_PRODUCT_ID = "product_id";
        public static final String COLUMN_PRODUCT_NAME = "product_name";
        public static final String COLUMN_PRODUCT_DESC = "product_desc";
        public static final String COLUMN_PRODUCT_PRICE = "product_price";
        public static final String COLUMN_PRODUCT_IMAGE_ID = "product_image_id";
        public static final String COLUMN_COUNTRY_NAME = "product_country_name";
        public static final String COLUMN_CATEGORY_NAME = "product_category_name";
    }

    private static final String SQL_CREATE_PRODUCT_TABLE =
            "CREATE TABLE IF NOT EXISTS " + ProductsEntry.TABLE_NAME + " (" +
                    ProductsEntry.COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                    ProductsEntry.COLUMN_PRODUCT_NAME + TEXT_TYPE + COMMA_SEP +
                    ProductsEntry.COLUMN_PRODUCT_DESC + TEXT_TYPE + COMMA_SEP +
                    ProductsEntry.COLUMN_PRODUCT_PRICE + INTEGER_TYPE + COMMA_SEP +
                    ProductsEntry.COLUMN_PRODUCT_IMAGE_ID + TEXT_TYPE + COMMA_SEP +
                    ProductsEntry.COLUMN_CATEGORY_NAME + TEXT_TYPE + COMMA_SEP +
                    ProductsEntry.COLUMN_COUNTRY_NAME + TEXT_TYPE +" )";


    private static final String SQL_DELETE_PRODUCT =
            "DROP TABLE IF EXISTS " + ProductsEntry.TABLE_NAME;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PRODUCT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_PRODUCT);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }
}
