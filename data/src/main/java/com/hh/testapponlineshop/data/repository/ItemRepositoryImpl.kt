package com.hh.testapponlineshop.data.repository

import com.hh.testapponlineshop.data.storage.interfaces.IReadableItemStorage
import com.hh.testapponlineshop.data.storage.interfaces.IReadableWritableItemStorage
import com.hh.testapponlineshop.domain.models.ItemDomain
import com.hh.testapponlineshop.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow

class ItemRepositoryImpl(private val itemStorage: IReadableItemStorage, private val writableItemStorage: IReadableWritableItemStorage) : ItemRepository
{

    override suspend fun getItemsFromServer(): Flow<List<ItemDomain>>
    {
        return itemStorage.getItems()
    }

    override suspend fun getItemsFromDatabase(): Flow<List<ItemDomain>>
    {
        return writableItemStorage.getItems()
    }

    override suspend fun insertAll(items: List<ItemDomain>)
    {
        return writableItemStorage.insertAll(items)
    }

    override suspend fun insertItem(item: ItemDomain)
    {
        writableItemStorage.insertItem(item)
    }

    override suspend fun updateItem(item: ItemDomain)
    {
        writableItemStorage.updateItem(item)
    }

    override suspend fun deleteItem(item: ItemDomain)
    {
        writableItemStorage.deleteItem(item)
    }
}