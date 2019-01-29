package com.example.milindasenaka.immunizesrilanka.feature;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ApiRequester {
    private Context context;
    private int method;
    private String url;
    private Map<String, String> headers;
    private Map<String, String> body;
    private Response.Listener requestListener;
    private Response.ErrorListener errorListener;

    public ApiRequester(Context context, int method, String url, Map<String, String> headers, Map<String, String> body, Response.Listener requestListener, Response.ErrorListener errorListener) {
        this.context = context;
        this.method = method;
        this.url = url;
        this.headers = headers;
        this.body = body;
        this.requestListener = requestListener;
        this.errorListener = errorListener;
    }

    void sendRequest() {
        RequestQueue queue = Volley.newRequestQueue(context);
        JSONObject jsonObject = new JSONObject();
        for (Map.Entry<String, String> entry : body.entrySet()
                ) {
            try {
                jsonObject.put(entry.getKey(), entry.getValue());
            } catch (JSONException e) {
                Log.d("MYAPP", e.getStackTrace().toString());
            }
        }
        JsonObjectRequest jsonRequest = new JsonObjectRequest(method, url, jsonObject, requestListener, errorListener){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers == null ? new HashMap<String, String>(): headers;
            }
        };

        queue.add(jsonRequest);
    }
}
