package com.example.rsshool2021_android_task6_music_app.data.local

import android.content.Context
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.rsshool2021_android_task6_music_app.data.entities.Song
import com.example.rsshool2021_android_task6_music_app.viewmodels.MainActivity
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.IOException
import java.io.InputStream

class LocalDatabase() {


  private  fun getJsonDataFromAsset(context: Context): String {
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