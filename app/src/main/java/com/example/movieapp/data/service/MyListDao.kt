package com.example.movieapp.data.service

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.data.local.db.MyListDb


@Dao
interface MyListDao {

     @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(myList: MyListDb)

    @Delete
    suspend fun deleteData(myList: MyListDb)

    @Query("Select * from my_movies")
     suspend fun getAllData():List<MyListDb>
}

