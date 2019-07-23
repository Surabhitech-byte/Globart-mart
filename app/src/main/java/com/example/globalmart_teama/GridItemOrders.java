package com.example.globalmart_teama;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.globalmart_teama.db.DBHelper;
import com.example.globalmart_teama.db.DataQueries;
import com.example.globalmart_teama.db.OrderModel;
import com.example.globalmart_teama.db.ProductsModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
//import com.example.globalmart_teama.db.ProductsModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridItemOrders extends BaseAdapter {

    HashMap<String, List<OrderModel>> orderMap = new HashMap<>();
    private boolean isBeverage;
    private Activity mActivity;
    PopupWindow popupWindow;
    int counter = 0;

    public GridItemOrders(Activity activity, HashMap<String, List<OrderModel>> orderMap) {
        this.mActivity = activity;
        this.orderMap = orderMap;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        // return (mIsStudent ? mStudentModelList.size() : mCourseModelList.size());
        return orderMap.size();
    }


    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
//        return (mIsStudent ? mStudentModelList.get(position) : mCourseModelList.get(position));
        return orderMap.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.grid_item_orders, null);
        }

        if (counter >= 1) {
            counter = 0;
            return convertView;
        }
        if (position == 0) {
            counter++;
        }

        final ImageView cancelBtn = convertView.findViewById(R.id.btnGridCancel);
        final ImageView trackOrderBtn = convertView.findViewById(R.id.btnGridTrack);
        final LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.linearLayout);
        final TextView txt_confirmation_cancelOrder = (TextView) convertView.findViewById(R.id.txtOrderCancelConfirm);
        LinearLayout lProductLayout = (LinearLayout) convertView.findViewById(R.id.linearProductLayout);
        final LinearLayout linearHeaderLayout = (LinearLayout) convertView.findViewById(R.id.linearHeaderLayout);
        linearHeaderLayout.setOrientation(LinearLayout.VERTICAL);

        String orderId = orderMap.keySet().toArray()[position].toString();
        double totalPrice = 0.0;
        List<OrderModel> productsList = (ArrayList<OrderModel>) orderMap.values().toArray()[position];
        for (OrderModel product : productsList) {
            LinearLayout lProductrow = new LinearLayout(parent.getContext());
            lProductrow.setOrientation(LinearLayout.HORIZONTAL);

            LinearLayout lImageLayout = new LinearLayout(parent.getContext());
            lImageLayout.setOrientation(LinearLayout.VERTICAL);

            LinearLayout lProductDetails = new LinearLayout(parent.getContext());
            lProductDetails.setOrientation(LinearLayout.VERTICAL);

            String imgId = product.getProductImageID();
            String pName = product.getProductName();
            int pQty = product.getQuantity();
            double unitPrice = product.getUnitPrice();
            totalPrice = product.getTotalPrice();

            //set product image
            ImageView imgProduct = new ImageView(parent.getContext());
            LayoutParams imgProductParams = new LayoutParams(300, 300);
            imgProduct.setLayoutParams(imgProductParams);
            int id = mActivity.getResources().getIdentifier(imgId, "drawable", mActivity.getPackageName());
            imgProduct.setImageResource(id);
            lImageLayout.addView(imgProduct);

            //set product name
            TextView txtProductName = new TextView(parent.getContext());
            txtProductName.setText(pName);
            txtProductName.setTextSize(18);
            lProductDetails.addView(txtProductName);

            //set product quantity
            TextView txtProductQty = new TextView(parent.getContext());
            String txtQty = pQty + "";
            txtProductQty.setText("Quantity: " + txtQty);
            txtProductQty.setTextSize(18);
            lProductDetails.addView(txtProductQty);

            //set product unit price
            TextView txtProductUnitPrice = new TextView(parent.getContext());
            String txtUnitPrice = unitPrice + "";
            txtProductUnitPrice.setText("$" + txtUnitPrice);
            txtProductUnitPrice.setTextSize(18);
            lProductDetails.addView(txtProductUnitPrice);

            // add image and details to the row
            lProductrow.addView(lImageLayout);
            lProductrow.addView(lProductDetails);

            // add row to the grid
            lProductLayout.addView(lProductrow);
        }


        // set order id
        final TextView txtGridOrderID = new TextView(parent.getContext());
        txtGridOrderID.setText("Order ID: " + orderId);
        txtGridOrderID.setTextSize(24);
        txtGridOrderID.setTypeface(null, Typeface.BOLD);
        linearHeaderLayout.addView(txtGridOrderID);

        // set total price
        final TextView txtTotalPrice = new TextView(parent.getContext());
        String txtTotal = totalPrice + "";
        txtTotalPrice.setText("Total Price: $" + txtTotal);
        txtTotalPrice.setTextSize(24);
        linearHeaderLayout.addView(txtTotalPrice);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper dbQuery = new DBHelper(mActivity);

                dbQuery.deleteOrder(orderMap.keySet().toArray()[position].toString());

                LayoutInflater layoutInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                View popUpView = layoutInflater.inflate(R.layout.popup_window_cancel_order_success, null);

                Button closePopupBtn = (Button) popUpView.findViewById(R.id.closePopupBtn);

                popupWindow = new PopupWindow(popUpView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

                popupWindow.showAtLocation(linearLayout, Gravity.CENTER, 0, 0);

                closePopupBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        txt_confirmation_cancelOrder.setVisibility(View.VISIBLE);
                        cancelBtn.setVisibility(View.INVISIBLE);
                        trackOrderBtn.setVisibility(View.INVISIBLE);

                        popupWindow.dismiss();
                    }
                });
            }
        });

        trackOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View popUpView = layoutInflater.inflate(R.layout.popup_window_track_order, null);

                Button closePopupBtn = (Button) popUpView.findViewById(R.id.closePopupBtnTrackOrder);

                popupWindow = new PopupWindow(popUpView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

                popupWindow.showAtLocation(linearLayout, Gravity.CENTER, 0, 0);

                closePopupBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        popupWindow.dismiss();
                    }
                });









                // code
            }
        });

        return convertView;
    }
}