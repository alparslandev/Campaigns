package com.campaigns.injection.component

import com.campaigns.injection.NetworkModule
import com.campaigns.ui.CampaignItemViewModel
import com.campaigns.ui.CampaignViewModel
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

    /**
     * Injects required dependencies into the specified CampaignItemViewModel.
     * @param campaignItemViewModel CampaignItemViewModel in which to inject the dependencies
     */
    fun inject(campaignItemViewModel: CampaignItemViewModel)
    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}