package com.mdshahsamir.mycatsfact

import com.mdshahsamir.mycatsfact.model.Cat
import com.mdshahsamir.mycatsfact.ui.factdetails.FactDetailsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class FactDetailsViewModelTest {

    @Test
    fun factDetailsViewModel_haveAnimalData() {
        val viewModel = FactDetailsViewModel(Cat())
        assertEquals(viewModel.animal.age, 2)
        assertEquals(viewModel.animal.breed, "Persian")
        assertEquals((viewModel.animal).animalSound(), "Meawww")
    }
}