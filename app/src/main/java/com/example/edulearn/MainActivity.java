package com.example.edulearn;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.edulearn.api.ApiClient;
import com.example.edulearn.databinding.ActivityMainBinding;
import com.example.edulearn.utils.TokenManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private TokenManager tokenManager;
    private NavController navController;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        // Initialize TokenManager
        tokenManager = new TokenManager(this);
        
        // Initialize ApiClient with TokenManager
        ApiClient.initialize(tokenManager);
        
        // Setup Navigation
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        
        // Setup Bottom Navigation
        BottomNavigationView bottomNav = binding.bottomNavigation;
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_courses, 
                R.id.navigation_assignments, R.id.navigation_profile)
                .build();
        
        NavigationUI.setupWithNavController(bottomNav, navController);
        
        // Hide bottom navigation on auth screens
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.loginFragment || 
                destination.getId() == R.id.registerFragment) {
                bottomNav.setVisibility(View.GONE);
            } else {
                bottomNav.setVisibility(View.VISIBLE);
            }
        });
        
        // Check if user is logged in and navigate accordingly
        if (!tokenManager.hasToken()) {
            navController.navigate(R.id.loginFragment);
        }
    }
    
    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}
