package com.example.movixcomposeapp.feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movixcomposeapp.core.presentation.state.UiState
import com.example.movixcomposeapp.feature.data.MovieRemoteRepository
import com.example.movixcomposeapp.feature.presentation.model.MovieModel
import com.example.movixcomposeapp.feature.presentation.model.toMovieModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version MovieViewModel, v 0.1 4/16/2023 5:58 AM by Rizky Alfikri Rachmat
 */
class MovieViewModel(private val movieRemoteRepository: MovieRemoteRepository) : ViewModel() {

    private val _moviesPopularState = MutableStateFlow<UiState<List<MovieModel>>>(UiState.Loading)
    val moviesPopularState: StateFlow<UiState<List<MovieModel>>>
        get() = _moviesPopularState

    fun getMoviesPopular() {
        viewModelScope.launch {
            movieRemoteRepository.getPopularMovies()
                .catch { error ->
                    _moviesPopularState.value = UiState.Error(error.message.toString())
                }.collect { result ->
                    when {
                        result.success == false -> {
                            _moviesPopularState.value =
                                UiState.Error(result.statusMessage.toString())
                        }

                        result.results.isNullOrEmpty() -> {
                            _moviesPopularState.value =
                                UiState.Error(result.statusMessage.toString())
                        }

                        else -> {
                            _moviesPopularState.value =
                                UiState.Success(result.results.map { it.toMovieModel() })
                        }
                    }
                }
        }
    }
}