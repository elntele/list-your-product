package com.br.jc.list_your_product.login.usercase

import androidx.lifecycle.LiveData
import com.br.jc.list_your_product.common.State
import com.br.jc.list_your_product.common.interfaces.UseCase

class RegisterUseCase(
   private val loginUseCase: LoginUseCase
): UseCase<Exception, LiveData<State<String>>> {

    override suspend fun execute(param: Exception): LiveData<State<String>> {
       return loginUseCase.execute(param)
    }
}