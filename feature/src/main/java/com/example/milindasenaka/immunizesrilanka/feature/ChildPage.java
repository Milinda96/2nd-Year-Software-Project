package com.example.milindasenaka.immunizesrilanka.feature;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.HashMap;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;


public class ChildPage extends AppCompatActivity
{

    EditText ChildName, ChildHIN, ChildRegNo, ChildRegDate, ChildTime, ChildDOB, ChildGender, ChildPlace, ChildWeight;
    EditText Address1,Address2,Address3,Postal;
    EditText MotherName,MotherNIC,MotherRegNo,FatherName,FatherNIC,ContactNo;
    EditText BHTNo,Ward,BirthCertificateNo,DetailsOfDelivary;
    Button InsertChild;
    Spinner spinner;
    String ChildNameHolder, ChildHINHolder, ChildRegNoHolder, ChildRegDateHolder, ChildTimeHolder, ChildDOBHolder, ChildGenderHolder, ChildPlaceHolder, ChildWeightHolder,
            Address1Holder,Address2Holder,Address3Holder,PostalHolder,MotherNameHolder,MotherNICHolder,MotherRegNoHolder,FatherNameHolder,FatherNICHolder,ContactNoHolder,
            BHTNoHolder,WardHolder,BirthCertificateNoHolder,DetailsOfDelivaryHolder;


    Boolean CheckEditText;
    ProgressDialog progressDialog;
    String finalResult;
    HashMap<String, String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    String HttpURL = "http://10.10.16.38/ChildDetails/ChildRegister.php";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_childdata);

        ChildName = (EditText) findViewById(R.id.editName);
        ChildHIN = (EditText) findViewById(R.id.editHIN);
        ChildRegNo = (EditText) findViewById(R.id.editRegNo);
        ChildRegDate = (EditText) findViewById(R.id.editRegDate);
        ChildTime = (EditText) findViewById(R.id.editTime);
        ChildDOB = (EditText) findViewById(R.id.editDOB);
        ChildGender = (EditText) findViewById(R.id.editGender);
        ChildPlace = (EditText) findViewById(R.id.editPlace);
        ChildWeight = (EditText) findViewById(R.id.editWeight);

        Address1 = (EditText)findViewById(R.id.editline1);
        Address2 = (EditText)findViewById(R.id.editline2);
        Address3 = (EditText) findViewById(R.id.editline3);
        Postal = (EditText) findViewById(R.id.editCode);

        MotherName = (EditText) findViewById(R.id.editmother);
        MotherNIC = (EditText) findViewById(R.id.editMNIC);
        MotherRegNo = (EditText) findViewById(R.id.editRegNo);
        FatherName = (EditText) findViewById(R.id.editFather);
        FatherNIC = (EditText) findViewById(R.id.editFNIC);
        ContactNo = (EditText) findViewById(R.id.editContact);

        BHTNo = (EditText) findViewById(R.id.editBHTNo);
        Ward = (EditText) findViewById(R.id.editWard);
        BirthCertificateNo = (EditText) findViewById(R.id.editBCNo);
        DetailsOfDelivary = (EditText) findViewById(R.id.editDetailsOfDelivary);


        InsertChild = (Button) findViewById(R.id.buttonSubmit);

        spinner = (Spinner)findViewById(R.id.rdhs);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ChildPage.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.rdhsArea));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        InsertChild.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                CheckEditTextIsEmptyOrNot();

                if (CheckEditText)
                {
                    ChildInsertion(ChildNameHolder, ChildHINHolder, ChildRegNoHolder, ChildRegDateHolder, ChildTimeHolder, ChildDOBHolder, ChildGenderHolder, ChildPlaceHolder, ChildWeightHolder);
                }
                else
                {
                    Toast.makeText(ChildPage.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void ChildInsertion(final String C_Name, final String C_HIN, final String C_RegNo, final String C_RegDate, final String C_Time, final String C_DOB, final String C_Gender, final String C_Place, final String C_Weight)
    {

        class ChildInsertionClass extends AsyncTask<String, Void, String>
        {

            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(ChildPage.this, "Loading Data", null, true, true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg)
            {

                super.onPostExecute(httpResponseMsg);
                progressDialog.dismiss();
                Toast.makeText(ChildPage.this, httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params)
            {
                hashMap.put("registrationId", params[0]);
                hashMap.put("registrationDate", params[1]);
                hashMap.put("fullName", params[2]);
                hashMap.put("hin", params[3]);
                hashMap.put("dateOfBirth", params[4]);
                hashMap.put("timeOfBirth", params[5]);
                hashMap.put("gender", params[6]);
                hashMap.put("birthWeight", params[7]);
                hashMap.put("placeOfBirth", params[8]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);
                return finalResult;
            }
        }

        ChildInsertionClass childInsertionClass = new ChildInsertionClass();
        childInsertionClass.execute(C_Name, C_HIN, C_RegNo, C_RegDate, C_Time, C_DOB, C_Gender, C_Place, C_Weight);
    }

    public void CheckEditTextIsEmptyOrNot()
    {

        ChildNameHolder = ChildName.getText().toString();
        ChildHINHolder = ChildHIN.getText().toString();
        ChildRegNoHolder = ChildRegNo.getText().toString();
        ChildRegDateHolder = ChildRegDate.getText().toString();
        ChildTimeHolder = ChildTime.getText().toString();
        ChildDOBHolder = ChildDOB.getText().toString();
        ChildGenderHolder = ChildGender.getText().toString();
        ChildPlaceHolder = ChildPlace.getText().toString();
        ChildWeightHolder = ChildWeight.getText().toString();

        if (TextUtils.isEmpty(ChildNameHolder) || TextUtils.isEmpty(ChildHINHolder) || TextUtils.isEmpty(ChildRegNoHolder) || TextUtils.isEmpty(ChildRegDateHolder) || TextUtils.isEmpty(ChildTimeHolder) || TextUtils.isEmpty(ChildDOBHolder) || TextUtils.isEmpty(ChildGenderHolder) || TextUtils.isEmpty(ChildPlaceHolder) || TextUtils.isEmpty(ChildWeightHolder))
        {
            CheckEditText = false;
        }
        else
        {
            CheckEditText = true;
        }

    }
}

