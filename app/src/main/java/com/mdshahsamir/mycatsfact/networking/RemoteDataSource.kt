package com.mdshahsamir.mycatsfact.networking

import com.mdshahsamir.mycatsfact.model.Fact

class RemoteDataSource {
    private val apiService = catApiService

    suspend fun fetchFact(): Result<Fact> {
        return try {
            val response = apiService.getFact()
            Result.success(response)
        }catch (e: Exception) {
            Result.failure(e)
        }
    }
}

