package com.qualentum.sprint4.di

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.qualentum.sprint4.data.shared_preferences.UserSharedPreferences
import com.qualentum.sprint4.domain.enums.ThemesEnum
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ContactsApp: Application() {

    override fun onCreate() {
        super.onCreate()
        setTheme()
    }

    private fun setTheme() {
        val themePreference = UserSharedPreferences.getAppThemePreference(this)
        when (themePreference) {
            ThemesEnum.LIGHT.name -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            ThemesEnum.DARK.name -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }


}