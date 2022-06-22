package com.example.wb_week_8_2.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.wb_week_8_2.App
import com.example.wb_week_8_2.model.DATA_HEROES_PREFERENCES_KEY
import com.example.wb_week_8_2.model.Hero
import com.example.wb_week_8_2.model.NAME_APP_PREFERENCES
import com.example.wb_week_8_2.utils.convertStringTOListHeroes

class LocalDataSource {
    lateinit var listHeroes: ArrayList<Hero>
    private val sharedPreferences: SharedPreferences = App.getSApplicationContext().getSharedPreferences(NAME_APP_PREFERENCES, Context.MODE_PRIVATE)
    fun saveDataHeroesToSharedPreferences(dataHeroes: String){
        sharedPreferences.edit().putString(DATA_HEROES_PREFERENCES_KEY, dataHeroes).apply()
    }

    fun getDataHeroesFromSharedPreferences(): ArrayList<Hero> {
        val dataHeroes = sharedPreferences.getString(DATA_HEROES_PREFERENCES_KEY, "")!!
            listHeroes =  convertStringTOListHeroes(dataHeroes)
        return listHeroes
    }
}