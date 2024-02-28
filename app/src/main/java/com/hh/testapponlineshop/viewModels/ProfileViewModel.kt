package com.hh.testapponlineshop.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hh.testapponlineshop.Session
import com.hh.testapponlineshop.domain.usecases.DeleteUserUseCase
import com.hh.testapponlineshop.domain.usecases.LoadFavouriteItemsUseCase
import com.hh.testapponlineshop.models.toUi
import kotlinx.coroutines.launch

class ProfileViewModel(private val session: Session, private val loadFavouriteItemsUseCase: LoadFavouriteItemsUseCase, private val deleteUserUseCase: DeleteUserUseCase) : ViewModel()
{
    var itemsFavouriteAmount = MutableLiveData<String>()
    var name = session.getCurrentUser().name
    var surname = session.getCurrentUser().surname
    var phoneNumber = session.getCurrentUser().phoneNumber

    private val _userHasDeleted: MutableLiveData<Boolean?> = MutableLiveData()
    val userHasDeleted: LiveData<Boolean?>
        get() = _userHasDeleted

    fun getAmountOfFavourites()
    {
        viewModelScope.launch {
            itemsFavouriteAmount.value = getRightForm(loadFavouriteItemsUseCase.execute(session.getCurrentUser().favourites).map { it.toUi() }.size)
        }
    }

    private fun getRightForm(count: Int): String
    {
        val forms = arrayOf("товар", "товара", "товаров")
        val cases = arrayOf(2, 0, 1, 1, 1, 2)

        val index = if (count % 100 in 5..20) 2 else cases[minOf(count % 10, 5)]
        return "$count ${forms[index]}"
    }

    fun onLogoutClick()
    {
        viewModelScope.launch {
            deleteUserUseCase.execute(session.getCurrentUser()).collect {
                _userHasDeleted.value = true
            }
        }
    }

    fun onLogoutClicked()
    {
        _userHasDeleted.value = null
    }
}