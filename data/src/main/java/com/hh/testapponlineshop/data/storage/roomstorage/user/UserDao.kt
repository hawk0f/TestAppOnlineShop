package com.hh.testapponlineshop.data.storage.roomstorage.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.hh.testapponlineshop.data.storage.roomstorage.user.models.UserRoom
import java.util.UUID

@Dao
interface UserDao
{
    @Query("SELECT * FROM users")
    suspend fun getUsers(): List<UserRoom>

    @Query("SELECT * FROM users where id = :id")
    suspend fun getUserById(id: UUID): UserRoom?

    @Query("SELECT * FROM users where name = :name and surname = :surname and phoneNumber = :phoneNumber")
    suspend fun getUserByData(name: String, surname: String, phoneNumber: String): UserRoom?

    @Insert
    @JvmSuppressWildcards
    suspend fun insertAll(vararg items: UserRoom)

    @Insert
    suspend fun insertUser(user: UserRoom)

    @Update
    suspend fun updateUser(userRoom: UserRoom)

    @Delete
    suspend fun deleteUser(user: UserRoom)
}