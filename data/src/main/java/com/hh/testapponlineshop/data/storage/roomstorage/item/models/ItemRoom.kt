package com.hh.testapponlineshop.data.storage.roomstorage.item.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hh.testapponlineshop.domain.models.Feedback
import com.hh.testapponlineshop.domain.models.Info
import com.hh.testapponlineshop.domain.models.ItemDomain
import com.hh.testapponlineshop.domain.models.Price
import java.util.UUID

@Entity(tableName = "items")
data class ItemRoom(val available: Int,
                    val description: String,
                    val count: Int,
                    val rating: Double,
                    @PrimaryKey
                    val id: String,
                    val info: List<Info>,
                    val ingredients: String,
                    val discount: Int,
                    val price: String,
                    val priceWithDiscount: String,
                    val unit: String,
                    val subtitle: String,
                    val tags: List<String>,
                    val title: String,
                    val pictures: List<Int>,
                    var isFavourite: Boolean)

fun ItemRoom.toDomain() : ItemDomain = ItemDomain(available, description, Feedback(count, rating), UUID.fromString(id), info, ingredients, Price(discount, price, priceWithDiscount, unit), subtitle, tags, title, pictures, isFavourite)

fun ItemDomain.toRoom() : ItemRoom = ItemRoom(available, description, feedback.count, feedback.rating, id.toString(), info, ingredients, price.discount, price.price,price.priceWithDiscount, price.unit, subtitle, tags, title, pictures, isFavourite)