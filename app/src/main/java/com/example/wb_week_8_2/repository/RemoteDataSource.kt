package com.example.wb_week_8_2.repository

import com.example.wb_week_8_2.model.*
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {
    private val heroApi = Retrofit.Builder()
        .baseUrl(SUPERHERO_API_BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create(GsonBuilder().setLenient().create())
        ).build().create(HeroApi::class.java)

    fun getHeroDetails(callBack: retrofit2.Callback<ArrayList<Hero>>) {
        heroApi.getHeroData().enqueue(callBack)
    }




}