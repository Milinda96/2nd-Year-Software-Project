package com.example.milindasenaka.immunizesrilanka.feature;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;

public class SingleChildDetails extends AppCompatActivity
{

    HttpParse httpParse = new HttpParse();
    ProgressDialog pDialog;

    String HttpURL = "http://192.168.43.128/ChildDetails/FilterChildData.php";
    String HttpUrlDeleteRecord = "http://192.168.43.128/ChildDetails/DeleteChild.php";

    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();

    String ParseResult ;
    HashMap<String,String> ResultHash = new HashMap<>();

    String FinalJSonObject ;
    TextView NAME,HIN,REGNO,REGDATE,TIME,DOB,GENDER,PLACE,WEIGHT;
    String NameHolder, HINHolder, REGNOHolder, REGDATEHolder, TIMEHolder, DOBHolder, GENDERHolder, PLACEHolder, WEIGHTHolder;
    Button EditButton, DeleteButton;
    String TempItem;
    ProgressDialog progressDialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        NAME = (TextView)findViewById(R.id.tv_fname);
        HIN = (TextView)findViewById(R.id.tv_hin);
        REGNO = (TextView)findViewById(R.id.tv_regno);
        REGDATE = (TextView)findViewById(R.id.tv_regdate);
        TIME = (TextView)findViewById(R.id.tv_timeofbirth);
        DOB = (TextView)findViewById(R.id.tv_dob);
        GENDER = (TextView)findViewById(R.id.tv_gender);
        PLACE = (TextView)findViewById(R.id.tv_place);
        WEIGHT = (TextView)findViewById(R.id.tv_weight);

        EditButton = (Button)findViewById(R.id.buttonEdit);
        DeleteButton = (Button)findViewById(R.id.buttonDelete);

        TempItem = getIntent().getStringExtra("ListViewValue");
        HttpWebCall(TempItem);


        EditButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Intent intent = new Intent(SingleChildDetails.this,UpdateChildDetailsActivity.class);

                intent.putExtra("Id", TempItem);
                intent.putExtra("Name", NameHolder);
                intent.putExtra("HIN", HINHolder);
                intent.putExtra("RegNo", REGNOHolder);
                intent.putExtra("RegDate", REGDATEHolder);
                intent.putExtra("Time", TIMEHolder);
                intent.putExtra("DOB", DOBHolder);
                intent.putExtra("Gender", GENDERHolder);
                intent.putExtra("Place", PLACEHolder);
                intent.putExtra("Weight", WEIGHTHolder);

                startActivity(intent);
                finish();
            }
        });

        DeleteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ChildDelete(TempItem);
            }
        });

    }

    public void ChildDelete(final String ChildID)
    {

        class ChildDeleteClass extends AsyncTask<String, Void, String>
        {

            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                progressDialog2 = ProgressDialog.show(SingleChildDetails.this, "Loading Data", null, true, true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg)
            {

                super.onPostExecute(httpResponseMsg);
                progressDialog2.dismiss();
                Toast.makeText(SingleChildDetails.this, httpResponseMsg.toString(), Toast.LENGTH_LONG).show();
                finish();

            }

            @Override
            protected String doInBackground(String... params)
            {

                hashMap.put("ChildID", params[0]);
                finalResult = httpParse.postRequest(hashMap, HttpUrlDeleteRecord);
                return finalResult;
            }
        }

        ChildDeleteClass studentDeleteClass = new ChildDeleteClass();
        studentDeleteClass.execute(ChildID);
    }

    public void HttpWebCall(final String PreviousListViewClickedItem)
    {

        class HttpWebCallFunction extends AsyncTask<String,Void,String>
        {

            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                pDialog = ProgressDialog.show(SingleChildDetails.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg)
            {

                super.onPostExecute(httpResponseMsg);
                pDialog.dismiss();
                FinalJSonObject = httpResponseMsg ;
                new GetHttpResponse(SingleChildDetails.this).execute();

            }

            @Override
            protected String doInBackground(String... params)
            {

                ResultHash.put("ChildID",params[0]);
                ParseResult = httpParse.postRequest(ResultHash, HttpURL);
                return ParseResult;
            }
        }

        HttpWebCallFunction httpWebCallFunction = new HttpWebCallFunction();
        httpWebCallFunction.execute(PreviousListViewClickedItem);
    }

    private class GetHttpResponse extends AsyncTask<Void, Void, Void>
    {
        public Context context;
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
            try
            {
                if(FinalJSonObject != null)
                {
                    JSONArray jsonArray = null;

                    try {
                        jsonArray = new JSONArray(FinalJSonObject);
                        JSONObject jsonObject;

                        for(int i=0; i<jsonArray.length(); i++)
                        {
                            jsonObject = jsonArray.getJSONObject(i);

                            NameHolder = jsonObject.getString("fullname").toString();
                            HINHolder = jsonObject.getString("hin").toString();
                            REGNOHolder = jsonObject.getString("childregid").toString();
                            REGDATEHolder = jsonObject.getString("childregdate").toString();
                            TIMEHolder = jsonObject.getString("timeofbirth").toString();
                            DOBHolder = jsonObject.getString("dob").toString();
                            GENDERHolder = jsonObject.getString("gender").toString();
                            PLACEHolder = jsonObject.getString("place").toString();
                            WEIGHTHolder = jsonObject.getString("weight").toString();

                        }
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
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
            NAME.setText(NameHolder);
            HIN.setText(HINHolder);
            REGNO.setText(REGNOHolder);
            REGDATE.setText(REGDATEHolder);
            TIME.setText(TIMEHolder);
            DOB.setText(DOBHolder);
            GENDER.setText(GENDERHolder);
            PLACE.setText(PLACEHolder);
            WEIGHT.setText(WEIGHTHolder);
        }
    }

}