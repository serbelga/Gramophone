/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

@BindingAdapter("albumImageFromUrl")
fun bindAlbumImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transform(RoundedCorners(16))
            .into(view)
    }
}