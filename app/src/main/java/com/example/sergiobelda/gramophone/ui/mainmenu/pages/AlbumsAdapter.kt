package com.example.sergiobelda.gramophone.ui.mainmenu.pages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sergiobelda.gramophone.R
import com.example.sergiobelda.gramophone.util.Album
import com.google.android.material.card.MaterialCardView

class AlbumsAdapter(private var albums: ArrayList<Album>, private var listener: OnItemClickListener) : RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsAdapter.AlbumsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.albums_list_item, parent, false)
        return AlbumsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    override fun onBindViewHolder(holder: AlbumsAdapter.AlbumsViewHolder, position: Int) {
        val album = albums[position]
        holder.bingAlbum(album, listener)
    }

    class AlbumsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var albumNameTextView : TextView = itemView.findViewById(R.id.albumNameTextView)
        private var artistTextView : TextView = itemView.findViewById(R.id.artistTextView)
        private var albumCardView : MaterialCardView = itemView.findViewById(R.id.albumCardView)

        fun bingAlbum(album: Album, listener: OnItemClickListener) {
            albumNameTextView.text = album.name
            albumCardView.setOnClickListener {
                listener.onItemClick(album)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(album: Album)
    }
}