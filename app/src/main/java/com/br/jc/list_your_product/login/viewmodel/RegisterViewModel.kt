package com.br.jc.list_your_product.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.jc.list_your_product.R
import com.br.jc.list_your_product.base.Error
import com.br.jc.list_your_product.base.Loaded
import com.br.jc.list_your_product.base.State
import com.br.jc.list_your_product.login.usercase.LoginUseCase
import kotlinx.coroutines.launch

class RegisterViewModel (
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _isFilled = MutableLiveData<State<String>>()
    private val _isLogged = MutableLiveData<State<String>>()

    fun isFieldLoginEmpty(name: String, email: String, password: String): LiveData<State<String>> {
        if (email.isNotEmpty() && password.isNotEmpty()) _isFilled.postValue(Loaded(""))
        val alertPassword = R.string.empty_password_space
        val alertEmail = R.string.empty_mail_space
        val alertName = R.string.empty_name
        if (name.isEmpty()) {
            _isFilled.postValue(Error("$alertName"))
            return _isFilled
        }
        if (password.isEmpty()) {
            _isFilled.postValue(Error("$alertPassword"))
            return _isFilled
        }
        if (email.isEmpty()) {
            _isFilled.postValue(Error("$alertEmail"))
            return _isFilled
        }
        return _isFilled
    }

    fun getExceptionCause(e: Exception): LiveData<State<String>> {

        viewModelScope.launch {
            loginUseCase.execute(e).observeForever { it ->
                when (it) {
                    is Loaded -> {
                        _isLogged.postValue(Loaded(it.result))
                    }

                    is Error -> {
                        _isLogged.postValue(Error(it.message))
                    }

                    else -> {}
                }
            }

        }

        return _isLogged
    }



}