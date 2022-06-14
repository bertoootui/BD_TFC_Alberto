package com.example.bd_app_tfc_alberto.ui;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.bd_app_tfc_alberto.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class ActivityUser extends AppCompatActivity {
    BottomNavigationView nav;
    private static String email = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        email = getIntent().getExtras().getString("email");
        nav = findViewById(R.id.navBar);
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
}
