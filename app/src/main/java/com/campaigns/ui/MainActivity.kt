package com.campaigns.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.campaigns.BaseActivity
import com.campaigns.R
import com.campaigns.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CampaignViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.rvCampaigns.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        viewModel = ViewModelProviders.of(this).get(CampaignViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
            errorMessage -> if (errorMessage != null) showError(errorMessage, viewModel.errorClickListener, binding.root) else hideError()
        })
        binding.model = viewModel
    }
}