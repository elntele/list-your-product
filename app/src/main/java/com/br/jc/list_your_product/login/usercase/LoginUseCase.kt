package com.br.jc.list_your_product.login.usercase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.br.jc.list_your_product.R
import com.br.jc.list_your_product.base.Error
import com.br.jc.list_your_product.base.Loaded
import com.br.jc.list_your_product.base.State
import com.br.jc.list_your_product.base.UseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

class LoginUseCase(
    private val auth: FirebaseAuth
): UseCase<Pair<String,String>, LiveData<State<String>>>{
    private val _isLogged = MutableLiveData<State<String>>()
    override suspend fun execute(param: Pair<String,String>): LiveData<State<String>> {
        val email = param.first
        val password=param.second
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _isLogged.postValue(Loaded(""))
                } else {
                    var msg = ""
                    try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthEmailException) {
                        msg = "${R.string.mail_not_registered}"
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        msg = "${R.string.mail_and_password_not_match}"
                    } catch (e: Exception) {
                        msg = "${R.string.mail_not_valid} ${e.message}"
                        e.printStackTrace()
                    }
                    _isLogged.postValue(Error(msg))
                }
            }
        return  _isLogged
    }
}