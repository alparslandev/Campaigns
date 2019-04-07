package com.campaigns

import android.app.Application
import com.campaigns.injection.ApplicationModule
import com.campaigns.injection.component.DaggerApplicationInjector

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val injector = DaggerApplicationInjector.builder()
            .applicationModule(ApplicationModule(this))
            .build()
        injector.inject(this@App)
    }
}