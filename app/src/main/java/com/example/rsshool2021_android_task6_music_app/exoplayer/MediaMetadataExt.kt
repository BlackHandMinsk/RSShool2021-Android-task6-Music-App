package com.example.rsshool2021_android_task6_music_app.exoplayer

import android.support.v4.media.MediaMetadataCompat
import com.example.rsshool2021_android_task6_music_app.data.entities.Song

fun MediaMetadataCompat.toSong():Song?{
    return description?.let {
        Song(
            it.mediaId ?: "",
            it.title.toString(),
            it.subtitle.toString(),
            it.iconUri.toString(),
            it.mediaUri.toString()
        )
    }
}