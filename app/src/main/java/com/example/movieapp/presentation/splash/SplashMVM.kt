package com.example.movieapp.presentation.splash


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashMVM  @Inject constructor(
    private var firebaseAuth:FirebaseAuth
): ViewModel() {
private val _auth=MutableLiveData<Boolean>()
    val auth:LiveData<Boolean> get()=_auth

    init{
        checkAuth()
    }
    private fun checkAuth() {
        viewModelScope.launch {
            delay(1500)
            firebaseAuth= Firebase.auth
            firebaseAuth.apply {
                _auth.value = firebaseAuth.currentUser==null



            }
        }

    }
}
