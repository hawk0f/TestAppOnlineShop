package com.hh.testapponlineshop.domain.usecases

import com.hh.testapponlineshop.domain.models.UserDomain
import com.hh.testapponlineshop.domain.repository.UserRepository

class CheckIfUserExistsUseCase(private val userRepository: UserRepository)
{
    suspend fun execute(name: String, surname: String, phoneNumber: String) : UserDomain?
    {
        var user: UserDomain? = null
        userRepository.getUserByData(name, surname, phoneNumber).collect {
            user = it
        }

        return user
    }
}