package com.qualentum.sprint4.presentation.detail

import android.content.Context
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
import com.qualentum.sprint4.presentation.common.interfaces.ToolbarTitleListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    private var toolbarTitleListener: ToolbarTitleListener? = null

    private val args: DetailFragmentArgs by navArgs()
    private var id: Int = 1

    //region fragment lifecycle
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ToolbarTitleListener) {
            toolbarTitleListener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getContactArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        lifecycleScope.launch {
            viewModel.getDetailContact(id)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    //endregion

    private fun getContactArgs() {
        id = args.id
    }

    private fun setToolbarTitle(title: String) {
        toolbarTitleListener?.updateToolbarTitle(title)
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
            tvFavouriteColor.text = it.favouriteColorHex.toString()
            tvFavouriteSport.text = it.favouriteSport
            setToolbarTitle("${it.name} ${it.lastName}")
        }
    }
}