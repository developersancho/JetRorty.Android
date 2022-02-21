package com.developersancho.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class InitViewModel: ViewModel() {
    private val _appIsReady = MutableStateFlow(false)
    val appIsReady = _appIsReady.asStateFlow()

    init {
        viewModelScope.launch {
            delay(2000)
            _appIsReady.value = true
        }
    }
}