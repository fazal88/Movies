package com.androidvoyage.movies.data.api

import com.androidvoyage.movies.data.model.MovieDetailResponse
import com.androidvoyage.movies.data.model.MovieItem
import com.androidvoyage.movies.data.model.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by Fazal Shaikh on 13/5/22
 */


interface ApiService {

    @GET("3/movie/popular?api_key=138b1ceb0617d71b6439bd33fb760ebc&language=en-US")
    suspend fun getMovieList(@Query("page") pageNo : Int): MovieListResponse

    @GET("3/movie/{id}?api_key=138b1ceb0617d71b6439bd33fb760ebc&language=en-US")
    suspend fun getMovieDetail(@Path("id") movieId : Long): MovieDetailResponse

}