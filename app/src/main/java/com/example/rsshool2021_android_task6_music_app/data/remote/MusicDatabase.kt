package com.example.rsshool2021_android_task6_music_app.data.remote


import android.content.Context

import com.example.rsshool2021_android_task6_music_app.data.entities.Song
import com.example.rsshool2021_android_task6_music_app.data.local.LocalDatabase
import com.example.rsshool2021_android_task6_music_app.other.Constants.SONG_COLLECTION

import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.coroutines.tasks.await
import java.lang.Exception


class MusicDatabase(context: Context){


    private val fireStore = FirebaseFirestore.getInstance()
    private val songCollection = fireStore.collection(SONG_COLLECTION)
    private val fromJson = LocalDatabase().getSongsFromJSON(context)


    suspend fun getAllSongs():List<Song>{
        return try{
            //сделаю нормальное переключение когда будет время ))
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