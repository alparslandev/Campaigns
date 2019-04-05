package com.campaigns.viewmodels

import androidx.databinding.ObservableInt
import android.view.View
import androidx.lifecycle.MutableLiveData
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

    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadCampaigns(0) }
    private val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    private lateinit var subscription: Disposable

    private var adapter = CampaignsAdapter(R.layout.item_campaign, this)
    var showEmpty = ObservableInt(View.GONE)

    init {
        loadCampaigns(0)
    }

    private fun loadCampaigns(page: Int) {
        subscription = api.getCampaigns(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveCampaignsStart() }
            .doOnTerminate { onRetrieveCampaignsFinish() }
            .subscribe(
                { onRetrieveCampaginsSuccess() },
                { onRetrieveCampaignsError() }
            )
    }
    private fun onRetrieveCampaignsError() {
        errorMessage.value = R.string.post_error
    }

    private fun onRetrieveCampaignsStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveCampaignsFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveCampaginsSuccess() { }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}