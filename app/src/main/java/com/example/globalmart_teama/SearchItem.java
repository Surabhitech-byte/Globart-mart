package com.example.globalmart_teama;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import com.example.globalmart_teama.db.Database;
import com.example.globalmart_teama.db.ProductsModel;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class SearchItem extends Fragment {

    public SearchItem() {
        // Required empty public constructor
    }

    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String > adapter;
    Button scanBtn;
    TextView formatTxt;
    TextView contentTxt;


    private Activity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search_item, container, false);

        searchView = (SearchView) view.findViewById(R.id.searchView);
        listView = (ListView) view.findViewById(R.id.listview);

        scanBtn = (Button) view.findViewById(R.id.scan_button);
        formatTxt = (TextView) view.findViewById(R.id.scan_format);
        contentTxt = (TextView) view.findViewById(R.id.scan_content);
        
        mActivity=getActivity();
        final Database database = new Database(mActivity);
        final List<ProductsModel> df=  database.getProductsModels();

        list = new ArrayList<>();
        for(ProductsModel item:df){
            list.add(item.getProductName());
        }

        adapter=new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1,list);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                int counter=0;
                for (String name: list)
                {
                    if(name.toLowerCase().contains(query.toLowerCase())) {
                        adapter.getFilter().filter(query);
                        listView.setAdapter(adapter);
                        counter=1;
                        }
                }
                if(counter==0){
                    Toast.makeText(getContext(),"No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //    adapter.getFilter().filter(newText);
                return false;
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adaptor, View v, int listIndex, long arg3) {

                Bundle detailsBundle = new Bundle();

                ProductsModel currBeverage = null;

                for(ProductsModel name:df){
                    if((name.getProductName().toLowerCase()).equals(adapter.getItem(0).toLowerCase())){
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

        // Inflate the layout for this fragment
        return view;
    }

}
