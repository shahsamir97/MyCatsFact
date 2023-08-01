package com.mdshahsamir.mycatsfact.networking

import com.mdshahsamir.mycatsfact.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private var retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

var catApiService: CatApiService = retrofit.create(CatApiService::class.java)
