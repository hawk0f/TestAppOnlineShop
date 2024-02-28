package com.hh.testapponlineshop.domain.usecases

import com.hh.testapponlineshop.domain.models.UserDomain
import com.hh.testapponlineshop.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class AddNewUserUseCase(private val userRepository: UserRepository)
{
    suspend fun execute(user: UserDomain): Flow<Unit>
    {
        return userRepository.insertUser(user)
    }
}