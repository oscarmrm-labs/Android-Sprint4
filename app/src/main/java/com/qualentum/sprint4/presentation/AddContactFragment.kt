package com.qualentum.sprint4.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.qualentum.sprint4.data.model.Contact
import com.qualentum.sprint4.databinding.FragmentAddContactBinding

class AddContactFragment : Fragment() {
    private lateinit var binding: FragmentAddContactBinding
    private lateinit var viewModel: AddContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //param1 = it.getString(ARG_PARAM1)
            //param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddContactBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(AddContactViewModel::class)

        binding.btnAddContact.setOnClickListener {
            insertContactInDatabse()
        }

        return binding.root
    }

    private fun insertContactInDatabse() {
        val name = binding.ietName.text.toString()
        val age = binding.ietAge.text.toString().toInt()

        viewModel.insertContact(Contact(name, age))
    }
}