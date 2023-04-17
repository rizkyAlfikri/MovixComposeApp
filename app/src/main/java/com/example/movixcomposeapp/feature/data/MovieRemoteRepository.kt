package com.example.movixcomposeapp.feature.data

import com.example.movixcomposeapp.BuildConfig
import com.example.movixcomposeapp.core.data.network.MovieApiService
import com.example.movixcomposeapp.core.data.network.utils.NetworkErrorParser
import com.example.movixcomposeapp.feature.data.model.MovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version MovieRemoteRepository, v 0.1 4/16/2023 5:26 AM by Rizky Alfikri Rachmat
 */
interface MovieRemoteRepository {

    suspend fun getPopularMovies(): Flow<MovieResponse>
}

class MovieRemoteRepositoryImpl(private val movieApiService: MovieApiService) :
    MovieRemoteRepository {

    override suspend fun getPopularMovies(): Flow<MovieResponse> = flow {
        kotlin.runCatching {
            val response = movieApiService.getPopularMovies(BuildConfig.API_KEY)
            emit(response)
        }.onFailure {
            val error = NetworkErrorParser(it).parse()
            emit(MovieResponse().apply {
                this.success = error.success
                this.statusCode = error.statusCode
                this.statusMessage = error.statusMessage
            })
        }
    }
}