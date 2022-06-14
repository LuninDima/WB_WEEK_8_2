package com.example.wb_week_7_22.utils

import com.example.wb_week_7_22.model.Hero
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

fun convertStringTOListHeroes(serverResponse: String): ArrayList<Hero> {
    val listHeroes: ArrayList<Hero>?
    val moshi: Moshi = Moshi.Builder().build()
    val listType = Types.newParameterizedType(List::class.java, Hero::class.java)
    val jsonAdapter: JsonAdapter<ArrayList<Hero>> = moshi.adapter<ArrayList<Hero>?>(listType).lenient()
    listHeroes = jsonAdapter.fromJson(serverResponse)
    return listHeroes!!
}