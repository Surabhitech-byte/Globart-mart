package com.example.globalmart_teama;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.globalmart_teama.db.ProductsModel;

import java.util.List;

public class GridItem extends BaseAdapter {
    private List<ProductsModel> mBeverageModelList;
    private boolean isBeverage;
    private Activity mActivity;

    public GridItem(Activity activity, List<ProductsModel> mBeverageModelList, boolean isBeverage) {
        this.mActivity = activity;
        this.mBeverageModelList = mBeverageModelList;
        this.isBeverage = isBeverage;
    }


    public GridItem(Activity activity, List<ProductsModel> mBeverageModelList) {
        this.mActivity = activity;
        this.mBeverageModelList = mBeverageModelList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        // return (mIsStudent ? mStudentModelList.size() : mCourseModelList.size());
        return mBeverageModelList.size();
    }


    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
//        return (mIsStudent ? mStudentModelList.get(position) : mCourseModelList.get(position));
        return mBeverageModelList.get(position);
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
            convertView = layoutInflater.inflate(R.layout.grid_item, null);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.txtProduct);
        textView.setText(mBeverageModelList.get(position).productName);

        System.out.println("product list : "+mBeverageModelList.get(position).productName);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imgProduct);
//        imageView.setImageResource(R.drawable.ic_launcher_background);
        String imageID = mBeverageModelList.get(position).productImageID;
        int id = mActivity.getResources().getIdentifier(imageID, "drawable", mActivity.getPackageName());
        imageView.setImageResource(id);

        return convertView;

    }
}
