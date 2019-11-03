package com.android.sergiobelda.gramophone.ui.mylibrary.pages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.android.sergiobelda.gramophone.R
import com.android.sergiobelda.gramophone.model.Album
import com.google.android.material.card.MaterialCardView

class AlbumsAdapter(private var albums: ArrayList<Album>) :
    RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.albums_list_item, parent, false)
        return AlbumsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        val album = albums[position]
        //holder.bindAlbum(album, createOnClickListener(album))
        holder.bindAlbum(album)
    }

    class AlbumsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var albumNameTextView: TextView = itemView.findViewById(R.id.albumNameTextView)
        private var artistTextView: TextView = itemView.findViewById(R.id.artistTextView)
        private var albumCardView: MaterialCardView = itemView.findViewById(R.id.albumCardView)
        private var albumImageView: ImageView = itemView.findViewById(R.id.albumImageView)

        fun bindAlbum(album: Album) {
            albumNameTextView.text = album.title
            /*
            val extras = FragmentNavigatorExtras(
                albumImageView to "detail_image")
val extras = FragmentNavigatorExtras(
                albumImageView to album.name)

             */
            albumImageView.load(
                album.coverUri
            )
            albumCardView.setOnClickListener {
                it.findNavController().navigate(R.id.navToAlbumDetail)
            }
        }
    }
}