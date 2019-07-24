package com.example.globalmart_teama.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private Handler mDelay = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDelay.postDelayed(new Runnable() {
            @Override
            public void run() {

                try {

                    Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }, 3000);
    }

}
