package com.hh.testapponlineshop.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hh.testapponlineshop.Session
import com.hh.testapponlineshop.domain.usecases.LoadFavouriteItemsUseCase
import com.hh.testapponlineshop.domain.usecases.UpdateUserFavouriteList
import com.hh.testapponlineshop.models.ItemUI
import com.hh.testapponlineshop.models.toUi
import kotlinx.coroutines.launch

class FavouriteListViewModel(private val session: Session, private val loadFavouriteItemsUseCase: LoadFavouriteItemsUseCase, private val updateUserFavouriteList: UpdateUserFavouriteList) : ViewModel()
{
    private val _itemsUi: MutableLiveData<List<ItemUI>?> = MutableLiveData()
    val itemsUi: LiveData<List<ItemUI>?>
        get() = _itemsUi

    private val _itemClicked: MutableLiveData<ItemUI?> = MutableLiveData(null)
    val itemClicked: LiveData<ItemUI?>
        get() = _itemClicked

    fun loadFavouriteItems()
    {
        _itemsUi.value = null
        viewModelScope.launch {
            _itemsUi.value = loadFavouriteItemsUseCase.execute(session.getCurrentUser().favourites).map { it.toUi() }
        }
    }

    fun onCardClick(item: ItemUI)
    {
        _itemClicked.value = item
    }

    fun onItemNavigated()
    {
        _itemClicked.value = null
    }

    fun onFavouriteClick(item: ItemUI)
    {
        session.getCurrentUser().favourites = session.getCurrentUser().favourites.minus(item.id.toString())
        viewModelScope.launch {
            updateUserFavouriteList.execute(session.getCurrentUser()).collect {
                val items = _itemsUi.value!!.minus(item)
                _itemsUi.value = items
            }
        }
    }
}