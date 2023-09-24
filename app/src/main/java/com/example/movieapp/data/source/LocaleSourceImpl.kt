package com.example.movieapp.data.source

import com.example.movieapp.common.utils.Resource
import com.example.movieapp.data.local.db.MyListDb
import com.example.movieapp.data.service.MyListDao
import javax.inject.Inject

class LocaleSourceImpl @Inject constructor(
    private val service:MyListDao
)
    :LocaleSource {
    override suspend fun insertData(myList: MyListDb) {
        service.insertData(myList)
    }

    override suspend fun deleteData(myList: MyListDb) {
        service.deleteData(myList)
    }

    override suspend fun getAllData(): Resource<List<MyListDb>> {
      return  try {
           val response= service.getAllData()
            Resource.Success(response)
        } catch (e: Exception) {
          Resource.Error(e.localizedMessage)
        }

    }
}