package com.example.wb_week_8_2

import android.app.Application
import android.content.Context
import com.github.terrakok.cicerone.Cicerone

class App : Application() {
    private val cicerone = Cicerone.create()
     val router = cicerone.router
     val navigatorHolder = cicerone.getNavigatorHolder()
    override fun onCreate() {
        super.onCreate()
        appInstants = this
    }

    companion object {
         var appInstants: App? = null
        fun getSApplicationContext(): Context {
            return appInstants!!.applicationContext
        }
    }
}