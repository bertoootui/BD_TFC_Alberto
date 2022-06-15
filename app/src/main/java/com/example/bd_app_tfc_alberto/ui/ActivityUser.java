package com.example.bd_app_tfc_alberto.ui;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.bd_app_tfc_alberto.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;


public class ActivityUser extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView nav;
    private static String email = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        email = getIntent().getExtras().getString("email");
        nav = findViewById(R.id.navBar);


                Toolbar toolbar = findViewById(R.id.toolbar);
                setSupportActionBar(findViewById(R.id.toolbar));
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(ActivityUser.this,
                        drawerLayout,
                        toolbar,
                        R.string.app_name,
                        R.string.txtnombre);
                drawerLayout.addDrawerListener(toggle);

                toggle.syncState();
            NavigationView navigationView = findViewById(R.id.navigation_view);
            navigationView.bringToFront();
            navigationView.setNavigationItemSelectedListener(this);



        final Fragment[] selectedFragment = {new NewDateFragment(email)};
        getSupportFragmentManager().beginTransaction().replace(R.id.container, selectedFragment[0]).commit();
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.menu_newDate:
                        selectedFragment[0] = new NewDateFragment(email);
                        nav.setSelected(true);
                        break;
                    case R.id.menu_calendar:
                        selectedFragment[0] = new CalendarFragment(email);
                        nav.setSelected(true);

                        break;
                    case R.id.menu_profile:
                        selectedFragment[0] = new ProfileFragment(email);
                        nav.setSelected(true);
                        break;


                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container,selectedFragment[0]).commit();
                return true;
            }
        });




    }

    @Override
    public void onBackPressed() {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_lateral, menu);
        return true;
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_camera:
                Intent t = new Intent(ActivityUser.this, ActivityLogin.class);
                startActivity(t);
                finish();
                return true;


        }

        return false;
    }
}
