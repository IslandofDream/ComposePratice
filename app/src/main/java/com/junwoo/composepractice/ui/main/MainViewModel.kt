package com.junwoo.composepractice.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private var count = 0
    private val _uiState = MutableStateFlow<UiState<Int>>(UiState.Init)
    val uiState = _uiState.asStateFlow()


    fun plusCount() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            delay(200)
            _uiState.value = UiState.Success(count++)
        }
    }
}