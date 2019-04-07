package com.campaigns.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.campaigns.BR
import com.campaigns.R
import com.campaigns.databinding.ItemCampaignBinding
import com.campaigns.network.model.HotDeal

class CampaignsAdapter : RecyclerView.Adapter<CampaignsAdapter.GenericViewHolder>() {
    private lateinit var hotDeals: List<HotDeal>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        val binding: ItemCampaignBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_campaign, parent, false)
        return GenericViewHolder(binding)
    }

    override fun getItemCount() = if(::hotDeals.isInitialized) hotDeals.size else 0

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        holder.bind(hotDeals[position], holder.adapterPosition)
    }

    fun updateCampaigns(campaigns:List<HotDeal>){
        this.hotDeals = campaigns
        notifyDataSetChanged()
    }

    inner class GenericViewHolder(val binding: ItemCampaignBinding):
        ViewHolder(binding.root) {
        private val viewModel = CampaignItemViewModel()

        fun bind(hotDeal: HotDeal, position: Int) {
            viewModel.bind(hotDeal)
            //viewModel.fetchCampaignsImagesAt(position)
            binding.setVariable(BR.viewModel, viewModel)
            binding.setVariable(BR.position, position)
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }
}