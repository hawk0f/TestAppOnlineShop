package com.hh.testapponlineshop.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hh.testapponlineshop.Session
import com.hh.testapponlineshop.domain.models.ItemDomain
import com.hh.testapponlineshop.domain.usecases.LoadItemsUseCase
import com.hh.testapponlineshop.domain.usecases.UpdateUserFavouriteList
import com.hh.testapponlineshop.models.ItemUI
import com.hh.testapponlineshop.models.toUi
import kotlinx.coroutines.launch

class CatalogViewModel(private val loadItemsUseCase: LoadItemsUseCase, private val session: Session, private val updateUserFavouriteList: UpdateUserFavouriteList) : ViewModel()
{
    private val _items: MutableLiveData<List<ItemDomain>> = MutableLiveData()
    val items: LiveData<List<ItemDomain>>
        get() = _items

    private val _itemsUi: MutableLiveData<List<ItemUI>> = MutableLiveData()
    val itemsUi: LiveData<List<ItemUI>>
        get() = _itemsUi

    private val _filteredList: MutableLiveData<List<ItemUI>?> = MutableLiveData()
    val filteredList: LiveData<List<ItemUI>?>
        get() = _filteredList

    private val _itemClicked: MutableLiveData<ItemUI?> = MutableLiveData(null)
    val itemClicked: LiveData<ItemUI?>
        get() = _itemClicked

    init
    {
        viewModelScope.launch {
            _items.value = loadItemsUseCase.execute()
        }
    }

    fun setFavouriteStatus()
    {
        val items = _items.value!!.map { it.toUi() }
        items.filter { session.getCurrentUser().favourites.contains(it.id.toString()) }.forEach { it.isFavourite = true }
        _itemsUi.value = items
    }

    fun onCardClick(item: ItemUI)
    {
        _itemClicked.value = item
    }

    fun onItemNavigated()
    {
        _itemClicked.value = null
    }

    fun filterAll()
    {
        _filteredList.value = _itemsUi.value
    }

    fun filterBody()
    {
        _filteredList.value = _itemsUi.value!!.filter { it.tags.contains("body") }
    }

    fun filterFace()
    {
        _filteredList.value = _itemsUi.value!!.filter { it.tags.contains("face") }
    }

    fun filterSuntan()
    {
        _filteredList.value = _itemsUi.value!!.filter { it.tags.contains("suntan") }
    }

    fun filterMask()
    {
        _filteredList.value = _itemsUi.value!!.filter { it.tags.contains("mask") }
    }

    fun sortByPopularity()
    {
        _filteredList.value = _filteredList.value!!.sortedBy { it.feedback.rating }
    }

    fun sortByPrice()
    {
        _filteredList.value = _filteredList.value!!.sortedBy { it.price.priceWithDiscount.toInt() }
    }

    fun sortByPriceDescending()
    {
        _filteredList.value = _filteredList.value!!.sortedByDescending { it.price.priceWithDiscount.toInt() }
    }

    fun onFavouriteClick(item: ItemUI)
    {
        if (session.getCurrentUser().favourites.contains(item.id.toString()))
        {
            session.getCurrentUser().favourites = session.getCurrentUser().favourites.minus(item.id.toString())
        }
        else
        {
            session.getCurrentUser().favourites = session.getCurrentUser().favourites.plus(item.id.toString())
        }
        viewModelScope.launch {
            updateUserFavouriteList.execute(session.getCurrentUser()).collect {
                _filteredList.value = _filteredList.value
            }
        }
    }
}