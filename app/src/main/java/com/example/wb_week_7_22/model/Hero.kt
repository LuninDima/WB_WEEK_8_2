package com.example.wb_week_7_22.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
@JsonClass(generateAdapter = true)
@Parcelize
data class Hero(
    val id: String,
    val name: String,
    val images: Image,
    val powerstats: PowerStats
) : Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Image(
    val xs: String,
    val sm: String
): Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class PowerStats(
    val intelligence: String,
    val strength: String,
    val durability: String,
    val speed: String,
    val power: String,
    val combat: String
) : Parcelable