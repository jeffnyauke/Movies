package io.piestack.movies.api

import io.piestack.movies.BuildConfig
import io.piestack.movies.model.MovieResponse
import io.piestack.movies.model.Order
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

class MoviesApiClient(retrofit: Retrofit) : MoviesApi {

    interface Service {

        @GET("discover/movie")
        fun getMovies(
            @Query("api_key") apiKey: String,
            @Query("sort_by") order: String
        ): Deferred<MovieResponse>

    }

    private val service = retrofit.create(Service::class.java)

    override suspend fun getMovies(order: Order): MovieResponse {
        return withContext(IO) {
            service.getMovies(BuildConfig.TMDB_API_KEY, order.url).await()
        }
    }
}