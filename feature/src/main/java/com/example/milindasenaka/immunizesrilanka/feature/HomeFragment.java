package com.example.milindasenaka.immunizesrilanka.feature;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class HomeFragment extends Fragment
{
    LinearLayout linear1,linear2,linear3,linear4,linear5,linear6;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        ((HomeActivity)getActivity()).setActionBarTitle("Home");
        View view=inflater.inflate(R.layout.activity_home,container,false);

        linear1=(LinearLayout) view.findViewById(R.id.linearlayout1);
        linear1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                LayoutInflater inflater1 = LayoutInflater.from(getActivity());
                View view1 = inflater1.inflate(R.layout.alert_dialog_child, null);

                Button button1 = view1.findViewById(R.id.button1);
                Button button2 = view1.findViewById(R.id.button2);

                button1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(getActivity(),ShowAllChildActivity.class);
                        startActivity(intent);
                    }
                });
                button2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent=new Intent(getActivity(), ChildPage.class);
                        startActivity(intent);
                    }
                });

                AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                        .setView(view1)
                        .create();
                alertDialog.show();
            }
        });


        linear2=(LinearLayout) view.findViewById(R.id.linearlayout2);
        linear2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getActivity(), MasterPage.class);
                startActivity(intent);
            }
        });


        linear3=(LinearLayout) view.findViewById(R.id.linearlayout3);
        linear3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                LayoutInflater inflater1 = LayoutInflater.from(getActivity());
                View view1 = inflater1.inflate(R.layout.alert_dialog, null);

                Button button1 = view1.findViewById(R.id.button1);
                Button button2 = view1.findViewById(R.id.button2);

                button1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(getActivity(),ShowAllVaccineActivity.class);
                        startActivity(intent);

                    }
                });
                button2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent=new Intent(getActivity(), InventoryPage.class);
                        startActivity(intent);
                    }
                });

                AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                        .setView(view1)
                        .create();
                alertDialog.show();
            }
        });

        linear4=(LinearLayout) view.findViewById(R.id.linearlayout4);
        linear4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getActivity(), ImmunizationPage.class);
                startActivity(intent);
            }
        });

        linear5=(LinearLayout) view.findViewById(R.id.linearlayout5);
        linear5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getActivity(), AggregationPage.class);
                startActivity(intent);
            }
        });

        linear6=(LinearLayout) view.findViewById(R.id.linearlayout6);
        linear6.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getActivity(), ReportsPage.class);
                startActivity(intent);
            }
        });

       return view;

    }

}
