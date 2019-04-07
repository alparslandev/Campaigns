package com.campaigns.injection.component

import android.content.Context
import com.campaigns.App
import com.campaigns.injection.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [(ApplicationModule::class)])
@Singleton
interface ApplicationInjector {
    fun context(): Context
    fun inject(application: App)
}