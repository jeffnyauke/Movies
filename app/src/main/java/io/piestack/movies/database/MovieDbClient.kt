package io.piestack.movies.database

import io.piestack.movies.database.dao.MovieDao
import io.piestack.movies.model.Movie
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class MovieDbClient(private val movieDao: MovieDao) : MovieDb {

    override suspend fun getAll(): List<Movie> {
        return withContext(IO) {
            movieDao.getAll()
        }
    }

    override suspend fun insert(movie: Movie) {
        withContext(IO) {
            movieDao.insert(movie)
        }
    }

    override suspend fun insertAll(movies: List<Movie>) {
        withContext(IO) {
            movieDao.insertAll(movies)
        }
    }

    override suspend fun delete(movie: Movie) {
        withContext(IO) {
            movieDao.delete(movie)
        }
    }

    override suspend fun deleteAll() {
        withContext(IO) {
            movieDao.deleteAll()
        }
    }
}