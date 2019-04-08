package com.campaigns.ui

import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import com.campaigns.BaseViewModel
import com.campaigns.R
import com.campaigns.network.Api
import com.campaigns.network.model.Response
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CampaignViewModel: BaseViewModel() {

    @Inject
    lateinit var api: Api

    private lateinit var subscription: Disposable
    var campaignsAdapter: CampaignsAdapter = CampaignsAdapter()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadCampaigns(0) }

    var page = 0
    var loadingVisibility = ObservableInt(View.GONE)
    var showEmpty = ObservableInt(View.GONE)

    init {
        loadCampaigns(page)
    }

    fun isLoading(): Boolean {
        return loadingVisibility.get() == View.VISIBLE
    }

    fun updateCampaigns() {
        loadCampaigns(page)
    }

    private fun loadCampaigns(page: Int) {
        subscription = api.getCampaigns(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveCampaignsStart() }
            .doOnTerminate { onRetrieveCampaignsFinish() }
            .subscribe(
                { result -> onRetrieveCampaginsSuccess(result) },
                { onRetrieveCampaignsError() }
            )
    }
    private fun onRetrieveCampaignsError() {
        errorMessage.value = R.string.post_error
    }

    private fun onRetrieveCampaignsStart() {
        loadingVisibility.set(View.VISIBLE)
        errorMessage.value = null
    }

    private fun onRetrieveCampaignsFinish() {
        loadingVisibility.set(View.GONE)
    }

    private fun onRetrieveCampaginsSuccess(response: Response) {
        for (counter in response.hotDeals.indices) {
            val model = response.hotDeals[counter]
            if (counter < response.banners.size && response.banners.isNotEmpty())
                model.image = response.banners[counter].image
        }
        if (page == 0) {
            campaignsAdapter.setCampaigns(response.hotDeals)
        } else {
            campaignsAdapter.updateCampaigns(response.hotDeals)
        }
        page++
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}