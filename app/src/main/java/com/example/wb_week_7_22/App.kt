package com.example.wb_week_7_22

import android.app.Application
import android.content.Context

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appInstants = this
    }

    companion object {
        private var appInstants: App? = null
        fun getSApplicationContext(): Context {
            return appInstants!!.applicationContext
        }
    }
}