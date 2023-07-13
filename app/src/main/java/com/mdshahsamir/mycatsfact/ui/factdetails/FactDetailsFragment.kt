package com.mdshahsamir.mycatsfact.ui.factdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mdshahsamir.mycatsfact.R
import com.mdshahsamir.mycatsfact.databinding.FragmentFactDetailsBinding
import com.mdshahsamir.mycatsfact.model.Cat
import com.mdshahsamir.mycatsfact.utils.getDottedText

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

        (viewModel.animal as Cat).let { cat ->
            binding.animalNameTextView.text = cat.name
            binding.animalFactTextView.text = cat.fact

            binding.animalBreedTextView.text = getDottedText(
                listOf(
                    getString(R.string.breed) + cat.breed,
                    getString(R.string.age) + cat.age.toString(),
                    getString(R.string.weight) + cat.weight.toString()
                )
            )

            Glide.with(requireContext()).load(cat.imageLink)
                .into(binding.animalImageView)

            binding.eatButton.setOnClickListener {
                Toast.makeText(
                    requireContext(),
                    cat.animalFavoriteFood(),
                    Toast.LENGTH_SHORT
                ).show()
            }

            binding.sleepButton.setOnClickListener {
                Toast.makeText(
                    requireContext(),
                    cat.animalSleepCycle(),
                    Toast.LENGTH_SHORT
                ).show()
            }

            binding.soundButton.setOnClickListener {
                Toast.makeText(requireContext(), cat.animalSound(), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}