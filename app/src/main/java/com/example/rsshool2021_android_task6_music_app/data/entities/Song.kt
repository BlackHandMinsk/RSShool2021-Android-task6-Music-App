package com.example.rsshool2021_android_task6_music_app.data.entities


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Song (
    @Json(name = "mediaId")
    val mediaId:String = "",
    @Json(name = "title")
    val tittle:String = "",
    @Json(name = "artist")
    val artist:String = "",
    @Json(name = "bitmapUri")
    val bitmapUri:String = "",
    @Json(name = "trackUri")
    val trackUri:String = "",
)