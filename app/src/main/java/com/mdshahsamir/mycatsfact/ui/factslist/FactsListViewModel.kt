package com.mdshahsamir.mycatsfact.ui.factslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mdshahsamir.mycatsfact.model.Animal
import com.mdshahsamir.mycatsfact.model.Cat
import com.mdshahsamir.mycatsfact.model.Fact
import com.mdshahsamir.mycatsfact.testdata.generateCatData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FactsListViewModel(private val factListRepository: FactListRepository) : ViewModel() {

    private val _catLiveData = MutableLiveData<List<Animal>>()
            val  catLiveData : LiveData<List<Animal>>
                get() = _catLiveData

    private val _isDataLoading = MutableLiveData(false)
    val isDataLoading : LiveData<Boolean>
        get() = _isDataLoading

    private var offset: Int = 1

    init {
        populateData()
    }

     fun populateData() {
        _isDataLoading.value = true
        val data = generateCatData(10, offset)

        viewModelScope.launch {
            data.forEach {
                val fact = factListRepository.getCatFact()
                it.fact = fact.fact
            }
            data.distinctBy { it.fact }
            _catLiveData.postValue(data)
            _isDataLoading.postValue(false)
        }
    }

    fun loadMore() {
        offset += 10
        _isDataLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val newData = generateCatData(10, offset)

            newData.forEach {
                val fact = factListRepository.getCatFact()
                it.fact = fact.fact
            }

            val previousData = _catLiveData.value?.toMutableList()?: mutableListOf()

            previousData.addAll(newData)
            previousData.distinctBy { it.uniqueKey() }
            _catLiveData.postValue(previousData)
            _isDataLoading.postValue(false)
        }
    }
}
