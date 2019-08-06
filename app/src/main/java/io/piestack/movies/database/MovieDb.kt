package io.piestack.movies.database

import io.piestack.movies.model.Movie

interface MovieDb {

    suspend fun getAll(): List<Movie>

    suspend fun insert(movie: Movie)

    suspend fun insertAll(movies: List<Movie>)

    suspend fun delete(movie: Movie)

    suspend fun deleteAll()
}