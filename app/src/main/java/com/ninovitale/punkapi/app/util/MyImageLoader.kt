package com.ninovitale.punkapi.app.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.ninovitale.punkapi.app.R.drawable
import javax.inject.Singleton

/**
 * Created by antoninovitale on 29/08/2017.
 */
@Singleton
class MyImageLoader {
    fun loadImage(context: Context, url: String?, view: ImageView) {
        Glide.with(context)
                .load(url)
                .placeholder(drawable.ic_placeholder)
                .fallback(drawable.ic_placeholder)
                .error(drawable.ic_placeholder)
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .fitCenter()
                .into(view)
    }
}