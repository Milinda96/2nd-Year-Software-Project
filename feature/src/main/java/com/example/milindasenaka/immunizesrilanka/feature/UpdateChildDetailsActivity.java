package com.example.milindasenaka.immunizesrilanka.feature;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.HashMap;

public class UpdateChildDetailsActivity extends AppCompatActivity
{

    String HttpURL = "http://192.168.43.128/ChildDetails/UpdateChild.php";
    ProgressDialog progressDialog;
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    EditText ChildName, ChildHIN, ChildRegNo, ChildRegDate, ChildTime, ChildDOB, ChildGender,ChildPlace, ChildWeight;
    Button UpdateChild;
    String IDHolder, ChildNameHolder, ChildHINHolder, ChildRegNoHolder, ChildRegDateHolder, ChildTimeHolder, ChildDOBHolder, ChildGenderHolder, ChildPlaceHolder, ChildWeightHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatechild);

        ChildName = (EditText)findViewById(R.id.editName);
        ChildHIN = (EditText)findViewById(R.id.editHIN);
        ChildRegNo = (EditText)findViewById(R.id.editRegNo);
        ChildRegDate = (EditText)findViewById(R.id.editRegDate);
        ChildTime = (EditText)findViewById(R.id.editTime);
        ChildDOB = (EditText)findViewById(R.id.editDOB);
        ChildGender = (EditText)findViewById(R.id.editGender);
        ChildPlace = (EditText)findViewById(R.id.editPlace);
        ChildWeight = (EditText)findViewById(R.id.editWeight);

        UpdateChild = (Button)findViewById(R.id.btn_update);

        IDHolder = getIntent().getStringExtra("Id");
        ChildNameHolder = getIntent().getStringExtra("Name");
        ChildHINHolder = getIntent().getStringExtra("HIN");
        ChildRegNoHolder = getIntent().getStringExtra("RegNo");
        ChildRegDateHolder = getIntent().getStringExtra("RegDate");
        ChildTimeHolder = getIntent().getStringExtra("Time");
        ChildDOBHolder = getIntent().getStringExtra("DOB");
        ChildGenderHolder = getIntent().getStringExtra("Gender");
        ChildPlaceHolder = getIntent().getStringExtra("Place");
        ChildWeightHolder = getIntent().getStringExtra("Weight");


        ChildName.setText(ChildNameHolder);
        ChildHIN.setText(ChildHINHolder);
        ChildRegNo.setText(ChildRegNoHolder);
        ChildRegDate.setText(ChildRegDateHolder);
        ChildTime.setText(ChildTimeHolder);
        ChildDOB.setText(ChildDOBHolder);
        ChildGender.setText(ChildGenderHolder);
        ChildPlace.setText(ChildPlaceHolder);
        ChildWeight.setText(ChildWeightHolder);

        UpdateChild.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                GetDataFromEditText();
                ChildRecordUpdate(IDHolder, ChildNameHolder, ChildHINHolder, ChildRegNoHolder, ChildRegDateHolder, ChildTimeHolder, ChildDOBHolder, ChildGenderHolder, ChildPlaceHolder, ChildWeightHolder);
            }
        });
    }

    public void GetDataFromEditText()
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


    }
    public void ChildRecordUpdate(final String ID, final String C_Name, final String C_HIN, final String C_RegNo, final String C_RegDate, final String C_Time, final String C_DOB, final String C_Gender, final String C_Place, final String C_Weight)
    {

        class ChildRecordUpdateClass extends AsyncTask<String,Void,String>
        {

            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(UpdateChildDetailsActivity.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg)
            {

                super.onPostExecute(httpResponseMsg);
                progressDialog.dismiss();
                Toast.makeText(UpdateChildDetailsActivity.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params)
            {

                hashMap.put("ChildID",params[0]);
                hashMap.put("ChildFName",params[1]);
                hashMap.put("ChildHIN",params[2]);
                hashMap.put("ChildRegID",params[3]);
                hashMap.put("ChildRegDate",params[4]);
                hashMap.put("ChildTimeofBirth",params[5]);
                hashMap.put("ChildDOB",params[6]);
                hashMap.put("ChildGender",params[7]);
                hashMap.put("ChildPlace",params[8]);
                hashMap.put("ChildWeight",params[9]);
                finalResult = httpParse.postRequest(hashMap, HttpURL);
                return finalResult;
            }
        }

        ChildRecordUpdateClass childRecordUpdateClass = new ChildRecordUpdateClass();
        childRecordUpdateClass.execute(ID,C_Name,C_HIN,C_RegNo,C_RegDate,C_Time,C_DOB,C_Gender,C_Place,C_Weight);
    }
}
