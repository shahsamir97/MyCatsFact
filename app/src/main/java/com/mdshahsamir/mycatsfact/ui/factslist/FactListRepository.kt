package com.mdshahsamir.mycatsfact.ui.factslist

import com.mdshahsamir.mycatsfact.networking.CatApiService

class FactListRepository(private val catApiService: CatApiService) {

    suspend fun getCatFact() = catApiService.getFact()
}
