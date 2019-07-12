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
import java.util.Iterator;
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
        GridView orderList =  (GridView) view.findViewById(R.id.grid);
        List<OrderModel> list1 = new ArrayList<>();
        list1 = database.getAllOrders();

        System.out.println("list item"+list1);
        Iterator i = list1.iterator();
        while (i.hasNext())
        {
            System.out.println("ITEM IN LIST"+i.next());

        }



        orderList.setAdapter(new GridItemOrders(getActivity(), list1));





        //orderList.setOnClickListener();

        /*orderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adaptor, View v, int listIndex, long arg3) {
                System.out.println("Inside on item click");
                MyOrdersFragment fragment = new MyOrdersFragment();


                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(((ViewGroup)(getView().getParent())).getId()
                        , fragment, "New My order fragment");
                ft.addToBackStack(null);
                ft.commit();
            }
        });*/

        return view;
    }

}

