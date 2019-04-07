package com.campaigns.injection

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val context: Context) {
    @Provides
    fun context(): Context {
        return context
    }
}