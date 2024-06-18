package com.qualentum.sprint4.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.qualentum.sprint4.R
import com.qualentum.sprint4.databinding.ActivityMainBinding
import com.qualentum.sprint4.presentation.extensions.navigateToSettings

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //transparentSystemBars()
        setUpNavController()
        setUpToolbar()
        setUpBottomNavigation()
        changeBottomBarVisibility()
    }

    private fun changeBottomBarVisibility() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.mainFragment -> binding.bottomNavigationView.visibility = View.VISIBLE
                R.id.favouritesFragment -> binding.bottomNavigationView.visibility = View.VISIBLE
                else -> binding.bottomNavigationView.visibility = View.GONE
            }
        }
    }

    private fun setUpNavController() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragmentContainer) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun setUpToolbar() {
        binding.toolbar.setupWithNavController(navController)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.settingsFragment -> {
                    navController.navigateToSettings()
                    //binding.bottomNavigationView.visibility = View.GONE
                    true
                }
                else -> {
                    false
                }
            }
        }
    }



    private fun transparentSystemBars() {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setUpBottomNavigation() {
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}