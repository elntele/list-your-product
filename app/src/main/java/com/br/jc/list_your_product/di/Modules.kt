package com.br.jc.list_your_product.di


import com.br.jc.list_your_product.login.usercase.LoginUseCase
import com.br.jc.list_your_product.login.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


object Modules {
    val module = module {
        viewModelInjetction()
        useCaseInjection()
    }

    /**
     * declare here, injection of all viewModels
     */
    private fun Module.viewModelInjetction() {
        //login viewmodel injections
        viewModel { LoginViewModel(get()) }
    }

    private fun Module.useCaseInjection() {
        single { FirebaseAuth.getInstance() }
        single {
            LoginUseCase(get())
        }
    }

}