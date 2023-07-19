package com.mdshahsamir.mycatsfact

import com.mdshahsamir.mycatsfact.model.Cat
import com.mdshahsamir.mycatsfact.model.Fact
import com.mdshahsamir.mycatsfact.ui.factdetails.FactDetailsViewModel
import com.mdshahsamir.mycatsfact.ui.factslist.FactListRepository
import com.mdshahsamir.mycatsfact.ui.factslist.FactsListViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

class ViewModelsUnitTest {

    @Test
    fun factsViewModel_loadsUsers_showsAllFact() {
        val viewModel = FactsListViewModel(FakeFactListRepositoryImpl)

        assertEquals(viewModel.catLiveData., 10)
    }

    @Test
    fun factDetailsViewModel_haveAnimalData(){
        val viewModel = FactDetailsViewModel(Cat())
        assertEquals(viewModel.animal.age, 2)
        assertEquals(viewModel.animal.breed, "Persian")
        assertEquals((viewModel.animal).animalSound(), "Meawww")
    }
}