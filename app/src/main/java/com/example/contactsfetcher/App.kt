package com.example.contactsfetcher

import android.app.Application
import com.example.contactsfetcher.di.modules.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        configureKoin()
    }

    private fun configureKoin() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            koin.loadModules(koinModules)
            koin.createRootScope()
        }
    }
}