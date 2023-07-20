package com.mdshahsamir.mycatsfact

import com.mdshahsamir.mycatsfact.model.Fact
import com.mdshahsamir.mycatsfact.ui.factslist.FactListRepository
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

val DUMMY_FACT = Fact("Dummy Fact", length = 10)

object FakeFactListRepositoryImpl : FactListRepository {

    override suspend fun getCatFact() = DUMMY_FACT
}

class RepositoryUnitTest {

    @Test
    fun factListRepository_getOneFactFromRemoteSource() = runTest {
        val fact = FakeFactListRepositoryImpl.getCatFact()
        assertEquals(fact, DUMMY_FACT)
    }
}