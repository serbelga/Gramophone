package com.android.sergiobelda.gramophone.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.android.sergiobelda.gramophone.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("artistImageFromUrl")
fun bindArtistImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .apply(RequestOptions()
                .placeholder(R.drawable.ic_outline_person_outline_24)
                .error(R.drawable.ic_outline_person_outline_24)
                .circleCrop()
            )
            .into(view)
    }
}