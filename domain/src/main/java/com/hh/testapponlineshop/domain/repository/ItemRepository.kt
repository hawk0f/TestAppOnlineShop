package com.hh.testapponlineshop.domain.repository

import com.hh.testapponlineshop.domain.models.ItemDomain
import kotlinx.coroutines.flow.Flow

interface ItemRepository
{
    suspend fun getItemsFromServer(): Flow<List<ItemDomain>>
    suspend fun getItemsFromDatabase(): Flow<List<ItemDomain>>

    suspend fun insertAll(items: List<ItemDomain>)
    suspend fun insertItem(item: ItemDomain)
    suspend fun updateItem(item: ItemDomain)
    suspend fun deleteItem(item: ItemDomain)
}