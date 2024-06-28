package com.qualentum.sprint4.presentation.contacts

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.qualentum.sprint4.R
import com.qualentum.sprint4.domain.model.ContactModel
import com.qualentum.sprint4.databinding.FragmentContactsBinding
import com.qualentum.sprint4.presentation.contacts.list.ContactsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class ContactsFragment @Inject constructor() : Fragment() {

    private val TAG = "TAG"

    private lateinit var binding: FragmentContactsBinding
    private val viewModel: ContactsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactsBinding.inflate(inflater, container, false)

        lifecycleScope.launch {
            viewModel.contactsState.collect {
                setupRecyclerView(it)
                Log.i(TAG, "onCreateView: List of contacts ==> $it")
            }
        }
        return binding.root
    }

    private fun setupRecyclerView(contactsList: List<ContactModel>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter =
            ContactsAdapter(contactsList) { contact -> changeScreen(contact) }
    }

    private fun changeScreen(contact: ContactModel?) {
        val action = ContactsFragmentDirections.actionContactsFragmentToDetailFragment(
            id = contact?.id.toString().toInt()
        )
        findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNavigateToDetail.setOnClickListener {
            findNavController().navigate(R.id.action_contactsFragment_to_detailFragment)
        }
        binding.fabAddContact.setOnClickListener {
            findNavController().navigate(R.id.action_contactsFragment_to_addContactFragment)
        }
    }
}