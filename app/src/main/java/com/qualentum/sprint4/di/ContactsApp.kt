package com.qualentum.sprint4.di

import android.app.Application
import android.app.LocaleManager
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.qualentum.sprint4.data.shared_preferences.UserSharedPreferences
import com.qualentum.sprint4.domain.enums.ThemesEnum
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ContactsApp: Application() {

    override fun onCreate() {
        super.onCreate()
        changeLanguage(UserSharedPreferences.getAppLanguagePreference(this))
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

    private fun changeLanguage(language: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            this.getSystemService(LocaleManager::class.java)
                .applicationLocales = LocaleList.forLanguageTags(language)
        } else {
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(language)
            )
        }
    }
}