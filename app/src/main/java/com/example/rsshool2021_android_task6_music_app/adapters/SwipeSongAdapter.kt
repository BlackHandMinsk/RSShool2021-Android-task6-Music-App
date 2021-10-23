package com.example.rsshool2021_android_task6_music_app.adapters

import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.rsshool2021_android_task6_music_app.R
import com.example.rsshool2021_android_task6_music_app.data.entities.Song
import kotlinx.android.synthetic.main.list_item.view.*
import javax.inject.Inject

class SwipeSongAdapter():BaseSongAdapter(R.id.list_item) {


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