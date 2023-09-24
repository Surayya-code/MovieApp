package com.example.movieapp.domein

import android.os.Parcelable
import com.example.movieapp.common.utils.MovieTypeEnum
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieType(
    var title : String,
    var type: MovieTypeEnum
) : Parcelable