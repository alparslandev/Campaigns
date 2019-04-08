package com.campaigns

import android.app.Application
import android.content.Context
import androidx.annotation.StringRes

class App : Application() {

    companion object {
        lateinit var appContext: Context
        fun str(@StringRes id: Int): String {
            return if (::appContext.isInitialized) appContext.getString(id) else ""
        }
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

}