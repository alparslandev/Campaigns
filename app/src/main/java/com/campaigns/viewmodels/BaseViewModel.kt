package com.campaigns.viewmodels

import androidx.lifecycle.ViewModel
import com.campaigns.injection.component.ViewModelInjector
import com.campaigns.injection.NetworkModule
import com.campaigns.injection.component.DaggerViewModelInjector

abstract class BaseViewModel: ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is CampaignViewModel -> injector.inject(this)
        }
    }
}