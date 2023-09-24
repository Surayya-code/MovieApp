package com.example.movieapp.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.utils.RegisterFieldsState
import com.example.movieapp.common.utils.RegisterValidation
import com.example.movieapp.common.utils.User
import com.example.movieapp.common.utils.Resource
import com.example.movieapp.common.utils.validateEmail
import com.example.movieapp.common.utils.validatePassword
import com.example.movieapp.domein.repository.AuthRepo
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class SigInMVVM @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val repository: AuthRepo,
) : ViewModel() {

    private val _authResult = MutableLiveData<Resource<Any>>()
    val authResult: LiveData<Resource<Any>> get() = _authResult

    private val _validation = Channel<RegisterFieldsState>()
    val validation = _validation.receiveAsFlow()

    private val _resetPassword = MutableLiveData<Resource<Any>>()
    val resetPassword: LiveData<Resource<Any>> get() = _resetPassword


    fun loginUser(user: User, password: String) {
        viewModelScope.launch {
            repository.login(user.email, password).collectLatest {
                if (checkValidation(user, password)) {
                    viewModelScope.launch {
                        _authResult.value = Resource.Loading
                    }
                    firebaseAuth.signInWithEmailAndPassword(
                        user.email, password
                    ).addOnSuccessListener {
                        viewModelScope.launch {
                            it.user?.let {
                                _authResult.value = Resource.Success(it)
                            }
                        }
                    }.addOnFailureListener {
                        viewModelScope.launch {
                            _authResult.value = Resource.Error(it.toString())
                        }
                    }
                } else {
                    val registerFieldsState = RegisterFieldsState(
                        validateEmail(user.email),
                        validatePassword(password)
                    )
                    runBlocking {
                        _validation.send(registerFieldsState)
                    }
                }

            }

        }


    }


    fun resetPassword(email: String) {
        viewModelScope.launch {
            _resetPassword.value = Resource.Loading
        }
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnSuccessListener {
                viewModelScope.launch {
                    _resetPassword.value = Resource.Success(email)
                }
            }.addOnFailureListener {
                viewModelScope.launch {
                    _resetPassword.value = Resource.Error(it.toString())
                }
            }
    }

    private fun checkValidation(user: User, password: String): Boolean {
        val emailValidation = validateEmail(user.email)
        val passwordValidation = validatePassword(password)

        val shouldRegister =
            emailValidation is RegisterValidation.Success &&
                    passwordValidation is RegisterValidation.Success

        return shouldRegister
    }
}