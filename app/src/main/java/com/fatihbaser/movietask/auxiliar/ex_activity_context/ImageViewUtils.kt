package com.fatihbaser.movietask.auxiliar.ex_activity_context

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.fatihbaser.movietask.R

fun ImageView.loadImage (
    urlImage: String,
    circleCrop: Boolean = true,
    errorImage: Int = R.drawable.ic_fake_poster
) {
    if (urlImage.isNotEmpty()) {
        if (circleCrop) {
            Glide
                .with(this.context)
                .load(urlImage)
                .circleCrop()
                .error(errorImage)
                .into(this)
        } else {
            Glide
                .with(this.context)
                .load(urlImage)
                .error(errorImage)
                .into(this)
        }
    }
}