package com.androidvoyage.movies.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.androidvoyage.movies.data.api.ApiHelper
import com.androidvoyage.movies.data.api.RetrofitBuilder
import com.androidvoyage.movies.data.repository.MovieRepository
import com.androidvoyage.movies.utils.Resource
import kotlinx.coroutines.Dispatchers


/**
 * Created by Fazal Shaikh on 13/5/22
 */



class DetailViewModel : ViewModel() {

    val movieRepository = MovieRepository(ApiHelper(RetrofitBuilder.apiService))

    fun getMovieDetail(id : Long) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = movieRepository.getMovieDetail(id)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}