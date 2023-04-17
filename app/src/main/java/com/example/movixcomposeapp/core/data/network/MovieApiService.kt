package com.example.movixcomposeapp.core.data.network

import com.example.movixcomposeapp.feature.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version MovieApiService, v 0.1 4/16/2023 5:36 AM by Rizky Alfikri Rachmat
 */
interface MovieApiService {

    @GET("popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MovieResponse
}