package com.example.rsshool2021_android_task6_music_app.data.local

import android.content.Context

import com.example.rsshool2021_android_task6_music_app.data.entities.Song

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

import java.io.IOException


class LocalDatabase() {


    private fun getJsonDataFromAsset(context: Context): String {
        val jsonString: String
        try {
            jsonString = context.assets.open("playlist.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return " "
        }
        return jsonString
    }

    fun getSongsFromJSON(context: Context): List<Song> {
        val moshi = Moshi.Builder().build()
        val listType = Types.newParameterizedType(List::class.java, Song::class.java)
        val jsonAdapter = moshi.adapter<List<Song>>(listType)
        val result = jsonAdapter.fromJson(getJsonDataFromAsset(context))
        return result!!
    }
}