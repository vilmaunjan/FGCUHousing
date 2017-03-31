package com.example.vilma.fgcuhousing;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CreateEvent extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner spinnerFilter = (Spinner) findViewById(R.id.spinnerLocation);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.spinner_housing_options, android.R.layout.simple_spinner_item);

        spinnerFilter.setAdapter(adapter);
        spinnerFilter.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView spinnerDialogText = (TextView) view;
        Toast.makeText(this, "you selected " + spinnerDialogText.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {    }
}
