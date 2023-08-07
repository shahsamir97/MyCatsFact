package com.mdshahsamir.mycatsfact.ui.factslist

import com.mdshahsamir.mycatsfact.database.CatDao
import com.mdshahsamir.mycatsfact.model.Cat
import com.mdshahsamir.mycatsfact.networking.CatApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface FactListRepository {
    suspend fun getCatFacts(cats: List<Cat>)

    suspend fun getMoreCatFacts(cats: List<Cat>)
}

class FactListRepositoryImpl(
    private val catApiService: CatApiService,
    private val catDao: CatDao,
) : FactListRepository {

    val catsFact = catDao.fetchAll()

    override suspend fun getCatFacts(cats: List<Cat>) {
            try {
                cats.forEach {
                    val fact = catApiService.getFact()
                    it.fact = fact.fact
                }
                catDao.deleteAll()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            withContext(Dispatchers.IO) {
                catDao.insertAll(cats)
            }
    }

    override suspend fun getMoreCatFacts(cats: List<Cat>) {
        try {
            cats.forEach {
                val fact = catApiService.getFact()
                it.fact = fact.fact
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        withContext(Dispatchers.IO) {
            catDao.insertAll(cats)
        }
    }
}
