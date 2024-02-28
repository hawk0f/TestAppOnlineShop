package com.hh.testapponlineshop.domain.usecases

import com.hh.testapponlineshop.domain.models.ItemDomain
import com.hh.testapponlineshop.domain.repository.ItemRepository

class LoadFavouriteItemsUseCase(private val itemRepository: ItemRepository)
{
    suspend fun execute(favouritesIds: List<String>): List<ItemDomain>
    {
        var dbItems: List<ItemDomain> = ArrayList()
        itemRepository.getItemsFromDatabase().collect {
            dbItems = it
        }

        dbItems.filter { favouritesIds.contains(it.id.toString()) }.forEach { it.isFavourite = true }
        dbItems = dbItems.filter { it.isFavourite }

        return dbItems
    }
}