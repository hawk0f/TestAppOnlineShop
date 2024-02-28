package com.hh.testapponlineshop.domain.models

data class Price(
    val discount: Int,
    val price: String,
    val priceWithDiscount: String,
    val unit: String
)