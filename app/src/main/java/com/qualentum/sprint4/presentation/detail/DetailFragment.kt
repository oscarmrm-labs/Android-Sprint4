package com.qualentum.sprint4.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.qualentum.sprint4.databinding.FragmentDetailBinding
import com.qualentum.sprint4.domain.model.DetailContactModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    val args: DetailFragmentArgs by navArgs()
    private var id: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getContactArgs()
        viewModel.setContactId(id)
    }

    private fun getContactArgs() {
        id = args.id
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        observeViewModel()
        return binding.root
    }

    private fun observeViewModel() {
        lifecycleScope.apply {
            launch {
                viewModel.loadingState.collect {
                    // TODO: Mostrar Loading
                }
            }
            launch {
                viewModel.contactState.collect {
                    bindDetailContact(it)
                }
            }
        }
    }

    private fun bindDetailContact(it: DetailContactModel) {
        binding.apply {
            tvName.text = it.name
            tvLastName.text = it.lastName
            tvDateOfBirth.text = it.dateOfBirth
            tvFavouriteColor.text = it.favouriteColorHex
            tvFavouriteSport.text = it.favouriteSport
        }
    }
}