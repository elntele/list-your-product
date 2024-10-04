package com.br.jc.list_your_product.di


import com.br.jc.list_your_product.login.usercase.LoginUseCase
import com.br.jc.list_your_product.login.usercase.RegisterUseCase
import com.br.jc.list_your_product.login.viewmodel.LoginViewModel
import com.br.jc.list_your_product.login.viewmodel.RegisterViewModel
import com.br.jc.list_your_product.rest.usecase.RestGetIdsUseCase
import com.br.jc.list_your_product.rest.usecase.RestGetMoviesListUseCase
import com.br.jc.list_your_product.rest.usecase.RestSearchUseCase
import com.br.jc.list_your_product.rest.viewmodel.WaitRestViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


object Modules {
    val module = module {
        viewModelInjetction()
        useCaseInjection()
        resourcesInjection()
    }

    /**
     * declare here, injection of all viewModels
     */
    private fun Module.viewModelInjetction() {
        //login viewmodel injections
        viewModel { LoginViewModel(get()) }
        viewModel { RegisterViewModel(get()) }
        viewModel {WaitRestViewModel(get(), get(), get())}
    }

    /**
     * declare here, injection of all useCases
     * as a singleton
     */
    private fun Module.useCaseInjection() {


        single {
            LoginUseCase()
        }

        single {
            RegisterUseCase(get())
        }

        single {
            RestGetIdsUseCase()
        }

        single {
            RestGetMoviesListUseCase()
        }

        single {
            RestSearchUseCase()
        }
    }

    /**
     * declare here, injection of all resources
     * as a singleton
     */

    private fun Module.resourcesInjection(){
        single {
            FirebaseAuth.getInstance()
        }

        single<CoroutineDispatcher> {
            Dispatchers.IO
        }
    }

}