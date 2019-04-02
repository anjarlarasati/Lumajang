package com.laras.lumajang;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.laras.lumajang.Fragment.Category.FragmentCategory;
import com.laras.lumajang.Fragment.Home.FragmentHome;
import com.laras.lumajang.Fragment.Maps.FragmentMaps;
import com.laras.lumajang.Fragment.Maps.MapsActivity;

public class MainMenu extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setTitle("Home");
                    FragmentHome fragment = new FragmentHome();
                    FragmentTransaction fragmentTransaction1= getSupportFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.frame, fragment, "FragmentName");
                    fragmentTransaction1.commit();
                    return  true;
                case R.id.navigation_category:
                    setTitle("Categories");
                    FragmentCategory fragment2 = new FragmentCategory();
                    FragmentTransaction fragmentTransaction2= getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.frame, fragment2, "FragmentName");
                    fragmentTransaction2.commit();
                    return  true;

                case R.id.navigation_maps:
                    setTitle("Maps");

                    FragmentMaps fragment3 = new FragmentMaps();
                    FragmentTransaction fragmentTransaction3= getSupportFragmentManager().beginTransaction();
                    fragmentTransaction3.replace(R.id.frame, fragment3, "FragmentName");
                    fragmentTransaction3.commit();
                    return  true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_nav);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setTitle("Home");
        FragmentHome fragment = new FragmentHome();
        FragmentTransaction fragmentTransaction1= getSupportFragmentManager().beginTransaction();
        fragmentTransaction1.replace(R.id.frame, fragment, "FragmentName");
        fragmentTransaction1.commit();

    }

}
