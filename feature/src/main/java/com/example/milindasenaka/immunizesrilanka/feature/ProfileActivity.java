package com.example.milindasenaka.immunizesrilanka.feature;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView textView1,textView2,textView3;
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_profile);

        textView1.setText(SharedPrefManager.getInstance(this).getUName());
        textView2.setText(SharedPrefManager.getInstance(this).getEmail());
        textView3.setText(SharedPrefManager.getInstance(this).getUsername());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int i = item.getItemId();

        if (i == R.id.logout)
        {
            SharedPrefManager.getInstance(this).logout();
            finish();
            startActivity(new Intent(this, MainActivity.class));

        }
        return true;
    }
}
