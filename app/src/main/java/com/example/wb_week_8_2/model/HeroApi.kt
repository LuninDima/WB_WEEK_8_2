package com.example.wb_week_8_2.model

import retrofit2.Call
import retrofit2.http.GET

interface HeroApi {
    @GET("api/all.json")
    fun getHeroData(): Call<ArrayList<Hero>>
}