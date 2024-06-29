package com.qualentum.sprint4.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.qualentum.sprint4.R
import com.qualentum.sprint4.databinding.ActivityMainBinding
import com.qualentum.sprint4.presentation.common.ToolbarManager
import com.qualentum.sprint4.presentation.extensions.navigateToSettings
import com.qualentum.sprint4.presentation.interfaces.ToolbarTitleListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ToolbarTitleListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var toolbarManager: ToolbarManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpNavController()
        setUpToolbar()
        setUpBottomNavigation()
        changeBottomBarVisibility()
    }

    private fun changeBottomBarVisibility() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.contactsFragment -> binding.bottomNavigationView.visibility = View.VISIBLE
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
        toolbarManager = ToolbarManager(this, binding.toolbar, navController)
    }


    private fun setUpBottomNavigation() {
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    override fun updateToolbarTitle(title: String) {
        toolbarManager.setTitle(title)
    }

}