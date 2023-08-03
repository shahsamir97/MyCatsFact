package com.mdshahsamir.mycatsfact.ui.factslist

import android.net.ConnectivityManager
import com.mdshahsamir.mycatsfact.database.CatDao
import com.mdshahsamir.mycatsfact.model.Cat
import com.mdshahsamir.mycatsfact.model.Fact
import com.mdshahsamir.mycatsfact.networking.CatApiService
import com.mdshahsamir.mycatsfact.networking.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.withContext
import java.lang.Exception

interface FactListRepository {
    suspend fun getCatFacts(cats: List<Cat>)

    suspend fun getMoreCatFacts(cats: List<Cat>)
}

class FactListRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val catDao: CatDao,
) : FactListRepository {

    val catsFact = catDao.fetchAll()

    override suspend fun getCatFacts(cats: List<Cat>) {
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
                catDao.deleteAll()
                catDao.insertAll(cats)
            }
    }

    override suspend fun getMoreCatFacts(cats: List<Cat>) {
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
