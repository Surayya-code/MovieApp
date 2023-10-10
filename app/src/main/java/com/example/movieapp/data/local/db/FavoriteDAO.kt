package com.example.movieapp.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFav(product: FavoriteDTO)

    @Delete
    suspend fun deleteFav(product: FavoriteDTO)

    @Query("SELECT * FROM movie_list")
    suspend fun getFav(): List<FavoriteDTO>

}