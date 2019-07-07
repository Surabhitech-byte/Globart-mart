package com.example.globalmart_teama;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.globalmart_teama.db.ProductsModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MyCartGridItem extends BaseAdapter {

    private List<ProductsModel> cartList = new ArrayList<>();
    private Activity mActivity;
    CartHolder holder;

    public MyCartGridItem(Activity activity, List<ProductsModel> cartList) {
        this.mActivity = activity;
        this.cartList = cartList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        // return (mIsStudent ? mStudentModelList.size() : mCourseModelList.size());
        return cartList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
//        return (mIsStudent ? mStudentModelList.get(position) : mCourseModelList.get(position));
        return cartList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.grid_item_mycart, null);

            holder = new CartHolder();
            holder.txtProductName = (TextView) convertView.findViewById(R.id.txtProduct);
            holder.lblQuantity = (TextView) convertView.findViewById(R.id.txtQuantity);
            holder.btnMinus = (ImageView) convertView.findViewById(R.id.btnminus);
            holder.btnPlus = (ImageView) convertView.findViewById(R.id.btnplus);
            convertView.setTag(holder);
        } else {
            holder = (CartHolder) convertView.getTag();
        }

        holder.btnPlus.setTag(position);
        holder.btnMinus.setTag(position);
        holder.lblQuantity.setTag(position);
        holder.txtProductName.setTag(position);

        // product name
        TextView textView = (TextView) convertView.findViewById(R.id.txtProduct);
        textView.setText(cartList.get(position).productName);

        // product image
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imgProduct);
        String imageID = cartList.get(position).productImageID;
        int id = mActivity.getResources().getIdentifier(imageID, "drawable", mActivity.getPackageName());
        imageView.setImageResource(id);

        // product price
        TextView textViewPrice = (TextView) convertView.findViewById(R.id.txtCartPrice);
        textViewPrice.setText("$" + cartList.get(position).productPrice);

        // product quantity
        TextView textViewQty = (TextView) convertView.findViewById(R.id.txtQuantity);
        textViewQty.setText("" + cartList.get(position).getProductCartQuantity());

        ProductsModel pd = cartList.get(position);
        final int qty = pd.getProductCartQuantity();

        // get products from shared preferences
        final SharedPreferences shref = mActivity.getSharedPreferences("CARTLIST", Context.MODE_PRIVATE);
        final Gson gson = new Gson();
        String response = shref.getString("CARTLIST", "");
        final ArrayList<ProductsModel> lstArrayList = gson.fromJson(response,
                new TypeToken<List<ProductsModel>>() {
                }.getType());

        // Minus click
        holder.btnMinus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ((GridView)parent).performItemClick(v,position,0);
            }
        });

        holder.btnPlus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ((GridView)parent).performItemClick(v,position,0);
            }
        });

        return convertView;

    }

    static class CartHolder {
        TextView txtProductName;
        TextView lblQuantity;
        ImageView btnPlus;
        ImageView btnMinus;
    }

}