package com.example.rsshool2021_android_task6_music_app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rsshool2021_android_task6_music_app.exoplayer.MusicService
import com.example.rsshool2021_android_task6_music_app.exoplayer.MusicServiceConnection
import com.example.rsshool2021_android_task6_music_app.exoplayer.currentPlaybackPosition
import com.example.rsshool2021_android_task6_music_app.other.Constants.UPDATE_PLAYER_POSITION_INTERVAL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongViewModel @Inject constructor(musicServiceConnection: MusicServiceConnection):ViewModel() {

    private val playbackState = musicServiceConnection.playbackState

    private val _curSongDuration = MutableLiveData<Long>()
    val curSongDuration:LiveData<Long> = _curSongDuration

    private val _curPlayerPosition = MutableLiveData<Long>()
    val curPlayerPosition:LiveData<Long> = _curPlayerPosition

    init {
        updateCurrentPlayerPosition()
    }

    private fun updateCurrentPlayerPosition(){
        viewModelScope.launch {
            while(true){
                val pos = playbackState.value?.currentPlaybackPosition
                if (curPlayerPosition.value != pos){
                    _curPlayerPosition.postValue(pos!!)
                    _curSongDuration.postValue(MusicService.curSongDuration)
                }
                delay(UPDATE_PLAYER_POSITION_INTERVAL)
            }
        }
    }


}