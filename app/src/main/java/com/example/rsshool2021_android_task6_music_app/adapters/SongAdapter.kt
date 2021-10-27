package com.example.rsshool2021_android_task6_music_app.adapters


import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.AsyncListDiffer

import com.bumptech.glide.RequestManager
import com.example.rsshool2021_android_task6_music_app.R

import kotlinx.android.synthetic.main.list_item.view.*
import javax.inject.Inject

class SongAdapter @Inject constructor(
    private val glide: RequestManager
) : BaseSongAdapter(R.layout.list_item) {


    override val differ = AsyncListDiffer(this, diffCallback)


    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songs[position]
        val animAlpha: Animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.scale)
        holder.itemView.apply {
            tvPrimary.text = song.tittle
            tvSecondary.text = song.artist
            glide.load(song.bitmapUri).into(ivItemImage)

            setOnClickListener {
                onItemClickListener?.let { click ->
                    it.startAnimation(animAlpha)
                    click(song)
                }
            }
        }
    }
}