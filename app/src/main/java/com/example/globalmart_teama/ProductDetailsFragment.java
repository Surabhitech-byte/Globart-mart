package com.example.globalmart_teama;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductDetailsFragment extends Fragment {

    ImageView imgProduct;
    TextView imgDescription;
    TextView imgProductTitle;
    TextView imgProductPrice;

    public ProductDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);

        imgDescription = view.findViewById(R.id.txtDescriptiontext);
        imgProductTitle = view.findViewById(R.id.imgProductName);
        imgProductPrice = view.findViewById(R.id.imgProductPrice);
        imgProduct = view.findViewById(R.id.imgProduct);

        Bundle dataBundle = getArguments();
        String name = dataBundle.getString("productName", "N/A");
        String desc = dataBundle.getString("beverageDesc", "N/A");
        int price = dataBundle.getInt("beveragePrice", -1);
        String imgId = dataBundle.getString("beverageImageId", "N/A");

        imgProductTitle.setText(name);
        imgDescription.setText(desc);
        imgProductPrice.setText("$" + price);

        int id = this.getActivity().getResources().getIdentifier(imgId, "drawable", this.getActivity().getPackageName());
        imgProduct.setImageResource(id);

        return view;
    }

}
