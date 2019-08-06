package io.piestack.movies.repository

import io.piestack.movies.api.MoviesApi
import io.piestack.movies.database.MovieDb
import io.piestack.movies.model.Movie
import io.piestack.movies.model.MovieResponse
import io.piestack.movies.model.Order
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val
    movieApi: MoviesApi,

    private val
    movieDb: MovieDb
) {

    suspend fun fetchMovies(order: Order): MovieResponse {
        return movieApi.getMovies(order)
    }

    suspend fun getMovies(): List<Movie> {
        return movieDb.getAll()
    }

    suspend fun saveLocal(movies: List<Movie>) {
        movieDb.insertAll(movies)
    }
}