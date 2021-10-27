package com.example.rsshool2021_android_task6_music_app.other

import android.graphics.drawable.AnimationDrawable

fun setBackGroundAnimation(animationDrawable: AnimationDrawable){
    animationDrawable.apply {
        setEnterFadeDuration(1000)
        setExitFadeDuration(3000)
        start()
    }
}