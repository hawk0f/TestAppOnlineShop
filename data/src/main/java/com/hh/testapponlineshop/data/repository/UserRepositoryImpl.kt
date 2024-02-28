package com.hh.testapponlineshop.data.repository

import com.hh.testapponlineshop.data.storage.interfaces.ReadWriteUserStorage
import com.hh.testapponlineshop.domain.models.UserDomain
import com.hh.testapponlineshop.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class UserRepositoryImpl(private val writableItemStorage: ReadWriteUserStorage) : UserRepository
{
    override suspend fun getUsers(): Flow<List<UserDomain>>
    {
        return writableItemStorage.getUsers()
    }

    override suspend fun getUserById(id: UUID): Flow<UserDomain?>
    {
        return writableItemStorage.getUserById(id)
    }

    override suspend fun getUserByData(name: String, surname: String, phoneNumber: String): Flow<UserDomain?>
    {
        return writableItemStorage.getUserByData(name, surname, phoneNumber)
    }

    override suspend fun insertAll(users: List<UserDomain>): Flow<Unit>
    {
        return writableItemStorage.insertAll(users)
    }

    override suspend fun insertUser(user: UserDomain): Flow<Unit>
    {
        return writableItemStorage.insertUser(user)
    }

    override suspend fun updateUser(user: UserDomain): Flow<Unit>
    {
        return writableItemStorage.updateUserFavourites(user)
    }

    override suspend fun deleteUser(user: UserDomain): Flow<Unit>
    {
        return writableItemStorage.deleteUser(user)
    }
}