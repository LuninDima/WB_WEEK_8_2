package com.example.wb_week_8_2.viewModel

import com.example.wb_week_8_2.model.Hero

sealed class AppStateList {
    data class Success(val Hero: List<Hero>) : AppStateList()
    data class Error(val error: Throwable) : AppStateList()
    object Loading : AppStateList()
}