package com.hh.testapponlineshop.data.storage.roomstorage.user

import com.hh.testapponlineshop.data.storage.roomstorage.user.models.toDomain
import com.hh.testapponlineshop.data.storage.roomstorage.user.models.toRoom
import com.hh.testapponlineshop.data.storage.interfaces.IReadableWritableUserStorage
import com.hh.testapponlineshop.domain.models.UserDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID

class RoomUserStorage(private val userDao: UserDao) : IReadableWritableUserStorage
{
    override suspend fun getUsers(): Flow<List<UserDomain>> = flow {
        emit(userDao.getUsers().map { it.toDomain() })
    }

    override suspend fun getUserById(id: UUID): Flow<UserDomain?> = flow {
        emit(userDao.getUserById(id)?.toDomain())
    }

    override suspend fun getUserByData(name: String, surname: String, phoneNumber: String): Flow<UserDomain?> = flow {
        emit(userDao.getUserByData(name, surname, phoneNumber)?.toDomain())
    }

    override suspend fun insertAll(users: List<UserDomain>): Flow<Unit> = flow {
        val usersRoom = users.map { it.toRoom() }
        userDao.insertAll(*usersRoom.toTypedArray())
        emit(Unit)
    }

    override suspend fun insertUser(user: UserDomain): Flow<Unit> = flow {
        userDao.insertUser(user.toRoom())
        emit(Unit)
    }

    override suspend fun updateUser(user: UserDomain): Flow<Unit> = flow {
        userDao.updateUser(user.toRoom())
        emit(Unit)
    }

    override suspend fun deleteUser(user: UserDomain): Flow<Unit> = flow {
        userDao.deleteUser(user.toRoom())
        emit(Unit)
    }
}