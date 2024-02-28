package com.hh.testapponlineshop.viewModels

import android.text.Html
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hh.testapponlineshop.Session
import com.hh.testapponlineshop.domain.usecases.UpdateUserFavouriteList
import com.hh.testapponlineshop.models.ItemUI
import kotlinx.coroutines.launch

class CatalogItemInfoViewModel(private val session: Session, private val updateUserFavouriteList: UpdateUserFavouriteList) : ViewModel()
{
    private lateinit var itemUI: ItemUI

    var title = ""
    var subtitle = ""
    var description = ""
    var available = ""
    var ratingValue = 0f
    var ratingAndFeedback = ""
    var ingredients = ""
    var oldPrice = ""
    var newPrice = ""
    var discount = ""
    var isFavourite = false

    private val _updateFavouriteIcon: MutableLiveData<Boolean?> = MutableLiveData()
    val updateFavouriteIcon: LiveData<Boolean?>
        get() = _updateFavouriteIcon

    fun setItemUI(itemUI: ItemUI)
    {
        this.itemUI = itemUI
        title = itemUI.title
        subtitle = itemUI.subtitle
        description = itemUI.description
        available = getStringAmount(itemUI.available)
        ratingValue = itemUI.feedback.rating.toFloat()
        ratingAndFeedback = "${itemUI.feedback.rating} ${Html.fromHtml("&#183;")} ${getStringFeedback(itemUI.feedback.count)}"
        ingredients = itemUI.ingredients
        oldPrice = "${itemUI.price.price} ${itemUI.price.unit}"
        newPrice = "${itemUI.price.priceWithDiscount} ${itemUI.price.unit}"
        discount = "-${itemUI.price.discount}%"
        isFavourite = itemUI.isFavourite
    }

    fun onFavouriteClick()
    {
        if (session.getCurrentUser().favourites.contains(itemUI.id.toString()))
        {
            session.getCurrentUser().favourites = session.getCurrentUser().favourites.minus(itemUI.id.toString())
        }
        else
        {
            session.getCurrentUser().favourites = session.getCurrentUser().favourites.plus(itemUI.id.toString())
        }
        viewModelScope.launch {
            updateUserFavouriteList.execute(session.getCurrentUser()).collect {
                _updateFavouriteIcon.value = true
            }
        }
    }

    private fun getStringAmount(available: Int): String
    {
        val forms = listOf("штука", "штуки", "штук")
        return when
        {
            available % 10 == 1 && available % 100 != 11 -> "Доступно для заказа $available ${forms[0]}"
            available % 10 in 2..4 && available % 100 !in 12..14 -> "Доступно для заказа $available ${forms[1]}"
            else -> "Доступно для заказа $available ${forms[2]}"
        }
    }

    private fun getStringFeedback(feedback: Int): String
    {
        val forms = listOf("отзыв", "отзыва", "отзывов")
        return when
        {
            feedback % 10 == 1 && feedback % 100 != 11 -> "$feedback ${forms[0]}"
            feedback % 10 in 2..4 && feedback % 100 !in 12..14 -> "$feedback ${forms[1]}"
            else -> "$feedback ${forms[2]}"
        }
    }
}