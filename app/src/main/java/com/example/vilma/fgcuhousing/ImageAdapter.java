package com.example.vilma.fgcuhousing;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Vilma on 3/31/2017.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c){
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView == null) {
            //it it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams (350, 350));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(40,40,40,40);
        } else {
            imageView = (ImageView) convertView;;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    //references to awards images
    private Integer[] mThumbIds = {
            R.drawable.award1, R.drawable.award2,
            R.drawable.award3, R.drawable.award4,
            R.drawable.award5, R.drawable.award6,
            R.drawable.award7, R.drawable.award8,
            R.drawable.award9
    };
}
