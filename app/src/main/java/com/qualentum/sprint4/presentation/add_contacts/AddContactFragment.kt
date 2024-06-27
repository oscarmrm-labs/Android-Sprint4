package com.qualentum.sprint4.presentation.add_contacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import com.qualentum.sprint4.R
import com.qualentum.sprint4.databinding.FragmentAddContactBinding
import com.qualentum.sprint4.domain.model.DetailContactModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class AddContactFragment : Fragment() {
    private lateinit var binding: FragmentAddContactBinding
    private lateinit var viewModel: AddContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[AddContactViewModel::class]
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

        activity?.let {
            datePicker.show(it.supportFragmentManager, "MATERIAL_DATE_PICKER")
        }

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

        viewModel.insertContact(DetailContactModel(name, lastName, dateOfBirth, favouriteColor, favouriteSport))
    }
}