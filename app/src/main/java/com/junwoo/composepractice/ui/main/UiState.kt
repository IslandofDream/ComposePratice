package com.junwoo.composepractice.ui.main


sealed class UiState<out T> {   // Use Sealed Class
    object Init : UiState<Nothing>()
    //초기상태
    object Loading : UiState<Nothing>()
    //로딩상태

    object Empty : UiState<Nothing>()
    // empty
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val error: String) : UiState<Nothing>()
}