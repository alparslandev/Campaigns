package com.campaigns.ui

import androidx.lifecycle.MutableLiveData
import com.campaigns.BaseViewModel
import com.campaigns.model.Banner
import com.campaigns.model.HotDeal

class CampaignItemViewModel : BaseViewModel() {

    fun bind(banner: Banner, hotDeal: HotDeal) {
        campaignHotDeal.value = hotDeal
        campaignBanner.value = banner
    }

    companion object {

        private val campaignHotDeal = MutableLiveData<HotDeal>()
        private val campaignBanner = MutableLiveData<Banner>()

        /*fun getBannerUrl():MutableLiveData<String>{
            return campaignBanner.value.toString()
        }*/

        fun getHotDeal():MutableLiveData<String>{
            return MutableLiveData((campaignHotDeal.value as HotDeal).title)
        }

        fun getHotDealTime():MutableLiveData<String>{
            // todo time conversion with Koda
            val timeStr = campaignHotDeal.value as HotDeal
            return MutableLiveData(timeStr.title)
        }
    }
}