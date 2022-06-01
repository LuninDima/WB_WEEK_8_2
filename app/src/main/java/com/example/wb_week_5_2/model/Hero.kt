package com.example.wb_week_5_2.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hero(
    val id: String,
    val name: String,
    val image: Image,
    val powerstats: PowerStats
) : Parcelable

@Parcelize
data class Image(
    val url: String,
): Parcelable

@Parcelize
data class PowerStats(
    val intelligence: String,
    val strength: String,
    val durability: String,
    val speed: String,
    val power: String,
    val combat: String
) : Parcelable