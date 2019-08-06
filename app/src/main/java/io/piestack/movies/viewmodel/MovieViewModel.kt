package io.piestack.movies.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.piestack.movies.model.Movie
import io.piestack.movies.model.Order
import io.piestack.movies.repository.MovieRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val mutableMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    val movies: LiveData<List<Movie>> = mutableMovies

    fun fetchMovies(order: Order) {
        viewModelScope.launch {
            runCatching {
                val tripResponse = movieRepository.fetchMovies(order)
                tripResponse.results?.let {
                    movieRepository.saveLocal(it)
                    mutableMovies.value = it
                }
            }.onSuccess {

            }.onFailure { exception ->
                Log.e("fetchMovies", exception.message)
            }
        }
    }

    fun getMovies() {
        viewModelScope.launch {
            runCatching {
                mutableMovies.value = movieRepository.getMovies()
            }.onSuccess {
            }.onFailure { exception ->
                Log.e("getMovies", exception.message)
            }
        }
    }
}