package com.example.movieapp.di

import android.content.Context
import androidx.room.Room
import com.example.movieapp.data.service.MyListDao
import com.example.movieapp.data.service.RoomDb
import com.example.movieapp.data.source.LocaleSource
import com.example.movieapp.data.source.LocaleSourceImpl
import com.example.movieapp.domein.repository.LocaleRepository
import com.example.movieapp.domein.repository.LocaleRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object LocaleModule {

    @Singleton
    @Provides
    fun injectRoomDB(@ApplicationContext context: Context): RoomDb {
        return Room.databaseBuilder(
            context, RoomDb::class.java, "movieDB"
        ).build()
    }

    @Singleton
    @Provides
    fun injectRoomDao(roomDb: RoomDb) = roomDb.myListDao()

@Singleton
@Provides
fun injectSource(service: MyListDao): LocaleSource = LocaleSourceImpl(service)

    @Singleton
    @Provides
    fun injectRepo(source: LocaleSource): LocaleRepository = LocaleRepositoryImpl(source)

}
