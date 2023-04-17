package com.example.movixcomposeapp.core.presentation.state

/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version UiState, v 0.1 4/16/2023 6:00 AM by Rizky Alfikri Rachmat
 */
sealed class UiState<out R> private constructor() {
    data class Success<out T>(val data: T): UiState<T>()
    data class Error(val error: String): UiState<Nothing>()
    object Loading: UiState<Nothing>()
    object Empty: UiState<Nothing>()
}
