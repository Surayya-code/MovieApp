package com.example.movieapp.common.utils

sealed class Resource <out T> {

    data class Success<out T : Any>(val data: T) : Resource<T>()

    data class Error(val exception: String) : Resource<Nothing>()

    object Loading : Resource<Nothing>()






}