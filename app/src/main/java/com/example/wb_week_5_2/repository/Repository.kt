package com.example.wb_week_5_2.repository

import com.example.wb_week_5_2.model.Hero

interface Repository {
    fun getHeroFromServer(id: Int, callback: retrofit2.Callback<Hero>)
}