package com.example.globalmart_teama;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.globalmart_teama.db.Database;
import com.example.globalmart_teama.db.OrderModel;
import com.example.globalmart_teama.db.ProductsModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PurchaseHistory extends Fragment {


    public PurchaseHistory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_purchase_history, container, false);

        final Database database = new Database(getActivity());
        GridView orderGrid = (GridView) view.findViewById(R.id.grid);
        List<OrderModel> dbOrdersList = new ArrayList<>();
        dbOrdersList = database.getAllOrders();

        HashMap<String, List<OrderModel>> orderMap = new LinkedHashMap<>();

        for (OrderModel item : dbOrdersList) {
            List<OrderModel> pList = new ArrayList<>();
            pList = orderMap.get(item.getOrderID());
            OrderModel product = new OrderModel(item.getOrderID(), item.getProductID(), item.getProductName(), item.getProductImageID(),
                    item.getUnitPrice(), item.getQuantity(), item.getTotalPrice(), 0);

            if (pList != null) {
                pList.add(product);
            }
            else{
                pList = new ArrayList<>();
                pList.add(product);
            }
            orderMap.put(item.getOrderID(), pList);
        }


        orderGrid.setAdapter(new GridItemOrders(getActivity(), orderMap));

        return view;
    }

}

