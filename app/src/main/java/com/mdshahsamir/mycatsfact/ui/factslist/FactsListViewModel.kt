package com.mdshahsamir.mycatsfact.ui.factslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mdshahsamir.mycatsfact.model.Animal
import com.mdshahsamir.mycatsfact.testdata.generateCatData

class FactsListViewModel : ViewModel() {

    private val _catLiveData = MutableLiveData<List<Animal>>()
            val  catLiveData : LiveData<List<Animal>>
                get() = _catLiveData

    init {
        populateData()
    }

    private fun populateData() {
        _catLiveData.value = generateCatData()
    }
}
