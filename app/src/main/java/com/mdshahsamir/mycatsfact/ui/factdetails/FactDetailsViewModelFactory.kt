package com.mdshahsamir.mycatsfact.ui.factdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mdshahsamir.mycatsfact.model.Animal
import java.lang.IllegalArgumentException

class FactDetailsViewModelFactory(private val animal : Animal) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FactDetailsViewModel::class.java)){
            return FactDetailsViewModel(animal) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
