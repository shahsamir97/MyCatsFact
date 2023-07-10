package com.mdshahsamir.mycatsfact.ui.factslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mdshahsamir.mycatsfact.R
import com.mdshahsamir.mycatsfact.databinding.FragmentFactsListBinding
import com.mdshahsamir.mycatsfact.model.Animal

class FactsListFragment : Fragment(), FactListItemActions {

    private lateinit var binding: FragmentFactsListBinding
    private val viewModel : FactsListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFactsListBinding.inflate(inflater, container, false)

        val adapter = FactsListAdapter(this)
        binding.factRecyclerView.adapter = adapter

        viewModel.catLiveData.observe(viewLifecycleOwner){
            adapter.submitData(it)
        }

        return binding.root
    }

    override fun onClick(animal: Animal) {
        findNavController().navigate(R.id.action_factsListFragment_to_factDetailsFragment)
    }
}