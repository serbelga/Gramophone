package com.android.sergiobelda.gramophone.ui.mylibrary.albums

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.sergiobelda.gramophone.R
import com.android.sergiobelda.gramophone.model.Track

class TracksAdapter(private var tracks: ArrayList<Track>) :
        RecyclerView.Adapter<TracksAdapter.TracksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TracksViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_track, parent, false)
        return TracksViewHolder(itemView)
    }

    override fun getItemCount() = tracks.size

    override fun onBindViewHolder(holder: TracksViewHolder, position: Int) {
        val track = tracks[position]
        holder.bindTrack(track)
    }

    inner class TracksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val trackNumberTextView: TextView = itemView.findViewById(R.id.track_number_text_view)
        private val trackTitleTextView: TextView = itemView.findViewById(R.id.track_title_text_view)
        private val trackDurationTextView: TextView = itemView.findViewById(R.id.track_duration_text_view)
        fun bindTrack(track: Track) {
            trackNumberTextView.text = track.number.toString()
            trackTitleTextView.text = track.title
            trackDurationTextView.text = track.duration_ms.toString()
        }
    }
}