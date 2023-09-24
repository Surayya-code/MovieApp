package com.example.movieapp.di

import com.example.movieapp.data.api.MovieApi
import com.example.movieapp.common.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
        @Provides
        @Singleton
        fun provideFirebaseAuth() = FirebaseAuth.getInstance()



        @Provides
        @Singleton
        fun getMovieApi(): MovieApi {
           return Retrofit.Builder().baseUrl(Constants.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create()).build().create()
        }

}