package com.example.wb_week_8_2.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hero(
    val id: String,
    val name: String,
    val images: Image,
    val powerstats: PowerStats
) : Parcelable

@Parcelize
data class Image(
    val xs: String,
    val sm: String
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