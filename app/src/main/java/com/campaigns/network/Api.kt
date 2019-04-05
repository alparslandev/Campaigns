package com.campaigns.network

import com.campaigns.model.Model
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    /** http://private-d190ab-campaigns19.apiary-mock.com/campaigns/20
     *  Get the list of the pots from the API
     */
    @GET("campaigns/{page}")
    fun getCampaigns(@Path("page") page: Int): Observable<Model>
}