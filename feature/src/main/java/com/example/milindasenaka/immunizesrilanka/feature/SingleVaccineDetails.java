package com.example.milindasenaka.immunizesrilanka.feature;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SingleVaccineDetails extends AppCompatActivity {
    HttpParse httpParse = new HttpParse();
    ProgressDialog pDialog;

    String HttpURL = "http://192.168.43.128/VaccineDetails/FilterVaccineData.php";
    String HttpUrlDeleteRecord = "http://192.168.43.128/VaccineDetails/DeleteVaccine.php";

    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();

    String ParseResult ;
    HashMap<String,String> ResultHash = new HashMap<>();

    String FinalJSonObject ;
    TextView NAME,QUANTITY,REORDERLEVEL,DESC;
    String NameHolder, QuantityHolder, ReOrderLevelHolder, DescHolder;
    Button EditButton, DeleteButton;
    String TempItem;
    ProgressDialog progressDialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        NAME = (TextView)findViewById(R.id.tv_name);
        QUANTITY = (TextView)findViewById(R.id.tv_quantity);
        REORDERLEVEL = (TextView)findViewById(R.id.tv_reorderlevel);
        DESC = (TextView)findViewById(R.id.tv_desc);

        EditButton = (Button)findViewById(R.id.buttonEdit);
        DeleteButton = (Button)findViewById(R.id.buttonDelete);

        TempItem = getIntent().getStringExtra("ListViewValue");
        HttpWebCall(TempItem);


        EditButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Intent intent = new Intent(SingleVaccineDetails.this,UpdateVaccineDetailsActivity.class);

                intent.putExtra("Id", TempItem);
                intent.putExtra("Name", NameHolder);
                intent.putExtra("Quantity", QuantityHolder);
                intent.putExtra("ReOrderLevel", ReOrderLevelHolder);
                intent.putExtra("Desc", DescHolder);

                startActivity(intent);
                finish();
            }
        });

        DeleteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                VaccineDelete(TempItem);

            }
        });

    }

    public void VaccineDelete(final String VaccineID)
    {

        class VaccineDeleteClass extends AsyncTask<String, Void, String>
        {

            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                progressDialog2 = ProgressDialog.show(SingleVaccineDetails.this, "Loading Data", null, true, true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg)
            {

                super.onPostExecute(httpResponseMsg);
                progressDialog2.dismiss();
                Toast.makeText(SingleVaccineDetails.this, httpResponseMsg.toString(), Toast.LENGTH_LONG).show();
                finish();

            }

            @Override
            protected String doInBackground(String... params)
            {

                hashMap.put("VaccineID", params[0]);
                finalResult = httpParse.postRequest(hashMap, HttpUrlDeleteRecord);
                return finalResult;
            }
        }

        VaccineDeleteClass vaccineDeleteClass = new VaccineDeleteClass();
        vaccineDeleteClass.execute(VaccineID);
    }

    public void HttpWebCall(final String PreviousListViewClickedItem)
    {

        class HttpWebCallFunction extends AsyncTask<String,Void,String>
        {

            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                pDialog = ProgressDialog.show(SingleVaccineDetails.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg)
            {

                super.onPostExecute(httpResponseMsg);
                pDialog.dismiss();
                FinalJSonObject = httpResponseMsg ;
                new SingleVaccineDetails.GetHttpResponse(SingleVaccineDetails.this).execute();

            }

            @Override
            protected String doInBackground(String... params)
            {

                ResultHash.put("VaccineID",params[0]);
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

                            NameHolder = jsonObject.getString("vaccineName").toString();
                            QuantityHolder = jsonObject.getString("availableQuantity").toString();
                            ReOrderLevelHolder = jsonObject.getString("reorderLevel").toString();
                            DescHolder = jsonObject.getString("description").toString();

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
            QUANTITY.setText(QuantityHolder);
            REORDERLEVEL.setText(ReOrderLevelHolder);
            DESC.setText(DescHolder);

        }
    }
}
