package com.cbp.test

import android.app.Application
import androidx.room.Room
import com.cbp.test.data.CBPDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val database = Room.databaseBuilder(applicationContext, CBPDatabase::class.java, "product.db")
            .build()

        startKoin {
            androidContext(applicationContext)
            modules(Modules.router, Modules.database(database))
        }
    }
}