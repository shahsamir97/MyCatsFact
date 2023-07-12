package com.mdshahsamir.mycatsfact.ui.factslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mdshahsamir.mycatsfact.model.Animal
import com.mdshahsamir.mycatsfact.model.Cat
import com.mdshahsamir.mycatsfact.testdata.generateCatData
import kotlinx.coroutines.launch

class FactsListViewModel(private val factListRepository: FactListRepository) : ViewModel() {

    private val _catLiveData = MutableLiveData<List<Animal>>()
            val  catLiveData : LiveData<List<Animal>>
                get() = _catLiveData

    private val _isDataLoading = MutableLiveData<Boolean>(false)
    val isDataLoading : LiveData<Boolean>
        get() = _isDataLoading

    init {
        populateData()
    }

    private fun populateData() {
        _isDataLoading.value = true
        val data = generateCatData(10)
        viewModelScope.launch {
            data.forEach {
                val fact = factListRepository.getCatFact()
                it.fact = fact.fact
            }
            _catLiveData.postValue(data)
            _isDataLoading.postValue(false)
        }
    }

    fun loadMore(){
        _isDataLoading.value = true
        viewModelScope.launch {
            val newData = generateCatData(10)
            newData.forEach {
                val fact = factListRepository.getCatFact()
                it.fact = fact.fact
            }
            val previousData = _catLiveData.value as ArrayList<Cat>
            previousData.addAll(newData)
            _catLiveData.value = previousData
            _isDataLoading.postValue(false)
        }
    }
}
