
package com.qualentum.sprint4.presentation.common

import android.content.Context
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.qualentum.sprint4.R
import com.qualentum.sprint4.presentation.extensions.navigateToSettings
import javax.inject.Singleton

@Singleton
class ToolbarManager(private val context: Context, private val toolbar: MaterialToolbar, navController: NavController) {

    init {
        toolbar.setupWithNavController(navController)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.settingsFragment -> {
                    navController.navigateToSettings()
                    true
                }

                else -> {
                    false
                }
            }
        }
    }

    fun setTitle(title: String){
        toolbar.setTitle(title)
    }

}