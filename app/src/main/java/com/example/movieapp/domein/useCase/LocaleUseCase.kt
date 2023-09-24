package com.example.movieapp.domein.useCase


import com.example.movieapp.common.utils.Resource
import com.example.movieapp.data.local.db.MyListDb
import com.example.movieapp.data.service.MyListDao
import com.example.movieapp.domein.repository.LocaleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LocaleUseCase @Inject constructor(
    private val repository: LocaleRepository
) {
    suspend fun insertData(myList: MyListDb) {
        repository.insertData(myList)
    }

    suspend fun deleteData(myList: MyListDb) {
        repository.deleteData(myList)
    }

   suspend fun getAllData(): Flow<Resource<List<MyListDb>>> {
        return repository.getAllData()
    }
}
