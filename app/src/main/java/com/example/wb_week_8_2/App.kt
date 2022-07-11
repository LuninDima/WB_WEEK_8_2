package com.example.wb_week_8_2

import android.app.Application
import android.content.Context
import com.example.wb_week_8_2.di.AppComponent
import com.example.wb_week_8_2.di.AppModule
import com.example.wb_week_8_2.di.DaggerAppComponent
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.Component

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appInstants = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
         var appInstants: App? = null

        lateinit var appComponent: AppComponent

        fun getSApplicationContext(): Context {
            return appInstants!!.applicationContext
        }
    }
}