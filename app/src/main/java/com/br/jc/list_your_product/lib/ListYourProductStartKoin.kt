package com.br.jc.list_your_product.lib

import android.app.Application
import com.br.jc.list_your_product.di.Modules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ListYourProductStartKoin: Application() {
    @Override
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ListYourProductStartKoin)
            modules(modules = Modules.module)
        }
    }
}