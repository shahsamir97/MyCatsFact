package com.mdshahsamir.mycatsfact.ui.factslist

import com.mdshahsamir.mycatsfact.model.Fact
import com.mdshahsamir.mycatsfact.networking.CatApiService

interface FactListRepository {
    suspend fun getCatFact(): Fact
}

class FactListRepositoryImpl(private val catApiService: CatApiService) : FactListRepository {

    override suspend fun getCatFact() = catApiService.getFact()
}



