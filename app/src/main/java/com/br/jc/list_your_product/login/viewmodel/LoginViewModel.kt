package com.br.jc.list_your_product.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.jc.list_your_product.R
import com.br.jc.list_your_product.common.Error
import com.br.jc.list_your_product.common.Loaded
import com.br.jc.list_your_product.common.State
import com.br.jc.list_your_product.login.usercase.LoginUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,

    ) : ViewModel() {

    private val _isFilled = MutableLiveData<State<String>>()
    private val _exceptionCause = MutableLiveData<State<String>>()
    private val _isLogged = MutableLiveData<Boolean>()
    val isLogged: LiveData<Boolean> =_isLogged


    fun isFieldLoginEmpty(email: String, password: String): LiveData<State<String>> {
        if (email.isNotEmpty() && password.isNotEmpty()) _isFilled.postValue(Loaded(""))
        val alertPassword = R.string.empty_password_space
        val alertEmail = R.string.empty_mail_space
        val clickSolicitation = R.string.click_to_continue
        if (password.isEmpty()) _isFilled.postValue(Error("$alertPassword"))
        if (email.isEmpty()) _isFilled.postValue(Error("$alertEmail"))
        return _isFilled
    }

    fun getExceptionCause(e: Exception): LiveData<State<String>> {

        viewModelScope.launch {
            loginUseCase.execute(e).observeForever { it ->
                when (it) {
                    is Loaded -> {
                        _exceptionCause.postValue(Loaded(it.result))
                    }

                    is Error -> {
                        _exceptionCause.postValue(Error(it.message))
                    }

                    else -> {}
                }
            }

        }

        return _exceptionCause
    }

}