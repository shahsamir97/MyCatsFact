package com.mdshahsamir.mycatsfact.ui.factslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class FactListViewModelFactory(private val factListRepository: FactListRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FactsListViewModel::class.java)) {
            return FactsListViewModel(factListRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}