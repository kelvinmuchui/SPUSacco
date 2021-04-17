package com.delaroystudios.materiallogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.delaroystudios.materiallogin.fragment.LoanFragment;
import com.delaroystudios.materiallogin.fragment.UsersFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminActivity extends AppCompatActivity {
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Users");
        BottomNavigationView navigationView = findViewById(R.id.navigation);

        navigationView.setOnNavigationItemSelectedListener(selectedListener);
        actionBar.setTitle("Home");
        UsersFragment fragment1 = new UsersFragment();
        FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
        ft1.replace(R.id.content, fragment1, "");
        ft1.commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    //handles item clicks
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            actionBar.setTitle("Home");
                            UsersFragment fragment1 = new UsersFragment();
                            FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                            ft1.replace(R.id.content, fragment1, "");
                            ft1.commit();
                            return true;
                        case R.id.nav_loans:
                            actionBar.setTitle("Loans");
                            LoanFragment fragment2 = new LoanFragment();
                            FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                            ft2.replace(R.id.content, fragment2, "");
                            ft2.commit();
                            return true;
                        case R.id.nav_updates:
                            actionBar.setTitle("Update");
                            UsersFragment fragment3 = new UsersFragment();
                            FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
                            ft3.replace(R.id.content, fragment3, "");
                            ft3.commit();
                            return true;



                    }
                    return false;
                }
            };
}
