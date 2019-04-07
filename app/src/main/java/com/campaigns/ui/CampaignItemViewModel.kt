package com.campaigns.ui

import androidx.lifecycle.MutableLiveData
import com.campaigns.BaseViewModel
import com.campaigns.network.model.HotDeal

class CampaignItemViewModel: BaseViewModel() {

    private val hotDealMutable = MutableLiveData<String>()
    private val hotDealTimeMutable = MutableLiveData<String>()

    private lateinit var campaignHotDeal: HotDeal

    fun bind(hotDeal: HotDeal) {
        campaignHotDeal = hotDeal
        hotDealMutable.value = hotDeal.title
        hotDealTimeMutable.value = hotDeal.expirationDate
    }

    /*fun getBannerUrl():MutableLiveData<String>{
        return campaignImage.value.toString()
    }*/

    fun getHotDeal(): MutableLiveData<String> {
        return hotDealMutable
    }

    fun getHotDealTime(): MutableLiveData<String> {
        // todo time conversion with Koda
        return hotDealTimeMutable
    }
}