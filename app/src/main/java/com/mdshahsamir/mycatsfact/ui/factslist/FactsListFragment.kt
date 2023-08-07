package com.mdshahsamir.mycatsfact.ui.factslist

import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
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
import com.bumptech.glide.Glide
import com.mdshahsamir.mycatsfact.database.AppDatabase
import com.mdshahsamir.mycatsfact.database.CatDao
import com.mdshahsamir.mycatsfact.databinding.FragmentFactsListBinding
import com.mdshahsamir.mycatsfact.model.Animal
import com.mdshahsamir.mycatsfact.model.Cat
import com.mdshahsamir.mycatsfact.networking.RemoteDataSource

class ServiceLocator(private val context: Context) {
    fun getFactListRepository(): FactListRepository {
        return FactListRepositoryImpl(getRemoteDataSource(), getCatDao())
    }

    private fun getRemoteDataSource(): RemoteDataSource {
        return RemoteDataSource()
    }

    private fun getCatDao(): CatDao {
        return AppDatabase.getDatabase(context).catDao()
    }
}

class FactsListFragment : Fragment(), FactListItemActions {

    private lateinit var binding: FragmentFactsListBinding
    private val adapter by lazy { FactsListAdapter(this, Glide.with(requireContext())) }
    private var gridLayoutNumberOfColumns = 2

    private val viewModel: FactsListViewModel by viewModels {
        FactListViewModelFactory(
            ServiceLocator(requireContext()).getFactListRepository()
        )
    }

    private val connectivityManager: ConnectivityManager by lazy {
        requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFactsListBinding.inflate(inflater, container, false)

        initRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        if(connectivityManager.activeNetwork != null) {
            viewModel.populateData()
        }
    }

    private fun initObservers() {
        viewModel.catFacts().observe(viewLifecycleOwner) {
            binding.factRecyclerView.visibility = if (it.isNotEmpty()) View.VISIBLE else View.GONE
            adapter.submitList(it)
        }

        viewModel.isDataLoading.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        gridLayoutNumberOfColumns =
            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) 4 else 2
        binding.factRecyclerView.layoutManager =
            GridLayoutManager(requireContext(), gridLayoutNumberOfColumns)
    }

    private fun initRecyclerView() {
        binding.factRecyclerView.layoutManager =
            GridLayoutManager(requireContext(), gridLayoutNumberOfColumns)
        binding.factRecyclerView.adapter = adapter
        binding.factRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?

                if (!viewModel.isDataLoading.value!!) {
                    if (linearLayoutManager != null
                        && linearLayoutManager.findLastCompletelyVisibleItemPosition() == viewModel.catFacts().value?.size?.minus(
                            1
                        )
                    ) {
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

        val action =
            FactsListFragmentDirections.actionFactsListFragmentToFactDetailsFragment(animal)
        findNavController().navigate(action, extraInfoForSharedElement)
    }
}
