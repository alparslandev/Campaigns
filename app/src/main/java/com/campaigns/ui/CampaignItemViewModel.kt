package com.campaigns.ui

import androidx.lifecycle.MutableLiveData
import com.campaigns.BaseViewModel
import com.campaigns.network.model.HotDeal
import com.campaigns.network.model.Image

class CampaignItemViewModel: BaseViewModel() {

    fun bind(image: Image, hotDeal: HotDeal) {
        campaignHotDeal.value = hotDeal
        campaignImage.value = image
    }

    companion object {

        private val campaignHotDeal = MutableLiveData<HotDeal>()
        private val campaignImage = MutableLiveData<Image>()

        /*fun getBannerUrl():MutableLiveData<String>{
            return campaignImage.value.toString()
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