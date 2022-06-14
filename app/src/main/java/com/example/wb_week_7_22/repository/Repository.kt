package com.example.wb_week_7_22.repository

import com.example.wb_week_7_22.model.Hero


interface Repository {
    fun getHeroFromServer(callback: retrofit2.Callback<ArrayList<Hero>>)

    fun getHeroFromSharedPreferences(): ArrayList<Hero>

    fun saveDataHeroesToSharedPreferences(dataHeroes: String)
}