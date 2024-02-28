package com.hh.testapponlineshop.data.storage.interfaces

import com.hh.testapponlineshop.domain.models.ItemDomain

/**
 * Интерфейс создан из-за read-only Json-Mocky(только get), так как в общем интерфейсе не определить всю функциональность работы с Item
 */
interface WritableItemStorage
{
    suspend fun insertAll(items: List<ItemDomain>)
    suspend fun insertItem(item: ItemDomain)
    suspend fun updateItem(item: ItemDomain)
    suspend fun deleteItem(item: ItemDomain)
}