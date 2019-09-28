package com.ibarra.news

import android.app.Application
import com.ibarra.news.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class IbarraNewsApp : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@IbarraNewsApp)
            modules(listOf(NetworkModule))
        }

    }
}