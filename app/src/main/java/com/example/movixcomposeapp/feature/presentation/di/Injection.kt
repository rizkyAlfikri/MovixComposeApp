package com.example.movixcomposeapp.feature.presentation.di

import com.example.movixcomposeapp.core.data.network.NetworkService
import com.example.movixcomposeapp.feature.data.MovieRemoteRepository
import com.example.movixcomposeapp.feature.data.MovieRemoteRepositoryImpl

/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version Injection, v 0.1 4/16/2023 6:41 AM by Rizky Alfikri Rachmat
 */
object Injection {

    fun provideMovieRemoteRepository(): MovieRemoteRepository {
        return MovieRemoteRepositoryImpl(NetworkService.provideApiService())
    }
}