package com.example.globalmart_teama;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.globalmart_teama.db.DBHelper;
import com.example.globalmart_teama.db.DataQueries;
import com.example.globalmart_teama.db.OrderModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
//import com.example.globalmart_teama.db.ProductsModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GridItemOrders extends BaseAdapter {
    private static String orderid = "";
    private static ArrayList productList_toShow = new ArrayList();
    private List<OrderModel> mOrderModelList;
    private boolean isBeverage;
    private Activity mActivity;
    PopupWindow popupWindow;
    //CartHolder holder;

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
    public View getView(final int position, View convertView, final ViewGroup parent) {


        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.grid_item_orders, null);
        }

        final TextView textView = (TextView) convertView.findViewById(R.id.txt_order_id);
        TextView txt_Product_id = (TextView) convertView.findViewById(R.id.txt_product_id);
        TextView txt_quantity = (TextView) convertView.findViewById(R.id.txt_quantity);
        final TextView txt_confirmation_cancelOrder = (TextView) convertView.findViewById(R.id.txtOrderCancelConfirm);
        final Button cancelBtn = convertView.findViewById(R.id.btn_cancelOrder);
        TextView prd = (TextView) convertView.findViewById(R.id.product2);
        TextView prd2 = (TextView) convertView.findViewById(R.id.product3);
        //OrderModel positionNew = mOrderModelList.get(position) ;


        final LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.linear_layout);

        textView.setText(mOrderModelList.get(position).getOrderID()+"");
        //System.out.println("position : "+mOrderModelList.get(position));
         orderid = mOrderModelList.get(position).getOrderID();


       /* if(orderid.equals(mOrderModelList.get(position).getOrderID()))
        {
            System.out.println("multiple orderss");

        }
*/
        System.out.println("ORDER ID : "+orderid);

        if(orderid.isEmpty())
        {

            orderid = mOrderModelList.get(position).getOrderID();
            System.out.println("Appended order"+orderid);
            productList_toShow.add(mOrderModelList.get(position).getProductID());
            System.out.println("Appended product"+productList_toShow);
        }
        else
        {
            if(orderid.equals(mOrderModelList.get(position).getOrderID()))
            {
                productList_toShow.add(mOrderModelList.get(position).getProductID());
                Iterator i = productList_toShow.iterator();
                while (i.hasNext())
                {
                    TextView prodTextView = new TextView(mActivity);

                    if(prodTextView.getParent() != null) {
                        ((ViewGroup)prodTextView.getParent()).removeView(prodTextView); // <- fix
                    }
                    String s =  i.next()+"";

                    prodTextView.setText(s);
                    linearLayout.addView(textView);
                    System.out.println("Product IN LIST  : "+i.next());

                }

            }
            else{
                System.out.println("next order");

            }

        }

        txt_Product_id.setText(mOrderModelList.get(position).getProductID()+"");

        txt_quantity.setText(mOrderModelList.get(position).getQuantity()+"");

        OrderModel pd = mOrderModelList.get(position);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v) {


//                OrderModel row = (OrderModel) adaptor.getItemAtPosition(listIndex);
                System.out.println("Position clicked "+position);
                System.out.println("order at position clicked "+mOrderModelList.get(position).getOrderID());

                DBHelper dbQuery = new DBHelper(mActivity);
                System.out.println("order to be deleted"+mOrderModelList.get(position).getOrderID());
                dbQuery.deleteOrder(mOrderModelList.get(position).getOrderID());


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

  /*  static class CartHolder {
        TextView txtProductName;
        TextView lblQuantity;
        ImageView btnPlus;
        ImageView btnMinus;
    }
*/
}
