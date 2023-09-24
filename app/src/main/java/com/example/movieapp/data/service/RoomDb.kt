package com.example.movieapp.data.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapp.data.local.db.MyListDb

@Database(entities = [MyListDb::class], version = 1)

abstract class RoomDb:RoomDatabase() {

    abstract fun myListDao(): MyListDao


    companion object{
        @Volatile private  var  instance: RoomDb?=null
        private  val lock=Any()
        operator  fun  invoke(context: Context)= instance ?: synchronized(lock){
            instance ?: makeDb(context).also{
                instance=it
            }
        }
       fun makeDb(context: Context)= Room.databaseBuilder(
         context.applicationContext,RoomDb::class.java, "roomDb"
     ).build()
    }
}