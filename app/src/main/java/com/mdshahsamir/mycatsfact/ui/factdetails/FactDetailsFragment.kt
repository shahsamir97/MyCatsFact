package com.mdshahsamir.mycatsfact.ui.factdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mdshahsamir.mycatsfact.databinding.FragmentFactDetailsBinding
import com.mdshahsamir.mycatsfact.model.Animal

class FactDetailsFragment : Fragment() {

    private lateinit var binding : FragmentFactDetailsBinding
    private val args : FactDetailsFragmentArgs by navArgs()

    private val viewModel : FactDetailsViewModel by viewModels() {
        FactDetailsViewModelFactory(args.animal)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFactDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        populateDate()
    }

    private fun populateDate() {
        binding.animalNameTextView.text = viewModel.animal.name
        binding.animalFactTextView.text = viewModel.animal.fact
        binding.animalBreedTextView.text = viewModel.animal.breed
        Glide.with(requireContext()).load(viewModel.animal.imageLink).into(binding.animalImageView)
    }
}