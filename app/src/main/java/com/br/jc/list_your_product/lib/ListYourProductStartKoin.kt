package com.br.jc.list_your_product.lib

import android.app.Application
import com.br.jc.list_your_product.di.Modules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

object ListYourProductStartKoin {
    fun run(application: Application) {
        startKoin {
            androidContext(application)
            modules(modules = Modules.module)
        }
    }
}