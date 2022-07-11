package com.example.wb_week_8_2.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wb_week_8_2.repository.Repository
import javax.inject.Inject

class MainViewModelFactory(
     val repository: Repository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListHeroViewModel(repository
        ) as T
    }

}