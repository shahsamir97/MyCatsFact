package com.mdshahsamir.mycatsfact

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mdshahsamir.mycatsfact.model.Animal
import com.mdshahsamir.mycatsfact.ui.factslist.FactsListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FactsListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: FactsListViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = FactsListViewModel(FakeFactListRepositoryImpl, connectivityManager)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun factsViewModel_loadsUsers_showsAllFact() = runTest {
        val observer = Observer<List<Animal>> {}
        viewModel.catLiveData.observeForever(observer)
        val value = viewModel.catLiveData.value
        assertEquals(10, value?.size)

        viewModel.catLiveData.removeObserver(observer)
    }

    @Test
    fun factViewModel_loadMoreData() = runTest {
        val observer = Observer<List<Animal>> {}
        viewModel.loadMore()
        viewModel.catLiveData.observeForever(observer)
        val value = viewModel.catLiveData.value
        assertEquals(20, value?.size)

        viewModel.catLiveData.removeObserver(observer)
    }
}
