package com.example.movieapp.domein.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.movieapp.common.utils.Resource
import com.example.movieapp.data.local.db.MyListDb
import kotlinx.coroutines.flow.Flow

interface LocaleRepository {
    @Insert
    suspend fun insertData(myList: MyListDb)

    @Delete
    suspend fun deleteData(myList: MyListDb)

    @Query("Select * from my_movies")
    suspend fun getAllData(): Flow<Resource<List<MyListDb>>>
}