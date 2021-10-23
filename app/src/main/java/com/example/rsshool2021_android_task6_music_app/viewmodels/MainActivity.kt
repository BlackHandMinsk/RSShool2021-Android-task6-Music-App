package com.example.rsshool2021_android_task6_music_app.viewmodels

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.RequestManager
import com.example.rsshool2021_android_task6_music_app.R
import com.example.rsshool2021_android_task6_music_app.adapters.SwipeSongAdapter
import com.example.rsshool2021_android_task6_music_app.data.entities.Song
import com.example.rsshool2021_android_task6_music_app.exoplayer.toSong
import com.example.rsshool2021_android_task6_music_app.other.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel:MainViewModel by viewModels()

    @Inject
    lateinit var swipeSongAdapter: SwipeSongAdapter

@Inject
lateinit var glide:RequestManager

private var curPlayingSong: Song? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribeToObservers()

        vpSong.adapter = swipeSongAdapter
    }

    private fun switchViewPagerToCurrentSong(song: Song){
        val newItemIndex = swipeSongAdapter.songs.indexOf(song)
        if(newItemIndex!=-1){
            vpSong.currentItem = newItemIndex
            curPlayingSong = song
        }
    }

    private fun subscribeToObservers(){
        mainViewModel.mediaItems.observe(this){
            it?.let { result->
                when(result.status){
                    Status.SUCCESS->{
                        result.data?.let { songs->
                            swipeSongAdapter.songs = songs
                            if(songs.isNotEmpty()){
                                glide.load((curPlayingSong?:songs[0].bitmapUri)).into(ivCurSongImage)
                            }
                            switchViewPagerToCurrentSong(curPlayingSong?:return@observe)
                        }
                    }
                    Status.ERROR->Unit
                    Status.LOADING->Unit
                }
            }
        }
        mainViewModel.curPlayingSong.observe(this){
            if(it==null) return@observe
            curPlayingSong = it.toSong()
            glide.load(curPlayingSong?.bitmapUri).into(ivCurSongImage)
            switchViewPagerToCurrentSong(curPlayingSong?:return@observe)
        }
    }
}