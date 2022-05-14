package com.androidvoyage.movies.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.androidvoyage.movies.data.api.ApiHelper
import com.androidvoyage.movies.data.api.RetrofitBuilder
import com.androidvoyage.movies.data.model.MovieDetailResponse
import com.androidvoyage.movies.data.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * Created by Fazal Shaikh on 13/5/22
 */



class DetailViewModel : ViewModel() {

    val movieRepository = MovieRepository(ApiHelper(RetrofitBuilder.apiService))
    val movieDetails = MutableLiveData<MovieDetailResponse>()

    val errorMessage = MutableLiveData("")
    val isLoading = MutableLiveData(false)


    fun getMovieDetail(id : Long) {
        CoroutineScope(Dispatchers.Default).launch {
            errorMessage.postValue("Loading...")
            isLoading.postValue(true)
            try {
                val response = movieRepository.getMovieDetail(id)
                delay(2000)
                movieDetails.postValue(response)
                errorMessage.postValue("")
                isLoading.postValue(false)
            } catch (exception: Exception) {
                errorMessage.postValue(exception.message ?: "Error Occurred!")
                isLoading.postValue(false)
            }
        }
    }


}