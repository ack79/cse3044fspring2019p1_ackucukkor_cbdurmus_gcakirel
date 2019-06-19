package com.canberkdurmus.libra;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_timeline:
                    NavigationController.openTimelineFragment(getSupportFragmentManager(), "Timeline fragment");
                    return true;
                case R.id.navigation_search:
                    NavigationController.openSearchFragment(getSupportFragmentManager(), "Search Fragment");
                    return true;
                case R.id.navigation_adverts:
                    NavigationController.openAdvertFragment(getSupportFragmentManager(), "Advert Fragment");
                    return true;
                case R.id.navigation_library:
                    NavigationController.openLibraryFragment(getSupportFragmentManager(), "Library Fragment");
                    return true;
                case R.id.navigation_profile:
                    NavigationController.openProfileFragment(getSupportFragmentManager(), "Profile Fragment");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        NavigationController.openTimelineFragment(getSupportFragmentManager(), "Initialized Timeline Fragment");
    }

}
