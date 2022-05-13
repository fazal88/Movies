package com.androidvoyage.movies.data.api


/**
 * Created by Fazal Shaikh on 13/5/22
 */

class ApiHelper(private val apiService: ApiService) {

    suspend fun getMovieList(pageNo : Int) = apiService.getMovieList(pageNo)

    suspend fun getMovieDetail(movieId : Long) = apiService.getMovieDetail(movieId = movieId)
}