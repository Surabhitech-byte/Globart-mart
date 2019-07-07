package com.example.globalmart_teama;


import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;


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
}
