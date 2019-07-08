package com.example.globalmart_teama;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.globalmart_teama.db.ProductsModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyCartFragment extends Fragment {


    public MyCartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_my_cart, container, false);

        ArrayList<ProductsModel> lstArrayList = null;

        // get products from shared preferences
        SharedPreferences shref = getActivity().getSharedPreferences("CARTLIST", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String response = shref.getString("CARTLIST", "");
        lstArrayList = gson.fromJson(response,
                new TypeToken<List<ProductsModel>>() {
                }.getType());

        if (lstArrayList == null) {
            lstArrayList = new ArrayList<>();
        }
        // set the data to grid
        GridView productsOrderedGrid = (GridView) view.findViewById(R.id.cartGrid);
        productsOrderedGrid.setAdapter(new MyCartGridItem(getActivity(), lstArrayList));

        //print total price
        int price = 0;
        for (ProductsModel p : lstArrayList) {
            price += p.getProductPrice() * p.getProductCartQuantity();
        }
        double totalPrice = price + (0.15 * price);
        TextView txtPrice = (TextView) view.findViewById(R.id.txtTotalPrice);
        txtPrice.setText("$" + totalPrice);

        productsOrderedGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adaptor, View v, int listIndex, long arg3) {
                TextView txtQty = adaptor.getChildAt(listIndex).findViewById(R.id.txtQuantity);
                TextView txtName = adaptor.getChildAt(listIndex).findViewById(R.id.txtProduct);
                TextView txtPrice = view.findViewById(R.id.txtTotalPrice);

                switch (v.getId()) {
                    case R.id.btnplus:
                        ProductsModel row = (ProductsModel) adaptor.getItemAtPosition(listIndex);

                        int q = Integer.parseInt(txtQty.getText().toString()) + 1;
                        String txt = q + "";
                        txtQty.setText(txt);

                        //update shared preferences
                        SharedPreferences shref = getActivity().getSharedPreferences("CARTLIST", Context.MODE_PRIVATE);
                        Gson gson = new Gson();
                        String response = shref.getString("CARTLIST", "");
                        ArrayList<ProductsModel> lst = gson.fromJson(response,
                                new TypeToken<List<ProductsModel>>() {
                                }.getType());

                        for (ProductsModel p : lst) {
                            if (p.getProductName().equals(txtName.getText().toString())) {
                                p.setProductCartQuantity(p.getProductCartQuantity() + 1);
                                break;
                            }
                        }

                        //put it back
                        SharedPreferences.Editor editor;
                        editor = shref.edit();
                        editor.remove("CARTLIST").commit();
                        String newJson = gson.toJson(lst);
                        editor.putString("CARTLIST", newJson);
                        editor.commit();

                        //print total price
                        int price = 0;
                        for (ProductsModel p : lst) {
                            price += p.getProductPrice() * p.getProductCartQuantity();
                        }
                        double totalPrice = price + (0.15 * price);
                        txtPrice.setText("$" + totalPrice);

                        break;
                    case R.id.btnminus:
//                        ProductsModel row = (ProductsModel) adaptor.getItemAtPosition(listIndex);

                        if (!txtQty.getText().toString().equals("0")) {
                            int q2 = Integer.parseInt(txtQty.getText().toString()) - 1;
                            String txt2 = q2 + "";
                            txtQty.setText(txt2);

                            //update shared preferences
                            SharedPreferences shref2 = getActivity().getSharedPreferences("CARTLIST", Context.MODE_PRIVATE);
                            Gson gson2 = new Gson();
                            String response2 = shref2.getString("CARTLIST", "");
                            ArrayList<ProductsModel> lst2 = gson2.fromJson(response2,
                                    new TypeToken<List<ProductsModel>>() {
                                    }.getType());

                            for (ProductsModel p : lst2) {
                                if (p.getProductName().equals(txtName.getText().toString())) {
                                    if (p.getProductCartQuantity() != 0) {
                                        p.setProductCartQuantity(p.getProductCartQuantity() - 1);
                                    }
                                    break;
                                }
                            }

                            //put it back
                            SharedPreferences.Editor editor2;
                            editor = shref2.edit();
                            editor.remove("CARTLIST").commit();
                            String newJson2 = gson2.toJson(lst2);
                            editor.putString("CARTLIST", newJson2);
                            editor.commit();

                            //print total price
                            int price2 = 0;
                            for (ProductsModel p : lst2) {
                                price2 += p.getProductPrice() * p.getProductCartQuantity();
                            }
                            double totalPrice2 = price2 + (0.15 * price2);
                            txtPrice.setText("$" + totalPrice2);
                        }
                        break;
                }

            }
        });


        Button btnPayment = (Button) view.findViewById(R.id.btnPayment);
        btnPayment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(((ViewGroup)(getView().getParent())).getId()
                        , new PaymentConfirmationFragment(), "PAYMENT");
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return view;
    }


}
