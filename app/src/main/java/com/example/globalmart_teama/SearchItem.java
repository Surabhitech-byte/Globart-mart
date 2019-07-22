package com.example.globalmart_teama;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;

import com.example.globalmart_teama.db.Database;
import com.example.globalmart_teama.db.ProductsModel;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;


public class SearchItem extends Fragment {

    List<ProductsModel> df = null;


    public SearchItem() {
        // Required empty public constructor
    }

    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Button scanBtn;

    private Activity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search_item, container, false);

        searchView = (SearchView) view.findViewById(R.id.searchView);
        listView = (ListView) view.findViewById(R.id.listview);

        scanBtn = (Button) view.findViewById(R.id.scan_button);

        mActivity = getActivity();
        final Database database = new Database(mActivity);
        df = database.getProductsModels();

        list = new ArrayList<>();
        for (ProductsModel item : df) {
            list.add(item.getProductName());
        }

        adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1, list);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                int counter = 0;
                for (String name : list) {
                    if (name.toLowerCase().contains(query.toLowerCase())) {
                        adapter.getFilter().filter(query);
                        listView.setAdapter(adapter);
                        counter = 1;
                    }
                }
                if (counter == 0) {
                    Toast.makeText(getContext(), "No Match found", Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //    adapter.getFilter().filter(newText);
                return false;
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adaptor, View v, int listIndex, long arg3) {

                Bundle detailsBundle = new Bundle();

                ProductsModel currProduct = null;

                for (ProductsModel name : df) {
                    if ((name.getProductName().toLowerCase()).equals(adapter.getItem(0).toLowerCase())) {
                        currProduct = name;

                    }
                }

                detailsBundle.putInt("productID", currProduct.getProductID());
                detailsBundle.putString("productName", currProduct.getProductName());
                detailsBundle.putString("productDesc", currProduct.getProductDesc());
                detailsBundle.putInt("productPrice", currProduct.getProductPrice());
                detailsBundle.putString("productImageId", currProduct.getProductImageID());

                ProductDetailsFragment fragment = new ProductDetailsFragment();
                fragment.setArguments(detailsBundle);

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(((ViewGroup) (getView().getParent())).getId()
                        , fragment, "PRODUCT DESCRIPTION");
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator scanIntegrator = new IntentIntegrator(getActivity());
                scanIntegrator.initiateScan();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
