package com.example.movieapp.presentation.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.utils.Resource
import com.example.movieapp.data.local.db.MyListDb
import com.example.movieapp.data.service.MyListDao
import com.example.movieapp.data.service.RoomDb
import com.example.movieapp.domein.repository.movieRepo
import com.example.movieapp.domein.state.CastUiState
import com.example.movieapp.domein.state.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: movieRepo,
    private val myListDao: MyListDao,
    application: Application
): AndroidViewModel(application)  {

    private val _detailMovies= MutableLiveData<DetailUiState>()
    val detailMovies: LiveData<DetailUiState> = _detailMovies



    private val _cast= MutableLiveData<CastUiState>()
    val cast: LiveData<CastUiState> = _cast



fun addMyList(myList: MyListDb) {
    viewModelScope.launch(Dispatchers.IO) {
        myListDao.insertData(myList)
    }
}



    fun getDetailMovie(id : Int) {
        viewModelScope.launch {
            movieRepository.getDetails(id).collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _detailMovies.value = DetailUiState.Success(it.data)

                    }

                    is Resource.Error -> {
                        _detailMovies.value = DetailUiState.Error(it.exception.toString())
                    }

                    is Resource.Loading -> {
                        _detailMovies.value = DetailUiState.Loading
                    }


                }
            }
        }
    }

    fun getCastDetail(id: Int) {
        viewModelScope.launch {
            movieRepository.getCredits(id).collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _cast.value = CastUiState.Success(it.data)

                    }

                    is Resource.Error -> {
                        _cast.value = CastUiState.Error(it.exception.toString())
                    }

                    is Resource.Loading -> {
                        _cast.value = CastUiState.Loading
                    }
                }
            }
        }
    }





}