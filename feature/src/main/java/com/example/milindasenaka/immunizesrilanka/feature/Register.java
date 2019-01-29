package com.example.milindasenaka.immunizesrilanka.feature;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private EditText editText1,editText2,editText3,editText4,editText5;
    private Button regbtn;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_register);

        /**if (SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, HomeActivity.class));
            return;
        }**/

        editText1 = (EditText) findViewById(R.id.ed_name);
        editText2 = (EditText) findViewById(R.id.ed_email);
        editText3 = (EditText) findViewById(R.id.ed_username);
        editText4 = (EditText) findViewById(R.id.ed_pass1);
        editText5 = (EditText) findViewById(R.id.ed_pass2);

        regbtn = (Button) findViewById(R.id.btn_register);
        progressDialog = new ProgressDialog(this);
        regbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if (view == regbtn)
        {
            register();
        }
    }

    private void register()
    {
        if (!validate())
        {
            Toast.makeText(this,"Please Try Again",Toast.LENGTH_LONG).show();

        }
        else
        {
            final String Name=editText1.getText().toString().trim();
            final String Email=editText2.getText().toString().trim();
            final String Uname=editText3.getText().toString().trim();
            final String Pass=editText4.getText().toString().trim();
            final String Cpass=editText5.getText().toString().trim();

            progressDialog.setMessage("Registering User...");
            progressDialog.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Constants.URL_REGISTER,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();

                            try {
                                JSONObject jsonObject =new JSONObject(response);
                                Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.hide();
                            Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();

                        }
                    }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("NAME", Name);
                    params.put("EMAIL", Email);
                    params.put("USERNAME", Uname);
                    params.put("PASSWORD", Pass);
                    params.put("CPASSWORD", Cpass);
                    return params;
                }
            };
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        }
    }

    public boolean validate()
    {
        String NAME=editText1.getText().toString().trim();
        String EMAIL=editText2.getText().toString().trim();
        String UNAME=editText3.getText().toString().trim();
        String PASS=editText4.getText().toString().trim();
        String CPASS=editText5.getText().toString().trim();

        boolean valid=true;
        if (NAME.isEmpty() || NAME.length()>32)
        {
            editText1.setError("Please Enter Valid Name");
            valid=false;
        }

        if (EMAIL.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(EMAIL).matches())
        {
            editText2.setError("Please Enter Valid Email Address");
            valid=false;
        }

        if (UNAME.isEmpty() || UNAME.length()<5)
        {
            editText3.setError("Please Enter Valid Username");
            valid=false;
        }

        if (PASS.isEmpty() || PASS.length()<9)
        {
            editText4.setError("Please Enter Valid Name");
            valid=false;
        }

        if (CPASS.isEmpty() || CPASS.length()<9)
        {
            editText5.setError("Please Enter Valid Name");
            valid=false;
        }
        return valid;
    }

}