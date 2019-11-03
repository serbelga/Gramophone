package com.android.sergiobelda.gramophone.ui.mylibrary.pages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.sergiobelda.gramophone.R
import com.android.sergiobelda.gramophone.model.Track


class TracksAdapter(private var tracks: ArrayList<Track>, private var listener: OnItemClickListener) :
    RecyclerView.Adapter<TracksAdapter.SongsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.songs_list_item, parent, false)
        return SongsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return tracks.size
    }

    override fun onBindViewHolder(holder: SongsViewHolder, position: Int) {
        val song = tracks[position]
        holder.bindSong(song, listener)
    }

    class SongsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var songTitleTextView: TextView = itemView.findViewById(R.id.songTitleTextView)
        private var artistTextView: TextView = itemView.findViewById(R.id.artistTextView)

        fun bindSong(track: Track, listener: OnItemClickListener) {
            songTitleTextView.text = track.title
            artistTextView.text = track.artists.toString()
            itemView.setOnClickListener {
                listener.onItemClick(track)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(track: Track)
    }
}