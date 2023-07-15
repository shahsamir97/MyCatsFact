package com.mdshahsamir.mycatsfact.ui.factslist

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mdshahsamir.mycatsfact.databinding.FragmentFactsListBinding
import com.mdshahsamir.mycatsfact.model.Animal
import com.mdshahsamir.mycatsfact.model.Cat
import com.mdshahsamir.mycatsfact.networking.catApiService

class FactsListFragment : Fragment(), FactListItemActions {

    private lateinit var binding: FragmentFactsListBinding
    private val adapter = FactsListAdapter(this)

    private val viewModel : FactsListViewModel by viewModels {
        FactListViewModelFactory(FactListRepository(catApiService))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFactsListBinding.inflate(inflater, container, false)

        initRecyclerView()
        initObservers()

        return binding.root
    }

    private fun initObservers() {
        viewModel.catLiveData.observe(viewLifecycleOwner){
            binding.factRecyclerView.visibility = if (it.isNotEmpty()) View.VISIBLE else View.GONE
            adapter.submitData(it)
        }

        viewModel.isDataLoading.observe(viewLifecycleOwner){
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun initRecyclerView(){
        val columns = if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 4 else 2
        binding.factRecyclerView.layoutManager = GridLayoutManager(requireContext(), columns)
        binding.factRecyclerView.adapter = adapter

        binding.factRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?

                if (!viewModel.isDataLoading.value!!) {
                    if (linearLayoutManager != null
                        && linearLayoutManager.findLastCompletelyVisibleItemPosition()
                        == viewModel.catLiveData.value?.size?.minus(1)) {

                        viewModel.loadMore()
                    }
                }
            }
        })

        postponeEnterTransition()
        binding.factRecyclerView.doOnPreDraw {
            startPostponedEnterTransition()
        }
    }

    override fun onClick(animal: Animal, view: View) {
        val extraInfoForSharedElement = FragmentNavigatorExtras(
            view as ImageView to (animal as Cat).imageLink
        )

        val action = FactsListFragmentDirections.actionFactsListFragmentToFactDetailsFragment(animal)
        findNavController().navigate(action, extraInfoForSharedElement)
    }
}
