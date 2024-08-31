package com.br.jc.list_your_product.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.jc.list_your_product.login.usercase.LoginUseCase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val auth: FirebaseAuth,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,

    ) : ViewModel() {

    private val _isLoged = MutableLiveData<Boolean?>()


    public suspend fun login(): LiveData<Boolean?> {
        return _isLoged
    }
}