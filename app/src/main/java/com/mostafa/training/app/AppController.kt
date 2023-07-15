package com.mostafa.training.app

import android.app.Application
import com.mostafa.training.di.appModule
import com.mostafa.training.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppController :Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(provideModules())
        }
    }

    private fun provideModules() = listOf(appModule, networkModule)
}

