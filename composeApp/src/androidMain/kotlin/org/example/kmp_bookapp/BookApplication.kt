package org.example.kmp_bookapp

import android.app.Application
import org.example.kmp_bookapp.di.initKoin
import org.koin.android.ext.koin.androidContext

class BookApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin{
            androidContext(this@BookApplication)
        }
    }
}