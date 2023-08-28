package com.example.codeclubapp.src.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CodeClubApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CodeClubApplication)
            modules(viewModelModule, daoImplModule, databaseModule)
        }
    }
}