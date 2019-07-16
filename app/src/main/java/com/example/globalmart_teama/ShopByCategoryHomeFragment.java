package com.example.globalmart_teama;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


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
        View view = inflater.inflate(R.layout.fragment_shop_by_category, container, false);
        final ProductsCategoryFragment fragment = new ProductsCategoryFragment();
        final Bundle bundle = new Bundle();
        final FragmentManager fm = getFragmentManager();
        final FragmentTransaction ft = fm.beginTransaction();
        Button btnBeverage = (Button) view.findViewById(R.id.btnBeverage);
        Button btnGrocery = (Button) view.findViewById(R.id.btnGrocery);
        Button btnFrtAndVeg = (Button) view.findViewById(R.id.btnFrtandVeg);

        btnBeverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("CategoryType", "Beverages");
                fragment.setArguments(bundle);

                ft.replace(((ViewGroup) (getView().getParent())).getId()
                        , fragment, "Beverages");
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        btnGrocery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("CategoryType", "Groceries");
                fragment.setArguments(bundle);

                ft.replace(((ViewGroup) (getView().getParent())).getId()
                        , fragment, "Groceries");
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        btnFrtAndVeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("CategoryType", "Fruits&Vegetables");
                fragment.setArguments(bundle);

                ft.replace(((ViewGroup) (getView().getParent())).getId()
                        , fragment, "Fruits&Vegetables");
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return view;
    }


}
