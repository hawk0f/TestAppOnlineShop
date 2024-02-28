package com.hh.testapponlineshop.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hh.testapponlineshop.Session
import com.hh.testapponlineshop.domain.models.UserDomain
import com.hh.testapponlineshop.domain.usecases.AddNewUserUseCase
import com.hh.testapponlineshop.domain.usecases.CheckIfUserExistsUseCase
import kotlinx.coroutines.launch

class RegistrationViewModel(private val session: Session, private val checkIfUserExistsUseCase: CheckIfUserExistsUseCase, private val addNewUserUseCase: AddNewUserUseCase) : ViewModel()
{
    var name: String = ""
    var surname: String = ""
    var phoneNumber: String = ""

    private val _isUserNew: MutableLiveData<Boolean?> = MutableLiveData(null)
    val isUserNew: LiveData<Boolean?>
        get() = _isUserNew

    fun onEnterClicked()
    {
        _isUserNew.value = null
    }

    fun onEnterClick()
    {
        viewModelScope.launch {
            var currentUser = checkIfUserExistsUseCase.execute(name, surname, phoneNumber)

            if (currentUser != null)
            {
                _isUserNew.value = false
            }
            else
            {
                currentUser = UserDomain(name = name, surname = surname, phoneNumber = phoneNumber)
                addNewUserUseCase.execute(currentUser).collect {
                    _isUserNew.value = true
                }
            }
            session.setCurrentUser(currentUser)
        }
    }
}