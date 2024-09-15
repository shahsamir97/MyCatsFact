package com.mdshahsamir.mycatsfact.ui.factslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mdshahsamir.mycatsfact.model.Animal
import com.mdshahsamir.mycatsfact.testdata.generateCatData
import kotlinx.coroutines.launch

class FactsListViewModel(
    private val factListRepositoryImpl: FactListRepositoryImpl,
) : ViewModel() {

    val catFacts = factListRepositoryImpl.catsFact.asLiveData()

    private val _catLiveData = MutableLiveData<List<Animal>>()
            val  catLiveData : LiveData<List<Animal>>
                get() = _catLiveData

    private val _isDataLoading = MutableLiveData(false)
    val isDataLoading : LiveData<Boolean>
        get() = _isDataLoading

    private var offset: Int = 1

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
}
