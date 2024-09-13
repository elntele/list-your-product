package com.br.jc.list_your_product.login.usercase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.br.jc.list_your_product.R
import com.br.jc.list_your_product.base.Error
import com.br.jc.list_your_product.base.State
import com.br.jc.list_your_product.base.UseCase
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class LoginUseCase(

) : UseCase<Exception, LiveData<State<String>>> {
    private val _isWitchException = MutableLiveData<State<String>>()
    override suspend fun execute(param: Exception): LiveData<State<String>> {
        var msg = ""
        try {
            throw param
        } catch (e: FirebaseAuthWeakPasswordException) {
            msg = "${R.string.weak_password}"
            _isWitchException.postValue(Error(msg))
            return _isWitchException
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            msg = "${R.string.mail_and_password_not_match}"
            _isWitchException.postValue(Error(msg))
            return _isWitchException
        } catch (e: FirebaseAuthEmailException) {
            msg = "${R.string.mail_not_registered}"
            _isWitchException.postValue(Error(msg))
            return _isWitchException
        } catch (e: FirebaseTooManyRequestsException) {
            msg = "${R.string.Too_many_requests}"
            _isWitchException.postValue(Error(msg))
            return _isWitchException
        } catch (e: FirebaseException) {
            // FirebaseException é a classe base
            msg = "${R.string.mail_not_valid} ${e.message}"
            e.printStackTrace()
            _isWitchException.postValue(Error(msg))
            return _isWitchException
        }
    }
}