package com.example.movixcomposeapp.feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movixcomposeapp.feature.data.MovieRemoteRepository
import com.example.movixcomposeapp.feature.presentation.di.Injection

/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version MovieViewModelFactory, v 0.1 4/16/2023 6:27 AM by Rizky Alfikri Rachmat
 */
class MovieViewModelFactory private constructor(private val movieRemoteRepository: MovieRemoteRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(movieRemoteRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class ${modelClass.name}")
    }

    companion object {

        @Volatile
        private var instance: MovieViewModelFactory? = null
        fun getInstance(): MovieViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: MovieViewModelFactory(Injection.provideMovieRemoteRepository())
            }.also {
                instance = it
            }
    }
}