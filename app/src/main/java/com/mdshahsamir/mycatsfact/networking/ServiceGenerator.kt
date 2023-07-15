package com.mdshahsamir.mycatsfact.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://catfact.ninja/"

private var retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

var catApiService: CatApiService = retrofit.create(CatApiService::class.java)
