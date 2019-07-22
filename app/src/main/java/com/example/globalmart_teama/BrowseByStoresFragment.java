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
public class BrowseByStoresFragment extends Fragment {


    public BrowseByStoresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_browse_by_stores, container, false);

        final ProductsStoreFragment fragment = new ProductsStoreFragment();
        final Bundle bundle = new Bundle();
        final FragmentManager fm = getFragmentManager();
        final FragmentTransaction ft = fm.beginTransaction();
        Button btnChinese = (Button) view.findViewById(R.id.btnChinese);
        Button btnIndian = (Button) view.findViewById(R.id.btnIndian);
        Button btnOthers = (Button) view.findViewById(R.id.btnOthers);

        btnChinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("StoreType", "Chinese");
                fragment.setArguments(bundle);

                ft.replace(((ViewGroup) (getView().getParent())).getId()
                        , fragment, "Chinese");
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        btnIndian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("StoreType", "Indian");
                fragment.setArguments(bundle);

                ft.replace(((ViewGroup) (getView().getParent())).getId()
                        , fragment, "Indian");
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        btnOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putString("StoreType", "Others");
                fragment.setArguments(bundle);

                ft.replace(((ViewGroup) (getView().getParent())).getId()
                        , fragment, "Others");
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return view;
    }

}
