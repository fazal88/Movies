package com.androidvoyage.movies.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Fazal Shaikh on 13/5/22
 */


object RetrofitBuilder {

    private const val BASE_URL = "https://api.themoviedb.org/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}