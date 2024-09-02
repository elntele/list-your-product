package com.br.jc.list_your_product.login.viewmodel

import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.jc.list_your_product.R
import com.br.jc.list_your_product.base.Error
import com.br.jc.list_your_product.base.Loaded
import com.br.jc.list_your_product.base.State
import com.br.jc.list_your_product.login.usercase.LoginUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,

    ) : ViewModel() {

    private val _isFilled = MutableLiveData<State<String>>()
    private val _isLogged= MutableLiveData<State<String>>()



    fun isFieldLoginEmpty(email: String, password: String): LiveData<State<String>> {
        if (email.isNotEmpty() && password.isNotEmpty()) _isFilled.postValue(Loaded(""))
        val alertPassword = R.string.empty_password_space
        val alertEmail = R.string.empty_mail_space
        val clickSolicitation = R.string.click_to_continue
        if (password.isEmpty()) _isFilled.postValue(Error("$alertPassword"))
        if (email.isEmpty()) _isFilled.postValue(Error("$alertEmail"))
        return _isFilled
    }


    fun login(email: String, password: String): LiveData<State<String>> {
        viewModelScope.launch {
                loginUseCase.execute(Pair(email, password)).observeForever{ it->
                    when(it){
                        is Loaded ->{
                            _isLogged.postValue(Loaded(it.result))
                        }
                        is Error -> {
                            _isLogged.postValue(Error(it.message))
                        }
                        else ->{}
                    }
                }

        }
        return _isLogged
    }

}