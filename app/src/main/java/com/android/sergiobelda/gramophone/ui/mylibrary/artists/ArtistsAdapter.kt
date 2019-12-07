package com.android.sergiobelda.gramophone.ui.mylibrary.artists

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.sergiobelda.gramophone.R
import com.android.sergiobelda.gramophone.model.Artist
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions

class ArtistsAdapter(private var context : Context, private var artists : ArrayList<Artist>) :
        RecyclerView.Adapter<ArtistsAdapter.ArtistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_artist, parent, false)
        return ArtistViewHolder(itemView, context)
    }

    override fun getItemCount(): Int {
        return artists.size
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val artist = artists[position]
        holder.bindArtist(artist)
    }

    fun setData(artists : ArrayList<Artist>) {
        this.artists = artists
        notifyDataSetChanged()
    }

    class ArtistViewHolder(itemView: View, private var context: Context) : RecyclerView.ViewHolder(itemView) {
        private val artistImageView: ImageView = itemView.findViewById(R.id.artist_image_view)
        private val nameTextView: TextView = itemView.findViewById(R.id.name_text_view)

        fun bindArtist(artist : Artist) {
            nameTextView.text = artist.name
            Glide.with(context)
                .load(artist.imageUri)
                .apply(RequestOptions.circleCropTransform())
                .into(artistImageView)
        }
    }
}