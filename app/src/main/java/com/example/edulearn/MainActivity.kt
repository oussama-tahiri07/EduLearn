package com.example.edulearn

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.edulearn.api.ApiClient
import com.example.edulearn.databinding.ActivityMainBinding
import com.example.edulearn.utils.TokenManager

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var tokenManager: TokenManager
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize TokenManager
        tokenManager = TokenManager(this)

        // Initialize ApiClient with TokenManager
        ApiClient.initialize(tokenManager)

        // Setup Navigation
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Setup Bottom Navigation
        binding.bottomNavigation.setupWithNavController(navController)

        // Hide bottom navigation on auth screens and show appropriate items based on user role
        navController.addOnDestinationChangedListener { _, destination, _ ->
            // Hide on auth screens
            if (destination.id == R.id.loginFragment ||
                destination.id == R.id.registerFragment) {
                binding.bottomNavigation.visibility = View.GONE
            } else {
                binding.bottomNavigation.visibility = View.VISIBLE

                // Update menu based on user role
                updateBottomNavigationMenu()
            }
        }

        // Check if user is logged in and navigate accordingly
        if (!tokenManager.getToken().isNullOrEmpty()) {
            navigateBasedOnRole()
        } else {
            navController.navigate(R.id.loginFragment)
        }
    }

    private fun updateBottomNavigationMenu() {
        val role = tokenManager.getUserRole()

        // Clear all menu items
        binding.bottomNavigation.menu.clear()

        // Add appropriate menu items based on role
        when (role) {
            "ADMIN" -> {
                binding.bottomNavigation.inflateMenu(R.menu.bottom_navigation_menu)
            }
            "TEACHER" -> {
                binding.bottomNavigation.inflateMenu(R.menu.bottom_navigation_menu)
            }
            else -> {
                binding.bottomNavigation.inflateMenu(R.menu.bottom_navigation_menu)
            }
        }
    }

    private fun navigateBasedOnRole() {
        // For now, navigate to home fragment
        // In a real implementation, you would navigate to different dashboards based on role
        navController.navigate(R.id.homeFragment)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
