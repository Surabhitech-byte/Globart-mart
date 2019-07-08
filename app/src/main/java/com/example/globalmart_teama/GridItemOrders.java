package com.example.globalmart_teama;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.globalmart_teama.db.OrderModel;
//import com.example.globalmart_teama.db.ProductsModel;

import java.util.List;

public class GridItemOrders extends BaseAdapter {

    private List<OrderModel> mOrderModelList;
    private boolean isBeverage;
    private Activity mActivity;
    PopupWindow popupWindow;

    public GridItemOrders(Activity activity, List<OrderModel> mOrderModelList, boolean isBeverage) {
        this.mActivity = activity;
        this.mOrderModelList = mOrderModelList;
        this.isBeverage = isBeverage;
    }


    public GridItemOrders(Activity activity, List<OrderModel> mOrderModelList) {
        this.mActivity = activity;
        this.mOrderModelList = mOrderModelList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        // return (mIsStudent ? mStudentModelList.size() : mCourseModelList.size());
        return mOrderModelList.size();
    }


    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
//        return (mIsStudent ? mStudentModelList.get(position) : mCourseModelList.get(position));
        return mOrderModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.grid_item_orders, null);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.txt_order_id);
        TextView txt_Product_id = (TextView) convertView.findViewById(R.id.txt_product_id);
        TextView txt_quantity = (TextView) convertView.findViewById(R.id.txt_quantity);
        Button cancelBtn = convertView.findViewById(R.id.btn_cancelOrder);
        //TextView textView = (TextView) convertView.findViewById(R.id.txt_order_id);

        //System.out.println("positions created :"+mOrderModelList.get(position));
       // System.out.println("value of id"+mOrderModelList.get(position).OrderID);



        final LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.linear_layout);
        //textView.setText(list.get(position));
        textView.setText(mOrderModelList.get(position).OrderID+"");
        txt_Product_id.setText(mOrderModelList.get(position).ProductID+"");
        //textView.setText(mOrderModelList.get(position).CustomerID+"");
        txt_quantity.setText(mOrderModelList.get(position).Quantity+"");

       // Button btn = addButton();

       // Button button = new Button(mActivity);


       /* LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(params);*/
        //button.setText("Cancel the Order");

        //linearLayout.addView(button);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {






                LayoutInflater layoutInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                View popUpView = layoutInflater.inflate(R.layout.popup_window_cancel_order_success, null);


                Button closePopupBtn = (Button) popUpView.findViewById(R.id.closePopupBtn);

                popupWindow = new PopupWindow(popUpView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

                popupWindow.showAtLocation(linearLayout, Gravity.CENTER, 0, 0);

                closePopupBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });



            }
        });


        return convertView;

    }


   /* private Button addButton() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        Button button = new Button(mActivity);
        button.setLayoutParams(params);
        button.setText("Cancel Order");
        return button;
    }
*/



}
