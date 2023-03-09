package com.tuvarna.mytu;

import static androidx.navigation.ui.BottomNavigationViewKt.setupWithNavController;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    NavHostFragment navHostFragment;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try  {
            Objects.requireNonNull(this.getSupportActionBar()).hide();
        } catch (NullPointerException ignored){}

        navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.main_fragment_layout);
        navController = navHostFragment.getNavController();
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        setupWithNavController(bottomNavigationView, navController);

    }
}