package com.campaigns

import androidx.lifecycle.ViewModel
import com.campaigns.injection.NetworkModule
import com.campaigns.injection.component.DaggerViewModelInjector
import com.campaigns.injection.component.ViewModelInjector
import com.campaigns.ui.CampaignItemViewModel
import com.campaigns.ui.CampaignViewModel

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
            is CampaignItemViewModel -> injector.inject(this)
            is CampaignViewModel -> injector.inject(this)
        }
    }
}