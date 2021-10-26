package com.example.rsshool2021_android_task6_music_app.data.remote

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.rsshool2021_android_task6_music_app.data.entities.Song
import com.example.rsshool2021_android_task6_music_app.data.local.LocalDatabase
import com.example.rsshool2021_android_task6_music_app.other.Constants.SONG_COLLECTION
import com.example.rsshool2021_android_task6_music_app.viewmodels.MainActivity
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import kotlin.math.log

class MusicDatabase(private val context: Context){


    private val firestore = FirebaseFirestore.getInstance()
    private val songCollection = firestore.collection(SONG_COLLECTION)
    private val fromJson = LocalDatabase().getSongsFromJSON(context)


    suspend fun getAllSongs():List<Song>{
        return try{
          if (fromJson.isNotEmpty()) {
              fromJson
          }else{
              songCollection.get().await().toObjects(Song::class.java)
            }
        }catch (e:Exception){
            emptyList()
        }
    }
}