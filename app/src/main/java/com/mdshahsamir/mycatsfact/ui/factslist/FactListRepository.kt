package com.mdshahsamir.mycatsfact.ui.factslist

import com.mdshahsamir.mycatsfact.database.CatDao
import com.mdshahsamir.mycatsfact.model.Cat
import com.mdshahsamir.mycatsfact.networking.CatApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.SortedSet

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
            val uniqueFacts: SortedSet<Cat> = sortedSetOf()
            cats.forEach {
                val fact = catApiService.getFact()
                it.fact = fact.fact
                uniqueFacts.add(it) // Add each cat to the sorted set
            }

            withContext(Dispatchers.IO) {
                catDao.deleteAll()
                catDao.insertAll(uniqueFacts.toList()) // Convert the SortedSet to a List and insert into the database
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun getMoreCatFacts(cats: List<Cat>) {
        try {
            val uniqueFacts: SortedSet<Cat> = sortedSetOf()
            cats.forEach {
                val fact = catApiService.getFact()
                it.fact = fact.fact
                uniqueFacts.add(it)
            }

            withContext(Dispatchers.IO) {
                catDao.insertAll(cats)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
