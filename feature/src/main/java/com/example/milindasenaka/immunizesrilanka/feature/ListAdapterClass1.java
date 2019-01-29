package com.example.milindasenaka.immunizesrilanka.feature;

import android.content.Context;
import java.util.List;
import android.app.Activity;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListAdapterClass1 extends BaseAdapter
{

    Context context;
    List<Vaccine> valueList1;


    public ListAdapterClass1(List<Vaccine> listValue1, Context context)
    {
        this.context = context;
        this.valueList1 = listValue1;
    }

    @Override
    public int getCount()
    {
        return this.valueList1.size();
    }

    @Override
    public Object getItem(int position)
    {
        return this.valueList1.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewItem1 viewItem1 = null;

        if(convertView == null)
        {
            viewItem1 = new ViewItem1();
            LayoutInflater layoutInflater = (LayoutInflater)this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.listview_item1, null);
            viewItem1.TextViewVaccineName = (TextView)convertView.findViewById(R.id.textView1);
            convertView.setTag(viewItem1);
        }
        else
        {
            viewItem1 = (ViewItem1) convertView.getTag();
        }

        viewItem1.TextViewVaccineName.setText(valueList1.get(position).VaccineName);
        return convertView;
    }
}

class ViewItem1
{
    TextView TextViewVaccineName;
}
