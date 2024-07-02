package com.qualentum.sprint4.presentation.add_contacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.datepicker.MaterialDatePicker
import com.qualentum.sprint4.R
import com.qualentum.sprint4.databinding.FragmentAddContactBinding
import com.qualentum.sprint4.domain.model.DetailContactModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class AddContactFragment : Fragment() {
    private lateinit var binding: FragmentAddContactBinding
    private val viewModel: AddContactViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddContactBinding.inflate(inflater, container, false)
        inflateBinding()
        return binding.root
    }

    private fun inflateBinding() {
        binding.apply {
            ietDate.apply {
                setOnClickListener {
                    createMaterialDatePicker()
                }
                onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) {
                        createMaterialDatePicker()
                    }
                }
            }
            btnAddContact.setOnClickListener {
                insertContactInDatabase()
            }


            btnShowLocation.setOnClickListener {
                lifecycleScope.launch {
                    val location = viewModel.getUserLocation(context)
                    ietLatitude.setText(location.latitude)
                    ietLongitude.setText(location.longitude)
                    tvLocation.text = "Latitud: ${location.latitude}, Longitud: ${location.longitude}"
                }
            }
        }
    }

    private fun createMaterialDatePicker() {
        val selectedDate = let {
            if (binding.ietDate.text?.isNotEmpty() == true) {
                //selectedDate = binding.ietDate.text
                val fechaStr = binding.ietDate.text
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val date = sdf.parse(fechaStr.toString())
                date?.time
            } else {
                MaterialDatePicker.todayInUtcMilliseconds()
            }
        }

        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText(R.string.add_contact_screen_date_of_birth_title_dialog)
            .setSelection(selectedDate)
            .build()

        activity?.let { datePicker.show(it.supportFragmentManager, "MATERIAL_DATE_PICKER") }

        setOnClicksDatePicker(datePicker)
    }

    private fun setOnClicksDatePicker(datePicker: MaterialDatePicker<Long>) {
        datePicker.addOnPositiveButtonClickListener { selection ->
            val selectedDate = Date(selection)
            val formattedDate =
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(selectedDate)
            binding.ietDate.setText(formattedDate.toString())
        }
    }

    private fun insertContactInDatabase() {
        val name = binding.ietName.text.toString()
        val lastName = binding.ietLastName.text.toString()
        val dateOfBirth = binding.ietDate.text.toString()
        val favouriteColor = binding.ietColor.text.toString()
        val favouriteSport = binding.ietSport.text.toString()
        val latitude = binding.ietLatitude.text.toString()
        val longitude = binding.ietLongitude.text.toString()

        lifecycleScope.launch {
            viewModel.insertContact(DetailContactModel(name, lastName, dateOfBirth, favouriteColor, favouriteSport, latitude, longitude))
        }
    }
}