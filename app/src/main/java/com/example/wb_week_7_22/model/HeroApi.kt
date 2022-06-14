package com.example.wb_week_7_22.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroApi {
    @GET("api/all.json")
    fun getHeroData(): Call<ArrayList<Hero>>
}