package com.example.vilma.fgcuhousing;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.vilma.fgcuhousing.data.CurrentUser;

import java.util.ArrayList;

/**
 * Created by Vilma on 3/31/2017.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private CurrentUser p;
    private  ArrayList<Integer> test;

    public ImageAdapter(Context c){
        mContext = c;
    }

    public ImageAdapter(Context c, CurrentUser p){
        mContext = c;
        this.p = p;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return mThumbIds[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            //it it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(350, 350));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(40, 40, 40, 40);
        } else {
            imageView = (ImageView) convertView;

        }
        test = new ArrayList<>();
        userSpecificAwards();//adds only the awards they have
        //imageView.setImageResource(mThumbIds[position]);
        Log.d("ImagePosition", String.valueOf(position));
        if(position < test.size()) {
            imageView.setImageResource(test.get(position));
        }else{
            imageView.setVisibility(View.INVISIBLE);
        }

        return imageView;
    }

    private void userSpecificAwards(){
        if(p.getMyawards().isFirstTime()){
            test.add(R.drawable.award1);
        }
        if(p.getMyawards().isThirdTimer()){
            test.add(R.drawable.award2);
        }
        if(p.getMyawards().isLuckySeven()){
            test.add(R.drawable.award3);
        }

        if(p.getMyawards().isOutGoing()){
            test.add(R.drawable.award4);
        }

        if(p.getMyawards().isMakeLifeSimple()){
            test.add(R.drawable.award5);
        }

        if(p.getMyawards().isTheHallPatrol()){
            test.add(R.drawable.award6);
        }

        if(p.getMyawards().isKingPin()){
            test.add(R.drawable.award7);
        }

        if(p.getMyawards().isComplicated()){
            test.add(R.drawable.award8);
        }

        if(p.getMyawards().isBetaTester()){
            test.add(R.drawable.award9);
        }
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
