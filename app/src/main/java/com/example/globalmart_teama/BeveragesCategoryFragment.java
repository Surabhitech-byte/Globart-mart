package com.example.globalmart_teama;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.globalmart_teama.db.Database;
import com.example.globalmart_teama.db.ProductsModel;

public class BeveragesCategoryFragment extends Fragment {

    GridView grid;

    public BeveragesCategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categories_beverages, container, false);

        final Database database = new Database(getActivity());
        GridView beveragesList = (GridView) view.findViewById(R.id.grid);

        beveragesList.setAdapter(new GridItem(getActivity(), database.getProductsModels()));

        beveragesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adaptor, View v, int listIndex, long arg3) {

                Bundle detailsBundle = new Bundle();

                ProductsModel currBeverage = database.getProductsModels().get(listIndex);
                int productId = currBeverage.getProductID();
                String productName = currBeverage.getProductName();
                String productDesc = currBeverage.getProductDesc();
                int productPrice = currBeverage.getProductPrice();
                String productImageId = currBeverage.getProductImageID();
                String productCategoryName = currBeverage.getProductCategoryName();
                String productCountryName = currBeverage.getProductCountryName();

                detailsBundle.putString("productName", productName);
                detailsBundle.putString("beverageDesc", productDesc);
                detailsBundle.putInt("beveragePrice", productPrice);
                detailsBundle.putString("beverageImageId", productImageId);
                detailsBundle.putString("beverageCategory", productCategoryName);
                detailsBundle.putString("beverageCountryId", productCountryName);

                ProductDetailsFragment fragment = new ProductDetailsFragment();
                fragment.setArguments(detailsBundle);

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(((ViewGroup)(getView().getParent())).getId()
                        , fragment, "PRODUCT DESCRIPTION");
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return view;
    }

}
