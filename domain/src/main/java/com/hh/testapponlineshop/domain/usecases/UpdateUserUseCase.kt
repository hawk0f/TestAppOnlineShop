package com.hh.testapponlineshop.domain.usecases

import com.hh.testapponlineshop.domain.models.UserDomain
import com.hh.testapponlineshop.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UpdateUserUseCase(private val userRepository: UserRepository)
{
    suspend fun execute(user: UserDomain): Flow<Unit>
    {
        return userRepository.updateUser(user)
    }
}