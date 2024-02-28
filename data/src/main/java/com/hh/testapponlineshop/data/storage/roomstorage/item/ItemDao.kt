package com.hh.testapponlineshop.data.storage.roomstorage.item

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.hh.testapponlineshop.data.storage.roomstorage.item.models.ItemRoom
import java.util.UUID

@Dao
interface ItemDao
{
    @Query("SELECT * FROM items")
    suspend fun getItems(): List<ItemRoom>

    @Query("SELECT * FROM items where id = :id")
    suspend fun getItemByItemId(id: UUID): ItemRoom

    @Insert
    @JvmSuppressWildcards
    suspend fun insertAll(vararg items: ItemRoom)

    @Insert
    suspend fun insertItem(itemRoom: ItemRoom)

    @Update
    suspend fun updateItem(itemRoom: ItemRoom)

    @Delete
    suspend fun deleteItem(itemRoom: ItemRoom)
}