package com.example.movieapp.data.source

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.common.utils.Resource
import com.example.movieapp.data.local.db.MyListDb

interface LocaleSource {
   @Insert
    suspend fun insertData(myList: MyListDb)

    @Delete
     suspend fun deleteData(myList: MyListDb)

    @Query("Select * from my_movies")
    suspend fun getAllData(): Resource<List<MyListDb>>

}

