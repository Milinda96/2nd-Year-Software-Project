package com.example.milindasenaka.immunizesrilanka.feature;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class UpdateVaccineDetailsActivity extends AppCompatActivity {
    String HttpURL = "http://192.168.43.128/VaccineDetails/UpdateVaccine.php";
    ProgressDialog progressDialog;
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    EditText VaccineName, VaccineQuantity, VaccineReOrderLevel, VaccineDesc;
    Button UpdateVaccine;
    String IDHolder, VaccineNameHolder, VaccineQuantityHolder, VaccineReOrderLevelHolder, VaccineDescHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_updatevaccine);

        VaccineName = (EditText)findViewById(R.id.editVName);
        VaccineQuantity = (EditText)findViewById(R.id.editVQuantity);
        VaccineReOrderLevel = (EditText)findViewById(R.id.editVReOrderLevel);
        VaccineDesc = (EditText)findViewById(R.id.editVDesc);


        UpdateVaccine = (Button)findViewById(R.id.btn_update);

        IDHolder = getIntent().getStringExtra("Id");
        VaccineNameHolder = getIntent().getStringExtra("Name");
        VaccineQuantityHolder = getIntent().getStringExtra("Quantity");
        VaccineReOrderLevelHolder = getIntent().getStringExtra("ReOrderLevel");
        VaccineDescHolder = getIntent().getStringExtra("Desc");


        VaccineName.setText(VaccineNameHolder);
        VaccineQuantity.setText(VaccineQuantityHolder);
        VaccineReOrderLevel.setText(VaccineReOrderLevelHolder);
        VaccineDesc.setText(VaccineDescHolder);

        UpdateVaccine.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                GetDataFromEditText();
                VaccineRecordUpdate(IDHolder, VaccineNameHolder, VaccineQuantityHolder, VaccineReOrderLevelHolder, VaccineDescHolder);
            }
        });
    }

    public void GetDataFromEditText()
    {

        VaccineNameHolder = VaccineName.getText().toString();
        VaccineQuantityHolder = VaccineQuantity.getText().toString();
        VaccineReOrderLevelHolder = VaccineReOrderLevel.getText().toString();
        VaccineDescHolder = VaccineDesc.getText().toString();


    }
    public void VaccineRecordUpdate(final String ID,String V_Name, final String V_Quantity, final String V_ReOderLevel, final String V_Desc)
    {

        class VaccineRecordUpdateClass extends AsyncTask<String,Void,String>
        {

            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(UpdateVaccineDetailsActivity.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg)
            {

                super.onPostExecute(httpResponseMsg);
                progressDialog.dismiss();
                Toast.makeText(UpdateVaccineDetailsActivity.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params)
            {

                hashMap.put("VaccineID",params[0]);
                hashMap.put("VaccineName",params[1]);
                hashMap.put("VaccineQuantity",params[2]);
                hashMap.put("VaccineReOrderLevel",params[3]);
                hashMap.put("VaccineDesc",params[4]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);
                return finalResult;
            }
        }

        VaccineRecordUpdateClass vaccineRecordUpdateClass = new VaccineRecordUpdateClass();
        vaccineRecordUpdateClass.execute(ID,V_Name,V_Quantity,V_ReOderLevel,V_Desc);
    }
}
