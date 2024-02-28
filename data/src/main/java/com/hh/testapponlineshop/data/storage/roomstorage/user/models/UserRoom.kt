package com.hh.testapponlineshop.data.storage.roomstorage.user.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.hh.testapponlineshop.domain.models.UserDomain
import java.util.UUID

@Entity(tableName = "users")
data class UserRoom(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val surname: String,
    val phoneNumber: String,
    var favourites: List<String> = emptyList()
)

fun UserRoom.toDomain() = UserDomain(id, name, surname, phoneNumber, favourites)

fun UserDomain.toRoom() = UserRoom(id, name, surname, phoneNumber, favourites)