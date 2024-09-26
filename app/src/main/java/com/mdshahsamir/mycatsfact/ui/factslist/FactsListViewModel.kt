package com.mdshahsamir.mycatsfact.ui.factslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mdshahsamir.mycatsfact.model.Animal
import com.mdshahsamir.mycatsfact.model.Cat
import com.mdshahsamir.mycatsfact.testdata.generateCatData
import kotlinx.coroutines.launch

class FactsListViewModel(
    private val factListRepositoryImpl: FactListRepositoryImpl,
) : ViewModel() {

    val catFacts: LiveData<List<Cat>> = factListRepositoryImpl.catsFact.asLiveData()

    var sortedCats = emptyList<Cat>()

    private val _filteredFacts = MutableLiveData<List<Cat>>()
    val filteredFacts: LiveData<List<Cat>>
        get() = _filteredFacts

    private val _catLiveData = MutableLiveData<List<Animal>>()
            val catLiveData : LiveData<List<Animal>>
                get() = _catLiveData

    private val _isDataLoading = MutableLiveData(false)
    val isDataLoading : LiveData<Boolean>
        get() = _isDataLoading

    private var offset: Int = 1

    fun filterCats(query: String) {
        _isDataLoading.value = true
        viewModelScope.launch {
            _filteredFacts.value = factListRepositoryImpl.fetchFilteredCat(query = query)
        }
        _isDataLoading.value = false
    }

     fun populateData() {
        _isDataLoading.value = true
        val data = generateCatData(10, offset)

        viewModelScope.launch {
             factListRepositoryImpl.getCatFacts(data)
            _isDataLoading.postValue(false)
        }
    }

    fun loadMore() {
        offset += 10
        _isDataLoading.value = true
        val data = generateCatData(10, offset)

        viewModelScope.launch {
            factListRepositoryImpl.getMoreCatFacts(data)
            _isDataLoading.postValue(false)
        }
    }

     fun <R : Comparable<R>> sortCatsBy(
        cats: List<Cat>,
        selector: (Cat) -> R
    ): List<Cat> {
        sortedCats = cats.sortedBy(selector)

        return sortedCats
    }
}
