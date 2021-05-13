package com.tibaes.search

import android.app.Application
import com.tibaes.search.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SearchApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SearchApplication)
            modules(appModules)
        }
    }
}