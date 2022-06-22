package com.example.wb_week_8_2.utils

import com.example.wb_week_8_2.model.Hero
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

fun convertStringTOListHeroes(serverResponse: String): ArrayList<Hero> {
    val listHeroes: ArrayList<Hero>?
    val gson = GsonBuilder().create()
    listHeroes = gson.fromJson<ArrayList<Hero>>(serverResponse, object :TypeToken<ArrayList<Hero>>(){}.type)
    return listHeroes!!
}