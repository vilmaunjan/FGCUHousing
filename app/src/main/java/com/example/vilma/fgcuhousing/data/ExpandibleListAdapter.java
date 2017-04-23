package com.example.vilma.fgcuhousing.data;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.vilma.fgcuhousing.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Vilma on 4/22/2017.
 */

public class ExpandibleListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listDataHandler;
    private HashMap<String,List<String>> listHashMap;

    public ExpandibleListAdapter(Context context, List<String> listDataHandler, HashMap<String, List<String>> listHashMap) {
        this.context = context;
        this.listDataHandler = listDataHandler;
        this.listHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return listDataHandler.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listHashMap.get(listDataHandler.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listDataHandler.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listHashMap.get(listDataHandler.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String)getGroup(groupPosition);
       // String avgRating = (String)getGroup(groupPosition);
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             convertView = inflater.inflate(R.layout.list_group,null);
        }
        TextView lblListHeader = (TextView)convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

       // TextView lblAvgRating = (TextView)convertView.findViewById(R.id.lblAvgRating);
       // lblAvgRating.setTypeface(null, Typeface.BOLD);
       // lblAvgRating.setText(avgRating);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String)getChild(groupPosition,childPosition);
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group_item,null);
        }

        TextView txtListChild = (TextView)convertView.findViewById(R.id.lblListItem);
        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
