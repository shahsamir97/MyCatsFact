package com.mdshahsamir.mycatsfact.ui.factslist

import com.mdshahsamir.mycatsfact.database.CatDao
import com.mdshahsamir.mycatsfact.model.Cat
import com.mdshahsamir.mycatsfact.networking.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface FactListRepository {
    fun getCatFacts(): Flow<List<Cat>>
    suspend fun fetchCatFacts(cats: List<Cat>)
    suspend fun fetchMoreCatFacts(cats: List<Cat>)
}

class FactListRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val catDao: CatDao,
) : FactListRepository {

    private val catsFact = catDao.fetchAll()

    override fun getCatFacts() = catsFact

    override suspend fun fetchCatFacts(cats: List<Cat>) {
            try {
                cats.forEach {
                    val fact = remoteDataSource.fetchFact()
                    if (fact.isSuccess) {
                        it.fact = fact.getOrThrow().fact
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            withContext(Dispatchers.IO) {
                //catDao.deleteAll()
                catDao.insertAll(cats)
            }
    }

    override suspend fun fetchMoreCatFacts(cats: List<Cat>) {
        try {
            cats.forEach {
                val fact = remoteDataSource.fetchFact()
                if (fact.isSuccess){
                    it.fact = fact.getOrThrow().fact
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        withContext(Dispatchers.IO) {
            catDao.insertAll(cats)
        }
    }
}
