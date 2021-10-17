package com.example.rsshool2021_android_task6_music_app.data.remote

import com.example.rsshool2021_android_task6_music_app.data.entities.Song
import com.example.rsshool2021_android_task6_music_app.other.Constants.SONG_COLLECTION
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class MusicDatabase{


  private val firestore = FirebaseFirestore.getInstance()
    private val songCollection = firestore.collection(SONG_COLLECTION)


    suspend fun getAllSongs():List<Song>{
        return try{
            songCollection.get().await().toObjects(Song::class.java)
        }catch (e:Exception){
            emptyList()
        }
    }
}