package com.hh.testapponlineshop.domain.models

import java.util.UUID

open class ItemDomain(
    val available: Int,
    val description: String,
    val feedback: Feedback,
    val id: UUID,
    val info: List<Info>,
    val ingredients: String,
    val price: Price,
    val subtitle: String,
    val tags: List<String>,
    val title: String,
    var pictures: List<Int>,
    var isFavourite: Boolean
)