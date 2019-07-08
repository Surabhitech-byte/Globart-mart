package com.example.globalmart_teama;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShopByCategoryHomeFragment extends Fragment {


    public ShopByCategoryHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_shop_by_category, container, false);

        Button myButton = (Button) view.findViewById(R.id.btnBeverage);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnBeverage:
                        //what to put here
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(((ViewGroup)(getView().getParent())).getId()
                                , new BeveragesCategoryFragment(), "BEVERAGES");
                        ft.addToBackStack(null);
                        ft.commit();
                        break;
                }
            }


        });

        return view;
    }


}
