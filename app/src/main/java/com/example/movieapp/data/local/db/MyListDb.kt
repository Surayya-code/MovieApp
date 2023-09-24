package com.example.movieapp.data.local.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "my_movies")
data class MyListDb(@PrimaryKey(autoGenerate = true)
                    val id: Int,
                    val title: String,
                    val image: String,
                    val vote: Double
): Parcelable