package com.androidvoyage.movies.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidvoyage.movies.data.api.ApiHelper
import com.androidvoyage.movies.data.api.RetrofitBuilder
import com.androidvoyage.movies.data.model.MovieItem
import com.androidvoyage.movies.data.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * Created by Fazal Shaikh on 13/5/22
 */


class ListViewModel : ViewModel() {

    val movieRepository = MovieRepository(ApiHelper(RetrofitBuilder.apiService))

    val errorMessage = MutableLiveData("")
    val listMovies = MutableLiveData<List<MovieItem>>()
    val clickedMovie = MutableLiveData<MovieItem?>()
    var pageNo = 1

    val adapter = MovieListAdapter(MovieListAdapter.MovieItemClickListener {
        it?.let {
            clickedMovie.postValue(it)
        }
    })

    init {
        getMovieList(1)
    }

    fun getMovieList(page : Int = pageNo.plus(1)) {
        pageNo = page
        CoroutineScope(Dispatchers.Default).launch {
            errorMessage.postValue("Loading...")
            try {
                val response = movieRepository.getMovieList(page)
                listMovies.postValue(response.results)
                if (response.results.isEmpty()) {
                    errorMessage.postValue("No Data Found.")
                } else {
                    errorMessage.postValue("")
                }
            } catch (exception: Exception) {
                errorMessage.postValue(exception.message ?: "Error Occurred!")
            }
        }
    }

    fun resetClickedMovie() {
        clickedMovie.postValue(null)
    }
}