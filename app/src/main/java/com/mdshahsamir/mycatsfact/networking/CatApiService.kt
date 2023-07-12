package com.mdshahsamir.mycatsfact.networking

import com.mdshahsamir.mycatsfact.model.Fact
import retrofit2.http.GET

interface CatApiService {

    @GET("fact")
    suspend fun getFact(): Fact
}