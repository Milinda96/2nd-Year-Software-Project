package com.example.milindasenaka.immunizesrilanka.feature;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private RelativeLayout relativeLayout1,relativeLayout2;
    private Button button1,button2;
    private EditText editText1,editText2;
    private ProgressDialog progressDialog;

    Handler handler = new Handler();
    Runnable runnable = new Runnable()
    {
        @Override
        public void run()
        {
            relativeLayout1.setVisibility(View.VISIBLE);
            relativeLayout2.setVisibility(View.VISIBLE);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /***if (SharedPrefManager.getInstance(this).isLoggedIn()){
         finish();
         startActivity(new Intent(this, HomeActivity.class));
         return;
         }**/

        relativeLayout1 = (RelativeLayout) findViewById(R.id.relay1);
        relativeLayout2 = (RelativeLayout) findViewById(R.id.relay2);
        handler.postDelayed(runnable, 2000);

        button1=(Button)findViewById(R.id.btn_login);
        button2=(Button)findViewById(R.id.btn_signup);
        editText1=(EditText)findViewById(R.id.ed_uname);
        editText2=(EditText)findViewById(R.id.ed_pass);

        progressDialog =new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }


    private void loginUser(){

        final String username = editText1.getText().toString().trim();
        final String password = editText2.getText().toString().trim();

        progressDialog.show();

        StringRequest stringRequest =new StringRequest
                (
                        Request.Method.POST,
                        "http://169.254.72.148:8081/user/login",
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response)
                            {
                                progressDialog.dismiss();
                                try
                                {
                                    JSONObject jsonObject = new JSONObject(response);
                                    if (!jsonObject.getBoolean("error"))
                                    {
                                        SharedPrefManager.getInstance(getApplicationContext())
                                                .userLogin(
                                                        jsonObject.getInt("id"),
                                                        jsonObject.getString("name"),
                                                        jsonObject.getString("email"),
                                                        jsonObject.getString("username")
                                                );
                                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                        finish();
                                    }
                                    else
                                    {
                                        Toast.makeText(
                                                getApplicationContext(),
                                                jsonObject.getString("message"),
                                                Toast.LENGTH_LONG
                                        ).show();
                                    }
                                }
                                catch (JSONException e)
                                {
                                    e.printStackTrace();
                                }

                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                progressDialog.dismiss();

                                Toast.makeText(
                                        getApplicationContext(),
                                        error.getMessage(),
                                        Toast.LENGTH_LONG
                                ).show();

                            }
                        }

                ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View v)
    {
        if (v == button1)
        {
            loginUser();

        }
        if (v == button2 )
        {
            startActivity(new Intent(this, Register.class));
        }

    }
}
