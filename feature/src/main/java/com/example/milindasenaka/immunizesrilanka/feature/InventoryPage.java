package com.example.milindasenaka.immunizesrilanka.feature;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.HashMap;

public class InventoryPage extends AppCompatActivity
{
    EditText VaccineName, VaccineQuantity, VaccineReOrderLevel, VaccineDesc;
    Button InsertVaccine;
    String VaccineNameHolder, VaccineQuantityHolder, VaccineReOrderLevelHolder, VaccineDescHolder;
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    String HttpURL = "http://192.168.43.128/VaccineDetails/InsertVaccine.php";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addvaccine);

        VaccineName = (EditText)findViewById(R.id.editVName);
        VaccineQuantity = (EditText)findViewById(R.id.editVQuantity);
        VaccineReOrderLevel = (EditText)findViewById(R.id.editVReOrderLevel);
        VaccineDesc = (EditText)findViewById(R.id.editVDesc);

        InsertVaccine = (Button)findViewById(R.id.buttonSubmit);
        InsertVaccine.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                CheckEditTextIsEmptyOrNot();

                if(CheckEditText)
                {

                    VaccineInsertion(VaccineNameHolder,VaccineQuantityHolder,VaccineReOrderLevelHolder,VaccineDescHolder);

                }
                else
                {

                    Toast.makeText(InventoryPage.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }

            }
        });
    }

    public void VaccineInsertion(final String V_Name, final String V_Quantity, final String V_ReOderLevel, final String V_Desc)
    {

        class VaccineInsertionClass extends AsyncTask<String,Void,String>
        {

            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(InventoryPage.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg)
            {

                super.onPostExecute(httpResponseMsg);
                progressDialog.dismiss();
                Toast.makeText(InventoryPage.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("VaccineName",params[0]);
                hashMap.put("VaccineQuantity",params[1]);
                hashMap.put("VaccineReOrderLevel",params[2]);
                hashMap.put("VaccineDesc",params[3]);
                finalResult = httpParse.postRequest(hashMap, HttpURL);
                return finalResult;
            }
        }

        VaccineInsertionClass VaccineInsertionClass = new VaccineInsertionClass();
        VaccineInsertionClass.execute(V_Name,V_Quantity,V_ReOderLevel,V_Desc);
    }


    public void CheckEditTextIsEmptyOrNot()
    {

        VaccineNameHolder = VaccineName.getText().toString();
        VaccineQuantityHolder = VaccineQuantity.getText().toString();
        VaccineReOrderLevelHolder = VaccineReOrderLevel.getText().toString();
        VaccineDescHolder = VaccineDesc.getText().toString();

        if(TextUtils.isEmpty(VaccineNameHolder) || TextUtils.isEmpty(VaccineQuantityHolder) || TextUtils.isEmpty(VaccineReOrderLevelHolder)|| TextUtils.isEmpty(VaccineDescHolder))
        {
            CheckEditText = false;
        }
        else
        {
            CheckEditText = true ;
        }

    }
}
