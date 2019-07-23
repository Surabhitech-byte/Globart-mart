package com.example.globalmart_teama;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.GridView;
import android.widget.TextView;
import com.example.globalmart_teama.db.Database;
import com.example.globalmart_teama.db.ProductsModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyCartFragment extends Fragment {

    SharedPreferences shref;
    Gson gson;
    SharedPreferences.Editor editor;
    double totalPrice;

    public MyCartFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_my_cart, container, false);

        ArrayList<ProductsModel> lstArrayList = null;
        GridView productsOrderedGrid = (GridView) view.findViewById(R.id.cartGrid);
        TextView txtTotalPrice = (TextView) view.findViewById(R.id.txtTotalPrice);
        TextView lblTotalPrice = (TextView) view.findViewById(R.id.lblTotal);
        ImageView btnPayment = (ImageView) view.findViewById(R.id.btnPayment);

        // get products from shared preferences
        shref = getActivity().getSharedPreferences("CARTLIST", Context.MODE_PRIVATE);
        gson = new Gson();
        lstArrayList = gson.fromJson(shref.getString("CARTLIST", ""),
                new TypeToken<List<ProductsModel>>() {
                }.getType());

        if (lstArrayList == null) {
            lstArrayList = new ArrayList<>();
        }

        if (lstArrayList.size() != 0) {
            // set the data to grid
            productsOrderedGrid.setAdapter(new MyCartGridItem(getActivity(), lstArrayList));

            //print total price
            int price = 0;
            for (ProductsModel p : lstArrayList) {
                price += p.getProductPrice() * p.getProductCartQuantity();
            }
            totalPrice = price + (0.15 * price);
            TextView txtPrice = (TextView) view.findViewById(R.id.txtTotalPrice);
            txtPrice.setText("$" + totalPrice);

            //set visibility of labels and button
            txtTotalPrice.setVisibility(View.VISIBLE);
            lblTotalPrice.setVisibility(View.VISIBLE);
            btnPayment.setVisibility(View.VISIBLE);
        } else {
            txtTotalPrice.setVisibility(View.INVISIBLE);
            lblTotalPrice.setVisibility(View.INVISIBLE);
            btnPayment.setVisibility(View.INVISIBLE);
        }

//        // Plus/minus click handler
//        productsOrderedGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adaptor, View v, int listIndex, long arg3) {
//                View vi = (View) adaptor.getItemAtPosition(listIndex);
//                TextView txtName = adaptor.getSelectedView().findViewById(R.id.txtProduct);
//                TextView txtPrice = view.findViewById(R.id.txtTotalPrice);
////
//                TextView txtQty = adaptor.getChildAt(listIndex).findViewById(R.id.txtQuantity);
////                TextView txtName = adaptor.getChildAt(listIndex).findViewById(R.id.txtProduct);
////                TextView txtPrice = view.findViewById(R.id.txtTotalPrice);
//
//                switch (v.getId()) {
//                    case R.id.btnplus:
//                        // change quantity
//                        int q = Integer.parseInt(txtQty.getText().toString()) + 1;
//                        String txt = q + "";
//                        txtQty.setText(txt);
//
//                        //get shared preferences and update quantities
//                        shref = getActivity().getSharedPreferences("CARTLIST", Context.MODE_PRIVATE);
//                        gson = new Gson();
//                        ArrayList<ProductsModel> lst = gson.fromJson(shref.getString("CARTLIST", ""),
//                                new TypeToken<List<ProductsModel>>() {
//                                }.getType());
//
//                        for (ProductsModel p : lst) {
//                            if (p.getProductName().equals(txtName.getText().toString())) {
//                                p.setProductCartQuantity(p.getProductCartQuantity() + 1);
//                                break;
//                            }
//                        }
//
//                        //put it back
//                        editor = shref.edit();
//                        editor.remove("CARTLIST").commit();
//                        editor.putString("CARTLIST", gson.toJson(lst)).commit();
//
//                        //update total price
//                        int price = 0;
//                        for (ProductsModel p : lst) {
//                            price += p.getProductPrice() * p.getProductCartQuantity();
//                        }
//                        double totalPrice = price + (0.15 * price);
//                        txtPrice.setText("$" + totalPrice);
//
//                        break;
//                    case R.id.btnminus:
//                        if (!txtQty.getText().toString().equals("0")) {
//
//                            //change quantity
//                            int q2 = Integer.parseInt(txtQty.getText().toString()) - 1;
//                            String txt2 = q2 + "";
//                            txtQty.setText(txt2);
//
//                            //get shared preferences and update quantities
//                            shref = getActivity().getSharedPreferences("CARTLIST", Context.MODE_PRIVATE);
//                            gson = new Gson();
//                            ArrayList<ProductsModel> lst2 = gson.fromJson(shref.getString("CARTLIST", ""),
//                                    new TypeToken<List<ProductsModel>>() {
//                                    }.getType());
//
//                            for (ProductsModel p : lst2) {
//                                if (p.getProductName().equals(txtName.getText().toString())) {
//                                    if (p.getProductCartQuantity() != 0) {
//                                        p.setProductCartQuantity(p.getProductCartQuantity() - 1);
//                                    }
//                                    break;
//                                }
//                            }
//
//                            //put it back
//                            editor = shref.edit();
//                            editor.remove("CARTLIST").commit();
//                            editor.putString("CARTLIST", gson.toJson(lst2)).commit();
//
//                            //update total price
//                            int price2 = 0;
//                            for (ProductsModel p : lst2) {
//                                price2 += p.getProductPrice() * p.getProductCartQuantity();
//                            }
//                            double totalPrice2 = price2 + (0.15 * price2);
//                            txtPrice.setText("$" + totalPrice2);
//                        }
//                        break;
//                }
//
//            }
//        });

        // "proceed to payment" button click handler
        btnPayment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String orderID = generateOrderID();

                updateOrdersTable(orderID);

                clearCart();

                Bundle detailsBundle = new Bundle();
                detailsBundle.putString("orderID", orderID);
                PaymentConfirmationFragment fragment = new PaymentConfirmationFragment();
                fragment.setArguments(detailsBundle);

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(((ViewGroup) (getView().getParent())).getId()
                        , fragment, "PAYMENT");
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return view;
    }

    private void clearCart() {
        SharedPreferences preferences = getActivity().getSharedPreferences("CARTLIST", Context.MODE_PRIVATE);
        preferences.edit().clear().commit();
    }

    private void updateOrdersTable(String orderID) {
        final Database database = new Database(getActivity());
        shref = getActivity().getSharedPreferences("CARTLIST", Context.MODE_PRIVATE);
        gson = new Gson();
        String response = shref.getString("CARTLIST", "");
        List<ProductsModel> lstArrayList = gson.fromJson(response,
                new TypeToken<List<ProductsModel>>() {
                }.getType());

        for (ProductsModel product : lstArrayList) {
            int pID = product.getProductID();
            String pName = product.getProductName();
            String pImgId = product.getProductImageID();
            int pUnitPrice = product.getProductPrice();
            int pQty = product.getProductCartQuantity();

            database.createOrder(orderID, pID, pName, pImgId, pUnitPrice, pQty, totalPrice, 123);
        }
    }

    private String generateOrderID() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();
        while (sb.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * chars.length());
            sb.append(chars.charAt(index));
        }
        String orderID = sb.toString();
        return orderID;
    }


}
