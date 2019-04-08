package com.campaigns.ui

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.campaigns.App
import com.campaigns.BaseViewModel
import com.campaigns.R
import com.campaigns.network.model.HotDeal
import com.campaigns.utils.DateFormatUtils


class CampaignItemViewModel: BaseViewModel() {

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
        val originalDate = hotDealTimeMutable.value
        val stringBuilder = StringBuilder()
        val msDiff = System.currentTimeMillis() - DateFormatUtils.toDate(originalDate!!).time
        stringBuilder.append(if (msDiff < 0) {
            App.str(R.string.no_longer_available)
        } else {
            DateFormatUtils.remainingTimeToHumanReadableForm(msDiff)
        })
        hotDealTimeMutable.value = stringBuilder.toString()

        /*Timer().schedule(object : TimerTask() {
            override fun run() {
                val msDiff = System.currentTimeMillis() - DateFormatUtils.toDate(originalDate!!).time
                stringBuilder.clear()
                stringBuilder.append(if (msDiff < 0) {
                    App.str(R.string.no_longer_available)
                } else {
                    DateFormatUtils.remainingTimeToHumanReadableForm(msDiff)
                })
            }
        }, 0,1000)
        hotDealTimeMutable.value = stringBuilder.toString()*/
        return hotDealTimeMutable
    }
}