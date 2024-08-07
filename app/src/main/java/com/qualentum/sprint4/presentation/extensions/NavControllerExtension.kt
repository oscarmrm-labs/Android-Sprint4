package com.qualentum.sprint4.presentation.extensions

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.qualentum.sprint4.R

fun NavController.navigateToSettings() {
    if (this.currentDestination?.id != R.id.settingsFragment) {
        val navOptions = NavOptions.Builder()
            .setPopUpTo(this.currentDestination?.id ?: R.id.contactsFragment, false)
            .build()
        this.navigate(R.id.settingsFragment, null, navOptions)
    }
}