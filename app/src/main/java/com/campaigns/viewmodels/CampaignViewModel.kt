package com.campaigns.viewmodels

import androidx.databinding.ObservableInt
import android.view.View
import com.campaigns.R
import com.campaigns.adapter.CampaignsAdapter
import com.campaigns.network.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CampaignViewModel: BaseViewModel() {

    @Inject
    lateinit var api: Api

    private lateinit var subscription: Disposable

    private var adapter = CampaignsAdapter(R.layout.item_campaign, this)
    var loading = ObservableInt(View.GONE)
    var showEmpty = ObservableInt(View.GONE)

    init {
        loadCampaigns(0)
    }

    private fun loadCampaigns(page: Int) {
        subscription = api.getCampaigns(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { onRetrievePostListSuccess() },
                { onRetrievePostListError() }
            )
    }
    private fun onRetrievePostListError() { }

    private fun onRetrievePostListStart() { }

    private fun onRetrievePostListFinish() { }

    private fun onRetrievePostListSuccess() { }
    
    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}