package com.example.globalmart_teama;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.globalmart_teama.db.Database;
import com.example.globalmart_teama.db.ProductsModel;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;


public class    MenuActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle togglelayout;
    private NavigationView navigationView;
    private FragmentTransaction fragmentTransaction;
    private BottomNavigationView bottomView;
    private FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_header_id);
        togglelayout = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(togglelayout);
        togglelayout.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bottomView = findViewById(R.id.bottom_nav);
        navigationView = findViewById(R.id.navigationView_id);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.my_cart_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new MyCartFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("My Cart");
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.my_orders_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new MyOrdersFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("My Orders");
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.need_help_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new NeedHelpFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Help & Support");
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.feedback_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new FeedbackFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Recommendations");
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.purchase_history_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new PurchaseHistory());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Purchase History");
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),"default case",Toast.LENGTH_SHORT);

                }

                return true;
            }
        });

        bottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId())
                {
                    case R.id.home:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new HomeFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Home");
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.search:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new SearchItem());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Search bar");
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.store_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new BrowseByStoresFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Stores");
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.categories:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new ShopByCategoryHomeFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Categories");
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;


                }
                return true;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(togglelayout.onOptionsItemSelected(item))
        {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        Log.d("hello","world");
        super.onActivityResult(requestCode, resultCode, intent);
        //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        Log.d("abc1", "hello");
        if (scanningResult != null) {
            //we have a result

            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();

            Log.d("abc", scanContent);

            final Database database = new Database(this);
            List<ProductsModel> df = null;
            df = database.getProductsModels();

            ProductsModel currBeverage = null;

            for (ProductsModel name : df) {
                if ((name.getProductCode().toLowerCase()).equals(scanContent)) {
                    currBeverage = name;

                }
            }

            int productId = currBeverage.getProductID();
            String productName = currBeverage.getProductName();
            String productDesc = currBeverage.getProductDesc();
            int productPrice = currBeverage.getProductPrice();
            String productImageId = currBeverage.getProductImageID();
            String productCategoryName = currBeverage.getProductCategoryName();
            String productCountryName = currBeverage.getProductCountryName();

            Bundle detailsBundle = new Bundle();
            detailsBundle.putString("productName", productName);
            detailsBundle.putString("beverageDesc", productDesc);
            detailsBundle.putInt("beveragePrice", productPrice);
            detailsBundle.putString("beverageImageId", productImageId);
            detailsBundle.putString("beverageCategory", productCategoryName);
            detailsBundle.putString("beverageCountryId", productCountryName);

            ProductDetailsFragment fragment = new ProductDetailsFragment();
            fragment.setArguments(detailsBundle);

            FragmentTransaction ft =  fragmentTransaction = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_container, fragment, "PRODUCT DESCRIPTION");
            ft.addToBackStack(null);
            ft.commit();

        } else {
//            Toast toast = Toast.makeText(getContext(),
//                    "No scan data received!", Toast.LENGTH_SHORT);
//            toast.show();
        }

    }
}
