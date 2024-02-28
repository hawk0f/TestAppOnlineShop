package com.hh.testapponlineshop.data.storage.roomstorage.item

import com.hh.testapponlineshop.data.storage.roomstorage.item.models.toDomain
import com.hh.testapponlineshop.data.storage.roomstorage.item.models.toRoom
import com.hh.testapponlineshop.data.storage.interfaces.ReadWriteItemStorage
import com.hh.testapponlineshop.domain.models.ItemDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID

class RoomItemStorage(private val itemDao: ItemDao) : ReadWriteItemStorage
{
    override suspend fun loadItems(): Flow<List<ItemDomain>> = flow {
        emit(itemDao.getItems().map { it.toDomain() })
    }

    override suspend fun getItem(id: UUID): Flow<ItemDomain> = flow {
        emit(itemDao.getItemByItemId(id).toDomain())
    }

    override suspend fun insertAll(items: List<ItemDomain>)
    {
        val itemsRoom = items.map { it.toRoom() }
        itemDao.insertAll(*itemsRoom.toTypedArray())
    }

    override suspend fun insertItem(item: ItemDomain)
    {
        itemDao.insertItem(item.toRoom())
    }

    override suspend fun updateItem(item: ItemDomain)
    {
        itemDao.updateItem(item.toRoom())
    }

    override suspend fun deleteItem(item: ItemDomain)
    {
        itemDao.deleteItem(item.toRoom())
    }
}