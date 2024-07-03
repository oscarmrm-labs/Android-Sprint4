package com.qualentum.sprint4.presentation.add_contacts

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.RippleDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.qualentum.sprint4.R
import com.qualentum.sprint4.databinding.FragmentAddContactBinding
import com.qualentum.sprint4.domain.model.DetailContactModel
import com.qualentum.sprint4.presentation.contacts.ContactsFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class AddContactFragment : Fragment() {
    private lateinit var binding: FragmentAddContactBinding
    private val viewModel: AddContactViewModel by viewModels()
    private val permissionLocation =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) Toast.makeText(requireContext(), "Permiso acceptado", Toast.LENGTH_SHORT).show()
            else Toast.makeText(requireContext(), "Permiso rechazado", Toast.LENGTH_SHORT).show()
        }

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
                setOnClickListener { createMaterialDatePicker() }
                onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) {
                        createMaterialDatePicker()
                    }
                }
            }
            btnAddContact.setOnClickListener {
                insertContactInDatabase()
                Toast.makeText(requireContext(), "Contacto añadido", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }


            btnShowLocation.setOnClickListener {
                if (requestPermission()) {
                    lifecycleScope.launch {
                        val location = viewModel.getUserLocation(context)
                        ietLatitude.setText(location.latitude)
                        ietLongitude.setText(location.longitude)
                    }
                }
            }

            showDialog.setOnClickListener {
                val background: Int = (showDialog.background as ColorDrawable).color
                val action = AddContactFragmentDirections.actionAddContactFragmentToColorPickerDialog(background)
                findNavController().navigate(action)
            }
            setFragmentResultListener("requestKey") { requestKey, bundle ->
                val resultColor = bundle.getInt("newBackgroundColor")
                showDialog.setBackgroundColor(resultColor)
            }
        }
    }

    private fun requestPermission(): Boolean {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                return true
            }
            else -> {
                permissionLocation.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                return false
            }
        }
    }

    private fun createMaterialDatePicker() {
        val selectedDate = let {
            if (binding.ietDate.text?.isNotEmpty() == true) {
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

    //endregion

    private fun insertContactInDatabase() {
        val name = binding.ietName.text.toString()
        val lastName = binding.ietLastName.text.toString()
        val dateOfBirth = binding.ietDate.text.toString()
        val favouriteColor = (binding.showDialog.background as ColorDrawable).color
        val favouriteSport = binding.ietSport.text.toString()
        val latitude = binding.ietLatitude.text.toString().toDouble()
        val longitude = binding.ietLongitude.text.toString().toDouble()

        lifecycleScope.launch {
            viewModel.insertContact(
                DetailContactModel(
                    name,
                    lastName,
                    dateOfBirth,
                    favouriteColor,
                    favouriteSport,
                    latitude,
                    longitude
                )
            )
        }
    }
}