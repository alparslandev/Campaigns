package com.campaigns.ui

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.campaigns.App
import com.campaigns.BaseViewModel
import com.campaigns.network.model.HotDeal
import com.campaigns.utils.DateFormatUtils
import javax.inject.Inject

class CampaignItemViewModel: BaseViewModel() {

    @Inject
    lateinit var app: App

    private val hotDealMutable = MutableLiveData<String>()
    private val hotDealTimeMutable = MutableLiveData<String>()
    val resultImageUrl = ObservableField<String>()

    private lateinit var campaignHotDeal: HotDeal

    fun bind(hotDeal: HotDeal) {
        campaignHotDeal = hotDeal
        hotDealMutable.value = hotDeal.title
        hotDealTimeMutable.value = hotDeal.expirationDate
        if (hotDeal.image != null && hotDeal.image!!.url.isNotEmpty())
            resultImageUrl.set(hotDeal.image!!.url + "x" + hotDeal.image!!.height + "x" + hotDeal.image!!.width)
    }

    fun getHotDeal(position: Int): MutableLiveData<String> {
        hotDealMutable.value = hotDealMutable.value + " #" + position
        return hotDealMutable
    }

    fun getHotDealTime(): MutableLiveData<String> {
        hotDealTimeMutable.value = DateFormatUtils.remainingTimeToHumanReadableForm(
            DateFormatUtils.toDate(hotDealTimeMutable.value!!).time, app.applicationContext)
        return hotDealTimeMutable
    }
}