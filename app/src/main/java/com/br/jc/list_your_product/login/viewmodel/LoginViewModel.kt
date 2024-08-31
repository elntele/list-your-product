package com.br.jc.list_your_product.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.jc.list_your_product.R
import com.br.jc.list_your_product.base.Error
import com.br.jc.list_your_product.base.Loaded
import com.br.jc.list_your_product.base.State
import com.br.jc.list_your_product.login.usercase.LoginUseCase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val auth: FirebaseAuth,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,

    ) : ViewModel() {

    private val _isLoged = MutableLiveData<State<String>?>()


    fun isFieldLoginEmpty(email: String, password: String): LiveData<State<String>?> {
        if (email.isNotEmpty() && password.isNotEmpty()) _isLoged.postValue(Loaded(""))
        val alertPassword = R.string.empty_password_space
        val alertEmail = R.string.empty_mail_space
        val clickSolicitation = R.string.click_to_continue
        if (password.isEmpty()) _isLoged.postValue(Error("${alertPassword},${clickSolicitation}"))
        if (password.isEmpty()) _isLoged.postValue(Error("${alertEmail},${clickSolicitation}"))
        return _isLoged
    }

    fun getNumbers(concatenatedMessage: String): Pair<Int, Int> {
        val intPair = concatenatedMessage.split(",")
        return Pair(intPair[0].toInt(), intPair[1].toInt())
    }
}