package com.example.vilma.fgcuhousing;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

/**
 * Created by Andre on 4/13/2017.
 */

public class AwardInfo extends Activity{
    private TextView p;
    private String title;
    private String Description;

    AwardInfo(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_awardwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .9), (int)(height * .2));
        p = (TextView) findViewById(R.id.TitleBox);
        p.setText("Testing");

    }
}
