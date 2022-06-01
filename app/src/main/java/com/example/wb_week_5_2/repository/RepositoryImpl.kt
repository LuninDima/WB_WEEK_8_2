package com.example.wb_week_5_2.repository

import com.example.wb_week_5_2.model.Hero

class RepositoryImpl(private val remoteDataSource: RemoteDataSource): Repository {
    override fun getHeroFromServer(id: Int, callback: retrofit2.Callback<Hero>) {
        remoteDataSource.getHeroDetails(id, callback)
    }

}
