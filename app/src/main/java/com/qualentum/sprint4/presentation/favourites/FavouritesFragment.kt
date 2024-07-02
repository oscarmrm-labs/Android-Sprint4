package com.qualentum.sprint4.presentation.favourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.qualentum.sprint4.databinding.FragmentFavouritesBinding
import com.qualentum.sprint4.domain.model.ContactModel
import com.qualentum.sprint4.presentation.contacts.ContactsFragmentDirections
import com.qualentum.sprint4.presentation.common.list.contacts.ContactsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class FavouritesFragment @Inject constructor() : Fragment() {
    private lateinit var binding: FragmentFavouritesBinding
    private val viewModel: FavouritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.favouritesContactsState.collect {
                setupRecyclerView(it)
            }
        }
    }

    private fun setupRecyclerView(contactsList: List<ContactModel>) {
        val adapter = ContactsAdapter(contactsList,
            { contact -> changeScreen(contact) },
            { contact -> addToFavouritesById(contact?.id!!, contact.isFavourite!!) },
            { contact -> deleteContactById(contact?.id) }
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun changeScreen(contact: ContactModel?) {
        val action = ContactsFragmentDirections.actionContactsFragmentToDetailFragment(
            id = contact?.id.toString().toInt()
        )
        findNavController().navigate(action)
    }

    private fun addToFavouritesById(id: Int?, isFavourite: Boolean?) {
        val toggleFavourite = !isFavourite!!
        lifecycleScope.launch {
            viewModel.updateFavouriteContact(id, toggleFavourite)
            viewModel.getAllFavouriteContacts()
        }
    }

    private fun deleteContactById(id: Int?) {
        lifecycleScope.launch {
            viewModel.deleteContact(id)
            viewModel.getAllFavouriteContacts()
        }
    }
}