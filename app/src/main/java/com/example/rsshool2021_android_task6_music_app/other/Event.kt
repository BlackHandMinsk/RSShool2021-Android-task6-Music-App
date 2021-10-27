package com.example.rsshool2021_android_task6_music_app.other

open class Event<out T>(private val data: T) {


    var hasBeenHandled = false
        private set


    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            data
        }
    }

    fun picContent() = data
}