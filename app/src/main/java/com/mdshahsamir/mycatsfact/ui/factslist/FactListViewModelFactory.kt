package com.mdshahsamir.mycatsfact.ui.factslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FactListViewModelFactory(
    private val factListRepositoryImpl: FactListRepositoryImpl) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FactsListViewModel::class.java)) {
            return FactsListViewModel(factListRepositoryImpl) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
