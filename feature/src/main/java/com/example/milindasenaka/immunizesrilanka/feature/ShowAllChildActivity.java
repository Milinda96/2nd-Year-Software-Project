package com.example.milindasenaka.immunizesrilanka.feature;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;

public class ShowAllChildActivity extends AppCompatActivity
{

    ListView ChildListView;
    ProgressBar progressBar;
    String HttpUrl = "http://192.168.43.128/ChildDetails/AllChildData.php";
    List<String> IdList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_childs);

        ChildListView = (ListView)findViewById(R.id.listview1);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        new GetHttpResponse(ShowAllChildActivity.this).execute();

        ChildListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(ShowAllChildActivity.this,SingleChildDetails.class);
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

        List<Child> childList;

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
                            Child child;

                            childList = new ArrayList<Child>();

                            for(int i=0; i<jsonArray.length(); i++)
                            {
                                child = new Child();
                                jsonObject = jsonArray.getJSONObject(i);

                                IdList.add(jsonObject.getString("id").toString());

                                child.ChildName = jsonObject.getString("fullname").toString();
                                childList.add(child);

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
            ChildListView.setVisibility(View.VISIBLE);
            ListAdapterClass adapter = new ListAdapterClass(childList, context);
            ChildListView.setAdapter(adapter);

        }
    }
}

