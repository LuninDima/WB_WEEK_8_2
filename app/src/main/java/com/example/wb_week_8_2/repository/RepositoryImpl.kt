package com.example.wb_week_8_2.repository

import com.example.wb_week_8_2.model.Hero

class RepositoryImpl(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource): Repository {
    override fun getHeroFromServer(callback: retrofit2.Callback<ArrayList<Hero>>) {
        remoteDataSource.getHeroDetails(callback)
    }

    override fun getHeroFromSharedPreferences(): ArrayList<Hero> {
       return localDataSource.getDataHeroesFromSharedPreferences()
    }

    override fun saveDataHeroesToSharedPreferences(dataHeroes: String) {
        localDataSource.saveDataHeroesToSharedPreferences(dataHeroes)
    }

}
