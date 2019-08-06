package io.piestack.movies.database.dao

import androidx.room.*
import io.piestack.movies.model.Movie

@Dao
abstract class MovieDao {

    @Query("SELECT * FROM movie ORDER BY popularity DESC")
    abstract fun getAll(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(movie: List<Movie>)

    @Delete
    abstract fun delete(movie: Movie)

    @Query("DELETE FROM movie")
    abstract fun deleteAll()
}

