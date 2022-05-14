package com.androidvoyage.movies.ui.detail

import androidx.lifecycle.ViewModel
import com.androidvoyage.movies.data.api.ApiHelper
import com.androidvoyage.movies.data.api.RetrofitBuilder
import com.androidvoyage.movies.data.repository.MovieRepository


/**
 * Created by Fazal Shaikh on 13/5/22
 */



class DetailViewModel : ViewModel() {

    val movieRepository = MovieRepository(ApiHelper(RetrofitBuilder.apiService))


}