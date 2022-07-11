package com.example.wb_week_8_2.di

import com.example.wb_week_8_2.repository.LocalDataSource
import com.example.wb_week_8_2.repository.RemoteDataSource
import com.example.wb_week_8_2.repository.Repository
import com.example.wb_week_8_2.repository.RepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideLocalDataSource():LocalDataSource{
        return LocalDataSource()
    }

    @Provides
    fun provideRemoteDataSource():RemoteDataSource{
        return RemoteDataSource()
    }

    @Provides
    fun provideRepository(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource):Repository{
        return RepositoryImpl(remoteDataSource, localDataSource)
    }
}