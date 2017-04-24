package com.example.vilma.fgcuhousing.data;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.vilma.fgcuhousing.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vilma on 4/24/2017.
 */

public class RaAdapter extends ArrayAdapter<RAItem> {
    private Activity activity;
    int id;
    ArrayList<RAItem> items;

    public RaAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull ArrayList<RAItem> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.id=resource;
        this.items = objects;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater=activity.getLayoutInflater();
            convertView=inflater.inflate(id,null);
        }
        RAItem item=items.get(position);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tvRAName);
        TextView tv_rating = (TextView) convertView.findViewById(R.id.tvRatingScore);

        tv_name.setText(item.getRaName());
        tv_rating.setText(item.getRating());

        return convertView;
    }
}
