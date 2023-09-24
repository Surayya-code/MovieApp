package com.example.movieapp.domein.repository

import com.example.movieapp.common.utils.Resource
import com.example.movieapp.data.local.db.MyListDb
import com.example.movieapp.data.source.LocaleSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocaleRepositoryImpl @Inject constructor(
   private  val source:LocaleSource
): LocaleRepository {
    override suspend fun insertData(myList: MyListDb) {
           source.insertData(myList)
    }

    override  suspend fun deleteData(myList: MyListDb) {
      source.deleteData(myList)
    }

    override  suspend fun getAllData(): Flow<Resource<List<MyListDb>>> =flow{
        try {
           emit(Resource.Loading)
            when(val response=source.getAllData()){
                is Resource.Error -> {
                    emit(Resource.Error(response.exception))
                }
                Resource.Loading ->{ Unit }
                is Resource.Success -> {
                    emit(Resource.Success(response.data))
                }
            }
        }catch (e:Exception){
            emit(Resource.Error(e.localizedMessage))
        }
    }
}