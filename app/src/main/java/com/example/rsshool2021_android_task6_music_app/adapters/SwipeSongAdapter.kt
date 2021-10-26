package com.example.rsshool2021_android_task6_music_app.adapters


import androidx.recyclerview.widget.AsyncListDiffer

import com.example.rsshool2021_android_task6_music_app.R

import kotlinx.android.synthetic.main.list_item.view.*


class SwipeSongAdapter:BaseSongAdapter(R.layout.swipe_item) {


    override val differ = AsyncListDiffer(this,diffCallback )


    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
       val song = songs[position]
        holder.itemView.apply {
            val text = "${song.tittle} - ${song.artist}"
            tvPrimary.text = text

            setOnClickListener {
                onItemClickListener?.let { click->
                    click(song )
                }
            }
        }
    }
}