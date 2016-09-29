package com.sunghyun.andriod.basiclist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by YKC on 2016. 9. 28..
 */
public class ExpandableAdapter extends BaseExpandableListAdapter {

    Context context;
    int groutLayout;
    int childLayout;
    ArrayList<ExpandData> datas;
    LayoutInflater inflater;

    public ExpandableAdapter(Context context, int groutLayout, int childLayout,
                             ArrayList<ExpandData> datas) {
        this.context = context;
        this.groutLayout = groutLayout;
        this.childLayout = childLayout;
        this.datas = datas;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getGroupCount() {
        return datas.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return datas.get(i).guNames.size();
    }

    @Override
    public Object getGroup(int i) {
        return datas.get(i);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return datas.get(groupPosition).guNames.get(childPosition);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = inflater.inflate(groutLayout,parent,false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.text1);
        textView.setText(datas.get(groupPosition).cityName);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(childLayout,parent,false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.text2);
        textView.setText(datas.get(groupPosition).guNames.get(childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
