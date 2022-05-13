package com.androidvoyage.movies.data.repository


/**
 * Created by Fazal Shaikh on 13/5/22
 */


import com.androidvoyage.movies.data.api.ApiHelper

class MovieRepository(private val apiHelper: ApiHelper) {

    suspend fun getMovieList(pageNo : Int) = apiHelper.getMovieList(pageNo)

    suspend fun getMovieDetail(id : Long) = apiHelper.getMovieDetail(id)
}