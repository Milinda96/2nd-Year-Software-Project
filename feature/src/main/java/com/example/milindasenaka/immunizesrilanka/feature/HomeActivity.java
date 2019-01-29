package com.example.milindasenaka.immunizesrilanka.feature;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_navigation);

        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout=findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle =new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstance == null)
        {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment()).commit();
                 navigationView.setCheckedItem(R.id.nav_home);
         }
    }

     @Override
     public boolean onOptionsItemSelected(MenuItem menuItem)
     {
        if (toggle.onOptionsItemSelected(menuItem))
        {
        return true;
        }
        return super.onOptionsItemSelected(menuItem);
     }


    public void setActionBarTitle(String title)
    {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onBackPressed()
    {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
            {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem)
    {
        int i = menuItem.getItemId();
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
         if (i == R.id.nav_type)
         {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(new HomeFragment(),"HomeFragment")
                    .addToBackStack("HomeFragment")
                    .replace(R.id.fragment_container, new VaccineFragment())
                    .commit();

        }
        else if (i == R.id.nav_stock)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(new HomeFragment(),"HomeFragment")
                    .addToBackStack("HomeFragment")
                    .replace(R.id.fragment_container, new UpdateFragment())
                    .commit();

        }
        else if (i == R.id.nav_schedule)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(new HomeFragment(),"HomeFragment")
                    .addToBackStack("HomeFragment")
                    .replace(R.id.fragment_container, new ScheduleFragment())
                    .commit();

        }
        else if (i == R.id.nav_settings)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(new HomeFragment(),"HomeFragment")
                    .addToBackStack("HomeFragment")
                    .replace(R.id.fragment_container, new SettingFragment())
                    .commit();

        }
        else if (i == R.id.nav_profile)
        {
             startActivity(new Intent(this, ProfileActivity.class));
        }
        else if (i == R.id.nav_share)
        {
            Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();

        }
        else if (i == R.id.nav_send)
        {
            Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
