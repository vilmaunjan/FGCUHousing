package com.example.vilma.fgcuhousing.data;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vilma.fgcuhousing.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vilma on 4/14/2017.
 */

public class EventAdapter extends ArrayAdapter<EventItem> {
    private Activity activity;
    int id;
    ArrayList<EventItem> items;

    public EventAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull ArrayList<EventItem> objects) {
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
        EventItem item=items.get(position);
        TextView tv_id = (TextView) convertView.findViewById(R.id.tv_id);
        TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title);
        TextView tv_date_time = (TextView) convertView.findViewById(R.id.tv_date_time);
        ImageView iv_image = (ImageView) convertView.findViewById(R.id.iv_image);

        tv_id.setText(item.getId());
        tv_title.setText(item.getTitle());
        tv_date_time.setText("When: "+item.getDate()+"       at: "+item.getTime());
        iv_image.setImageResource(Integer.parseInt(item.getPoster()));


        return convertView;
    }
}
