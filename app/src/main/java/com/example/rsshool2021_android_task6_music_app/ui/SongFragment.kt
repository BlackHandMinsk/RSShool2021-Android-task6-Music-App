package com.example.rsshool2021_android_task6_music_app.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.example.rsshool2021_android_task6_music_app.R
import com.example.rsshool2021_android_task6_music_app.data.entities.Song
import com.example.rsshool2021_android_task6_music_app.exoplayer.toSong
import com.example.rsshool2021_android_task6_music_app.other.Status
import com.example.rsshool2021_android_task6_music_app.viewmodels.MainViewModel
import com.example.rsshool2021_android_task6_music_app.viewmodels.SongViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_song.*
import javax.inject.Inject

@AndroidEntryPoint
class SongFragment:Fragment(R.layout.fragment_song) {
    @Inject
    lateinit var glide:RequestManager

    private lateinit var mainViewModel: MainViewModel

    private val songViewModel:SongViewModel by viewModels()

    private var curPlayingSong: Song? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        subscribeToObservers()
    }


    private fun updateTitleAndSongImage(song:Song){
        val title = "${song.tittle}-${song.artist}"
        tvSongName.text = title
        glide.load(song.bitmapUri).into(ivSongImage)
    }

    private fun subscribeToObservers(){
        mainViewModel.mediaItems.observe(viewLifecycleOwner){
            it?.let { result->
                when(result.status){
                    Status.SUCCESS->{
                        result.data?.let { songs->
                            if (curPlayingSong == null&& songs.isNotEmpty()){
                                curPlayingSong == songs[0]
                                updateTitleAndSongImage(songs[0])
                            }
                        }
                    }
                    else->Unit
                }
            }
        }

        mainViewModel.curPlayingSong.observe(viewLifecycleOwner){
            if(it==null) return@observe
            curPlayingSong = it.toSong()
            updateTitleAndSongImage(curPlayingSong!!)
        }
    }
}