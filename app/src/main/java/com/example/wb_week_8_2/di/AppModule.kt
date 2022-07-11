package com.example.wb_week_8_2.di

import android.content.Context
import com.example.wb_week_8_2.App
import com.example.wb_week_8_2.repository.Repository
import com.example.wb_week_8_2.viewModel.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {
    @Provides
    fun app(): App{
        return app
    }

    @Provides
    fun provideContext(): Context {
        return app.applicationContext
    }

    @Provides
    fun provideMainViewModelFactory(
        repository: Repository
    ):MainViewModelFactory{
return MainViewModelFactory(repository = repository)
    }

}
