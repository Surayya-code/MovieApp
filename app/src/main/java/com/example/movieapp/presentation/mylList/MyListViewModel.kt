package com.example.movieapp.presentation.mylList

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.utils.Resource
import com.example.movieapp.data.local.db.MyListDb
import com.example.movieapp.data.service.MyListDao
import com.example.movieapp.data.service.RoomDb
import com.example.movieapp.domein.useCase.LocaleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyListViewModel  @Inject constructor(
   private val localeUseCase: LocaleUseCase,
   private val application: Application
)
    : ViewModel() {

        private  val _liveData= MutableLiveData<MyListUiState>()
        val liveData: LiveData<MyListUiState> get()=_liveData

        private val myListDao: MyListDao = RoomDb.makeDb(application.applicationContext).myListDao()

    ////INSERT
       fun insertData(myListDb: MyListDb){
           viewModelScope.launch {
               localeUseCase.insertData(myListDb)
           }
       }

    ///DELETE
    fun deleteData(myListDb: MyListDb){
        viewModelScope.launch {
            localeUseCase.deleteData(myListDb)
        }
    }
  ///GEtAllData
   fun getAllData(){
       viewModelScope.launch {
           localeUseCase.getAllData().collectLatest {
               when(it){
                   is Resource.Error -> _liveData.value=MyListUiState.Error(it.exception)
                   Resource.Loading -> _liveData.value=MyListUiState.Loading
                   is Resource.Success -> _liveData.value=MyListUiState.MyListData(it.data ?: emptyList())
               }
           }
       }
   }

     sealed class MyListUiState(){
         data class Error (val message:String):MyListUiState()

         object Loading:MyListUiState()

         data class MyListData(val data:List<MyListDb>):MyListUiState()
     }


    fun deleteFavorite(favorite: MyListDb) {
        viewModelScope.launch(Dispatchers.IO) {
            myListDao.deleteData(favorite)
        }
    }

}