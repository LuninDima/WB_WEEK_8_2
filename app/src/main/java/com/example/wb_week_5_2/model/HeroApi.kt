package com.example.wb_week_5_2.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroApi {
    @GET("api.php/{apiKeyValue}/{id}")
    fun getHeroData(
        @Path("apiKeyValue") apiKeyValue: String,
        @Path("id") id: Int
    ): Call<Hero>
}