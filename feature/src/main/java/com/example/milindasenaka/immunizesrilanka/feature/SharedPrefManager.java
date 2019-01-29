package com.example.milindasenaka.immunizesrilanka.feature;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private static SharedPrefManager mInstance;
    private static Context mCtx;
    private  static final String SHARED_PREF_NAME ="itamp";
    private static final String USER_ID = "userid";
    private static final String USER_NAME = "userfname";
    private static final String USER_EMAIL = "useremail";
    private static final String USER_UNAME = " useruname";

    private SharedPrefManager(Context context)
    {
        mCtx = context;

    }

    public static synchronized SharedPrefManager getInstance(Context context){
        if (mInstance == null){
            mInstance = new SharedPrefManager(context);

        }
        return mInstance;
    }

    public boolean userLogin(int id, String name, String email, String username){

        SharedPreferences sharedPreferences =mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putInt(USER_ID, id);
        editor.putString(USER_NAME, name);
        editor.putString(USER_EMAIL, email);
        editor.putString(USER_UNAME, username);
        editor.apply();
        return true;
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
       if (sharedPreferences.getString(USER_UNAME, null) != null){
           return true;
       }
       return false;
    }
    public boolean logout(){
        SharedPreferences sharedPreferences =mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return  true;
    }

    public String getUName(){
        SharedPreferences sharedPreferences =mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_NAME, null);

    }
    public String getEmail(){
        SharedPreferences sharedPreferences =mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_EMAIL, null);

    }

    public String getUsername(){
        SharedPreferences sharedPreferences =mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_UNAME, null);

    }
}
