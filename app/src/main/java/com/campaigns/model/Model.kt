package com.campaigns.model

data class Model(
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
    val title: String
)