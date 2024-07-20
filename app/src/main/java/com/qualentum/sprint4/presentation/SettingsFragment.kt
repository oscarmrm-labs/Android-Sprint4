package com.qualentum.sprint4.presentation

import android.app.LocaleManager
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.qualentum.sprint4.data.shared_preferences.UserSharedPreferences
import com.qualentum.sprint4.databinding.FragmentSettingsBinding
import com.qualentum.sprint4.domain.enums.LanguagesEnum
import com.qualentum.sprint4.domain.enums.ThemesEnum
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        setUpRadioButtonsThemes()
        setUpRadioButtonsLanguages()
        return binding.root
    }

    private fun setUpRadioButtonsThemes() {
        loadCheckRbThemes()
        radioButtonSetOnChangeListenerTheme()
    }

    private fun loadCheckRbThemes() {
        binding.apply {
            val themePreference = UserSharedPreferences.getAppThemePreference(requireContext())
            when (themePreference) {
                ThemesEnum.LIGHT.name -> rbLight.isChecked = true
                ThemesEnum.DARK.name -> rbDark.isChecked = true
                ThemesEnum.SYSTEM.name -> rbSystem.isChecked = true
            }
        }
    }

    private fun radioButtonSetOnChangeListenerTheme() {
        binding.apply {
            rgTheme.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    rbLight.id -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        UserSharedPreferences.saveAppThemePreference(requireContext(), ThemesEnum.LIGHT.name)
                    }
                    rbDark.id -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        UserSharedPreferences.saveAppThemePreference(requireContext(), ThemesEnum.DARK.name)
                    }
                    rbSystem.id -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                        UserSharedPreferences.saveAppThemePreference(requireContext(), ThemesEnum.SYSTEM.name)
                    }
                }
            }
        }
    }

    private fun setUpRadioButtonsLanguages() {
        loadCheckRbLanguages()
        radioButtonSetOnChangeListenerLanguages()
    }

    private fun loadCheckRbLanguages() {
        binding.apply {
            val language = UserSharedPreferences.getAppLanguagePreference(requireContext())
            when (language) {
                LanguagesEnum.ENGLISH.systemLanguage -> rbEnglish.isChecked = true
                LanguagesEnum.SPANISH.systemLanguage -> rbSpanish.isChecked = true
            }
        }
    }

    private fun radioButtonSetOnChangeListenerLanguages() {
        binding.apply {
            rgLanguage.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    rbEnglish.id -> {
                        UserSharedPreferences.saveAppLanguagePreference(requireContext(), LanguagesEnum.ENGLISH.systemLanguage)
                        changeLanguage(LanguagesEnum.ENGLISH.systemLanguage)
                    }
                    rbSpanish.id -> {
                        UserSharedPreferences.saveAppLanguagePreference(requireContext(), LanguagesEnum.SPANISH.systemLanguage)
                        changeLanguage(LanguagesEnum.SPANISH.systemLanguage)
                    }
                }
            }
        }
    }

    private fun changeLanguage(language: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireContext().getSystemService(LocaleManager::class.java)
                .applicationLocales = LocaleList.forLanguageTags(language)
        } else {
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(language)
            )
        }
    }
}