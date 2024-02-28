package com.hh.testapponlineshop.models

import com.hh.testapponlineshop.domain.models.Feedback
import com.hh.testapponlineshop.domain.models.Info
import com.hh.testapponlineshop.domain.models.ItemDomain
import com.hh.testapponlineshop.domain.models.Price
import java.io.Serializable
import java.util.UUID

/**
 * Класс на случай вспомогательных свойств UI
 */
data class ItemUI(
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
    val pictures: List<Int>,
    var isFavourite: Boolean
) : Serializable

fun ItemDomain.toUi() = ItemUI(available, description, feedback, id, info, ingredients, price, subtitle, tags, title, pictures, isFavourite)
fun ItemUI.toDomain() = ItemDomain(available, description, feedback, id, info, ingredients, price, subtitle, tags, title, pictures, isFavourite)