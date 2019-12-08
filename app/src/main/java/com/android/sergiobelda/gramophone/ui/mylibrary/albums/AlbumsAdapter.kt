package com.android.sergiobelda.gramophone.ui.mylibrary.albums

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.sergiobelda.gramophone.R
import com.android.sergiobelda.gramophone.model.Album
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView

class AlbumsAdapter(private var context : Context, private var albums: ArrayList<Album>) :
    RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_album, parent, false)
        return AlbumsViewHolder(itemView, context)
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        val album = albums[position]
        //holder.bindAlbum(album, createOnClickListener(album))
        holder.bindAlbum(album)
    }

    fun setData(albums: ArrayList<Album>) {
        this.albums = albums
        notifyDataSetChanged()
    }

    inner class AlbumsViewHolder(itemView: View, var context: Context) : RecyclerView.ViewHolder(itemView) {
        private var titleTextView: TextView = itemView.findViewById(R.id.title_text_view)
        private var artistNameTextView: TextView = itemView.findViewById(R.id.artist_name_text_view)
        private var albumCardView: MaterialCardView = itemView.findViewById(R.id.album_card_view)
        private var coverImageView: ImageView = itemView.findViewById(R.id.cover_image_view)

        fun bindAlbum(album: Album) {
            titleTextView.text = album.title
            val artist = album.artists.getOrNull(0)
            artistNameTextView.text = artist?.name ?: context.getString(R.string.unknown_artist)
            Glide.with(context)
                .load(album.coverUri)
                .centerInside()
                .into(coverImageView)


            albumCardView.setOnClickListener {
                //it.findNavController().navigate(R.id.navToAlbumDetail)
                albumSelectedListener.onAlbumSelected(album)
            }
        }
    }

    lateinit var albumSelectedListener: AlbumSelectedListener

    interface AlbumSelectedListener {
        fun onAlbumSelected(album: Album)
    }
}