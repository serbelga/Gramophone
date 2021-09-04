/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.ui.mylibrary.artists

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.sergiobelda.gramophone.R
import com.android.sergiobelda.gramophone.model.Artist
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.imageview.ShapeableImageView

/**
 * ArtistsAdapter
 * @author Sergio Belda Galbis (@serbelga)
 */
class ArtistsAdapter(private var context: Context, private var artists: ArrayList<Artist>) :
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

    fun setData(artists: ArrayList<Artist>) {
        this.artists = artists
        notifyDataSetChanged()
    }

    inner class ArtistViewHolder(itemView: View, private var context: Context) : RecyclerView.ViewHolder(itemView) {
        private val artistImageView: ShapeableImageView = itemView.findViewById(R.id.artist_image_view)
        private val nameTextView: TextView = itemView.findViewById(R.id.name_text_view)
        private val artistLayout: LinearLayout = itemView.findViewById(R.id.artist_layout)

        fun bindArtist(artist: Artist) {
            nameTextView.text = artist.name
            artistImageView.apply {
                this.transitionName = artist.imageUri
                Glide.with(context)
                    .load(artist.imageUri)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.ic_outline_person_outline_24)
                            .error(R.drawable.ic_outline_person_outline_24)
                            .circleCrop()
                    )
                    .into(this)
            }
            artistLayout.setOnClickListener {
                artistSelectedListener.onArtistSelected(artist, artistImageView)
            }
        }
    }

    interface ArtistSelectedListener {
        fun onArtistSelected(artist: Artist, imageView: ImageView)
    }

    lateinit var artistSelectedListener: ArtistSelectedListener
}
