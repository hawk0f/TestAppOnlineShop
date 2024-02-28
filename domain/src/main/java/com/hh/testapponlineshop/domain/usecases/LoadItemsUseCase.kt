package com.hh.testapponlineshop.domain.usecases

import com.hh.testapponlineshop.domain.models.ItemDomain
import com.hh.testapponlineshop.domain.repository.ItemRepository

class LoadItemsUseCase(private val itemRepository: ItemRepository)
{
    suspend fun execute(): List<ItemDomain>
    {
        var dbItems: List<ItemDomain> = ArrayList()
        itemRepository.getItemsFromDatabase().collect {
            dbItems = it
        }
        //Не сокращаю для читаемости
        if (dbItems.isEmpty())
        {
            var serverItems: List<ItemDomain> = ArrayList()
            itemRepository.getItemsFromServer().collect {
                serverItems = it
            }
            itemRepository.insertAll(serverItems)
            return serverItems
        }
        else
        {
            return dbItems
        }
    }
}