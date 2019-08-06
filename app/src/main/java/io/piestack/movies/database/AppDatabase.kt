package io.piestack.movies.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.piestack.movies.database.dao.MovieDao
import io.piestack.movies.model.Movie
import io.piestack.movies.util.GenreConverter

@Database(entities = [Movie::class], version = 1, exportSchema = false)
@TypeConverters(GenreConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val movieDao: MovieDao
}