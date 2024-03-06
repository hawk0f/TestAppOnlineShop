package com.hh.testapponlineshop.data.storage.interfaces

import com.hh.testapponlineshop.domain.models.UserDomain
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface IReadableWritableUserStorage
{
    suspend fun getUsers(): Flow<List<UserDomain>>

    suspend fun getUserById(id: UUID): Flow<UserDomain?>

    suspend fun getUserByData(name: String, surname: String, phoneNumber: String): Flow<UserDomain?>

    suspend fun insertAll(users: List<UserDomain>): Flow<Unit>

    suspend fun insertUser(user: UserDomain): Flow<Unit>

    suspend fun updateUser(user: UserDomain): Flow<Unit>

    suspend fun deleteUser(user: UserDomain): Flow<Unit>
}