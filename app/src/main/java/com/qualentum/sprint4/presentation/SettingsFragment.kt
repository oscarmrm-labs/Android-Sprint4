package com.qualentum.sprint4.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.qualentum.sprint4.data.shared_preferences.UserSharedPreferences
import com.qualentum.sprint4.databinding.FragmentSettingsBinding
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
        loadCheckRadioButton()
        radioButtonSetOnChangeListener()
        return binding.root
    }

    private fun loadCheckRadioButton() {
        binding.apply {
            val themePreference = UserSharedPreferences.getAppThemePreference(requireContext())
            when (themePreference) {
                ThemesEnum.LIGHT.name -> rbLight.isChecked = true
                ThemesEnum.DARK.name -> rbDark.isChecked = true
                ThemesEnum.SYSTEM.name -> rbSystem.isChecked = true
            }
        }
    }

    private fun radioButtonSetOnChangeListener() {
        binding.apply {
            radioGroup.setOnCheckedChangeListener { _, checkedId ->
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
}