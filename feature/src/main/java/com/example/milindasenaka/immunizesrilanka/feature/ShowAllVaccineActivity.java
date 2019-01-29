package com.example.milindasenaka.immunizesrilanka.feature;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ShowAllVaccineActivity extends AppCompatActivity
{
    ListView VaccineListView;
    ProgressBar progressBar;
    String HttpUrl = "http://192.168.43.128/VaccineDetails/AllVaccineData.php";
    List<String> IdList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_vaccines);

        VaccineListView = (ListView)findViewById(R.id.listview1);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        new ShowAllVaccineActivity.GetHttpResponse(ShowAllVaccineActivity.this).execute();

        VaccineListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(ShowAllVaccineActivity.this,SingleVaccineDetails.class);
                intent.putExtra("ListViewValue", IdList.get(position).toString());
                startActivity(intent);
                finish();
            }
        });
    }

    private class GetHttpResponse extends AsyncTask<Void, Void, Void>
    {
        public Context context;
        String JSonResult;

        List<Vaccine> vaccineList;

        public GetHttpResponse(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            HttpServicesClass httpServicesClass = new HttpServicesClass(HttpUrl);
            try
            {
                httpServicesClass.ExecutePostRequest();

                if(httpServicesClass.getResponseCode() == 200)
                {
                    JSonResult = httpServicesClass.getResponse();

                    if(JSonResult != null)
                    {
                        JSONArray jsonArray = null;

                        try {
                            jsonArray = new JSONArray(JSonResult);
                            JSONObject jsonObject;
                            Vaccine vaccine;

                            vaccineList = new ArrayList<Vaccine>();

                            for(int i=0; i<jsonArray.length(); i++)
                            {
                                vaccine = new Vaccine();
                                jsonObject = jsonArray.getJSONObject(i);

                                IdList.add(jsonObject.getString("vaccineID").toString());

                                vaccine.VaccineName = jsonObject.getString("vaccineName").toString();
                                vaccineList.add(vaccine);

                            }
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
                else
                {
                    Toast.makeText(context, httpServicesClass.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)

        {
            progressBar.setVisibility(View.GONE);
            VaccineListView.setVisibility(View.VISIBLE);
            ListAdapterClass1 adapter = new ListAdapterClass1(vaccineList, context);
            VaccineListView.setAdapter(adapter);

        }
    }
}
