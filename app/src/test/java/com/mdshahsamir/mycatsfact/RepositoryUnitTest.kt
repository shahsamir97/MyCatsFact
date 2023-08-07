package com.mdshahsamir.mycatsfact

import com.mdshahsamir.mycatsfact.model.Cat
import com.mdshahsamir.mycatsfact.model.Fact
import com.mdshahsamir.mycatsfact.testdata.generateCatData
import com.mdshahsamir.mycatsfact.ui.factslist.FactListRepository
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

val DUMMY_FACT = Fact("Dummy Fact", length = 10)

object FakeFactListRepositoryImpl : FactListRepository {

    override suspend fun getCatFacts(cats: List<Cat>) {
        TODO("Not yet implemented")
    }

    override suspend fun getMoreCatFacts(cats: List<Cat>) {
        TODO("Not yet implemented")
    }
}

class RepositoryUnitTest {

    @Test
    fun factListRepository_getOneFactFromRemoteSource() = runTest {
        val fact = FakeFactListRepositoryImpl.getCatFacts(generateCatData(10, 0))
        assertEquals(fact, DUMMY_FACT)
    }
}