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
    private List<ProductsModel> mProductModelList;
    private Activity mActivity;

    public GridItem(Activity activity, List<ProductsModel> mProductModelList) {
        this.mActivity = activity;
        this.mProductModelList = mProductModelList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        // return (mIsStudent ? mStudentModelList.size() : mCourseModelList.size());
        return mProductModelList.size();
    }


    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
//        return (mIsStudent ? mStudentModelList.get(position) : mCourseModelList.get(position));
        return mProductModelList.get(position);
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
        textView.setText(mProductModelList.get(position).getProductName());

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imgProduct);
        String imageID = mProductModelList.get(position).getProductImageID();

        int id = mActivity.getResources().getIdentifier(imageID, "drawable", mActivity.getPackageName());
        imageView.setImageResource(id);

        return convertView;

    }
}
