package com.hh.testapponlineshop.data.repository

import com.hh.testapponlineshop.data.storage.interfaces.ReadOnlyItemStorage
import com.hh.testapponlineshop.data.storage.interfaces.ReadWriteItemStorage
import com.hh.testapponlineshop.domain.models.ItemDomain
import com.hh.testapponlineshop.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow

class ItemsRepositoryImpl(private val itemStorage: ReadOnlyItemStorage, private val writableItemStorage: ReadWriteItemStorage) : ItemRepository
{

    override suspend fun getItemsFromServer(): Flow<List<ItemDomain>>
    {
        return itemStorage.loadItems()
    }

    override suspend fun getItemsFromDatabase(): Flow<List<ItemDomain>>
    {
        return writableItemStorage.loadItems()
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