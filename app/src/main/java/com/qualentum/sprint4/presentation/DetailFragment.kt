package com.qualentum.sprint4.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.qualentum.sprint4.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    val args: DetailFragmentArgs by navArgs()
    private lateinit var name: String
    private var age: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getContactArgs()
    }

    private fun getContactArgs() {
        name = args.name
        age = args.age
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        bindContactDetail()
        return binding.root
    }

    private fun bindContactDetail() {
        binding.tvName.text = name
        binding.tvAge.text = age.toString()
    }
}