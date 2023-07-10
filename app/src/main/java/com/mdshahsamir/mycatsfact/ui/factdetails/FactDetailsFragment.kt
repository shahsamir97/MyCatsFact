package com.mdshahsamir.mycatsfact.ui.factdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mdshahsamir.mycatsfact.databinding.FragmentFactDetailsBinding

class FactDetailsFragment : Fragment() {

    private lateinit var binding : FragmentFactDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFactDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }
}