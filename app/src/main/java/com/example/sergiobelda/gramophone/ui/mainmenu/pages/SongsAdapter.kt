package com.example.sergiobelda.gramophone.ui.mainmenu.pages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sergiobelda.gramophone.R
import com.example.sergiobelda.gramophone.entities.Song



class SongsAdapter(private var songs: ArrayList<Song>, private var listener: OnItemClickListener) : RecyclerView.Adapter<SongsAdapter.SongsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.songs_list_item, parent, false)
        return SongsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    override fun onBindViewHolder(holder: SongsViewHolder, position: Int) {
        val song = songs[position]
        holder.bindSong(song, listener)
    }

    class SongsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var songTitleTextView : TextView = itemView.findViewById(R.id.songTitleTextView)
        private var artistTextView : TextView = itemView.findViewById(R.id.artistTextView)

        fun bindSong(song : Song, listener : OnItemClickListener){
            songTitleTextView.text = song.title
            artistTextView.text = song.artist
            itemView.setOnClickListener {
                listener.onItemClick(song)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(song : Song)
    }
}