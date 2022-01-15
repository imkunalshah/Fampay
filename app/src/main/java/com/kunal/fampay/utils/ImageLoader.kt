package com.kunal.fampay.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

class ImageLoader {
    companion object {
        fun loadImage(imageUrl: String, rootView:View, targetView: ImageView) {
            Glide.with(rootView).load(imageUrl).into(targetView)
        }
    }
}