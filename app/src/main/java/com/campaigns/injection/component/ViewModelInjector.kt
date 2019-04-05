package com.campaigns.injection.component

import com.campaigns.injection.NetworkModule
import com.campaigns.viewmodels.CampaignViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified CampaignViewModel.
     * @param campaignViewModel CampaignViewModel in which to inject the dependencies
     */
    fun inject(campaignViewModel: CampaignViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}