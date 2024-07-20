package com.qualentum.sprint4.data.shared_preferences

import android.content.Context
import android.content.SharedPreferences
import com.qualentum.sprint4.domain.enums.ThemesEnum
import java.util.Locale

object UserSharedPreferences {
    private const val SHARED_PREFERENCES_FILE_NAME: String = "USER_PREFERENCES"

    private const val CURRENT_APP_LANGUAGE_KEY: String = "CURRENT_APP_LANGUAGE_KEY"
    private const val THEME_PREFERENCE_KEY: String = "THEME_PREFERENCE_KEY"

    fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)
    }

    private inline fun SharedPreferences.editSh(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    fun saveAppLanguagePreference(context: Context, language: String) {
        val sharedPreferences = getSharedPreferences(context)
        sharedPreferences.editSh {
            it.putString(CURRENT_APP_LANGUAGE_KEY, language)
        }
    }

    fun getAppLanguagePreference(context: Context): String {
        val sharedPreferences = getSharedPreferences(context)
        return sharedPreferences.getString(CURRENT_APP_LANGUAGE_KEY, Locale.getDefault().language).toString()
    }

    fun saveAppThemePreference(context: Context, theme: String) {
        val sharedPreferences = getSharedPreferences(context)
        sharedPreferences.editSh {
            it.putString(THEME_PREFERENCE_KEY, theme)
        }
    }

    fun getAppThemePreference(context: Context): String {
        val sharedPreferences = getSharedPreferences(context)
        return sharedPreferences.getString(THEME_PREFERENCE_KEY, ThemesEnum.SYSTEM.name).toString()
    }
}