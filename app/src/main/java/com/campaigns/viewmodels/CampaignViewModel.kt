package com.campaigns.viewmodels

import androidx.databinding.ObservableInt
import android.view.View
import com.campaigns.R
import com.campaigns.adapter.CampaignsAdapter
import com.campaigns.network.Api
import javax.inject.Inject

class CampaignViewModel: BaseViewModel() {

    @Inject
    lateinit var api: Api

    private var adapter = CampaignsAdapter(R.layout.item_campaign, this)
    var loading = ObservableInt(View.GONE)
    var showEmpty = ObservableInt(View.GONE)

    public fun getAdapter() = adapter
}