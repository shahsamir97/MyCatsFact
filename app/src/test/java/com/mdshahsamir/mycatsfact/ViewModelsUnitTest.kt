package com.mdshahsamir.mycatsfact

import com.mdshahsamir.mycatsfact.model.Fact
import com.mdshahsamir.mycatsfact.networking.CatApiService
import com.mdshahsamir.mycatsfact.ui.factslist.FactListRepository
import com.mdshahsamir.mycatsfact.ui.factslist.FactsListViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

val DUMMY_FACT = Fact("Dummy Fact", length = 10)

object FakeFactListRepositoryImpl : FactListRepository {

    override suspend fun getCatFact() = Fact("Dummy", 1)
}

class ViewModelsUnitTest {

    @Test
    fun viewModelA_loadsUsers_showsFirstUser() {
        // Given a VM using fake data
        val viewModel = FactsListViewModel(FakeFactListRepositoryImpl) // Kicks off data load on init

        // Verify that the exposed data is correct
        assertEquals(viewModel.catLiveData., 10)
    }
}