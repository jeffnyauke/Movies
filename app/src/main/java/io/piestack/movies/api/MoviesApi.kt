package io.piestack.movies.api

import io.piestack.movies.model.MovieResponse
import io.piestack.movies.model.Order

interface MoviesApi {

    suspend fun getMovies(order: Order): MovieResponse
}