package com.campaigns.network.model

data class Response(
    val banners: List<Banner>,
    val hotDeals: List<HotDeal>
)

data class Banner(
    val image: Image
)

data class Image(
    val height: Int,
    val url: String,
    val width: Int
)

data class HotDeal(
    val expirationDate: String,
    val title: String,
    var image: Image? = null
)