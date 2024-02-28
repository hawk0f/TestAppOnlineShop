package com.hh.testapponlineshop.data.storage.mockystorage.models

import com.hh.testapponlineshop.domain.models.Feedback
import com.hh.testapponlineshop.domain.models.Info
import com.hh.testapponlineshop.domain.models.ItemDomain
import com.hh.testapponlineshop.domain.models.Price
import java.util.UUID

data class ItemMocky(
    val available: Int,
    val description: String,
    val feedback: Feedback,
    val id: UUID,
    val info: List<Info>,
    val ingredients: String,
    val price: Price,
    val subtitle: String,
    val tags: List<String>,
    val title: String
)

fun ItemMocky.toDomain(pictures: List<Int>) = ItemDomain(available, description, feedback, id, info, ingredients, price, subtitle, tags, title, pictures, false)