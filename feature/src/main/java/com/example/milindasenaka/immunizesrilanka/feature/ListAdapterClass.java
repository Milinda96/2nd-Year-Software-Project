package com.example.milindasenaka.immunizesrilanka.feature;

import android.content.Context;
import java.util.List;
import android.app.Activity;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListAdapterClass extends BaseAdapter {

    Context context;
    List<Child> valueList;


    public ListAdapterClass(List<Child> listValue, Context context)
    {
        this.context = context;
        this.valueList = listValue;
    }

    @Override
    public int getCount()
    {
        return this.valueList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return this.valueList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewItem viewItem = null;

        if(convertView == null)
        {
            viewItem = new ViewItem();
            LayoutInflater layoutInflater = (LayoutInflater)this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.listview_item, null);
            viewItem.TextViewChildName = (TextView)convertView.findViewById(R.id.textView1);
            convertView.setTag(viewItem);
        }
        else
        {
            viewItem = (ViewItem) convertView.getTag();
        }

        viewItem.TextViewChildName.setText(valueList.get(position).ChildName);
        return convertView;
    }

}

class ViewItem
{
    TextView TextViewChildName;

}

