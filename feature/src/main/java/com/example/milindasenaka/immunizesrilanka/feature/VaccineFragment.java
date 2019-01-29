package com.example.milindasenaka.immunizesrilanka.feature;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class VaccineFragment extends Fragment
{
    ListView listView;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        ((HomeActivity)getActivity()).setActionBarTitle("Vaccine Type");
        View view = inflater.inflate(R.layout.activity_type,container,false);

        listView = (ListView) view.findViewById(R.id.listview);
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.Vaccines));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(getActivity(), VaccineDetails.class);
                intent.putExtra("VaccineName", listView.getItemAtPosition(position).toString());
                startActivity(intent);

            }
        });
        listView.setAdapter(mAdapter);

        return view;

    }
}
