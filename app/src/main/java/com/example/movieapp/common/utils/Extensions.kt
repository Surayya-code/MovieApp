package com.example.movieapp.common.utils

import android.app.Activity
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.movieapp.R
import com.example.movieapp.common.utils.Constants.BASE_URL_IMAGE
import com.example.movieapp.common.utils.Constants.BASE_URL_IMAGE_YOUTUBE
import com.example.movieapp.common.utils.Constants.YOUTUBE_QUALITY
import com.example.movieapp.data.dto.DetailsResponseModelItem
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun Activity.showToast(
    title: String?,
    description: String?,
    style: MotionToastStyle
) {
    MotionToast.createColorToast(
        this,
        title,
        description!!,
        style,
        MotionToast.GRAVITY_BOTTOM,
        MotionToast.LONG_DURATION,
        null
    )
}


    fun ImageView.loadUrl(url: String, imageTypeEnum: ImageTypeEnum) {
        val options = RequestOptions().centerCrop().placeholder(R.drawable.home_bg)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .dontAnimate()
            .dontTransform()

        val imageUrl = when (imageTypeEnum) {
            ImageTypeEnum.MOVIE_IMAGE -> BASE_URL_IMAGE + url
            ImageTypeEnum.YOUTUBE_IMAGE -> BASE_URL_IMAGE_YOUTUBE + url + YOUTUBE_QUALITY
            ImageTypeEnum.USER_IMAGE -> url
        }

        Glide.with(this).load(imageUrl).apply(options).into(this)


    }