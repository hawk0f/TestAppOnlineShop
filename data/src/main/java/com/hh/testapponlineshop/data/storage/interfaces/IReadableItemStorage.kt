package com.hh.testapponlineshop.data.storage.interfaces

import com.hh.testapponlineshop.domain.models.ItemDomain
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface IReadableItemStorage
{
    suspend fun getItems() : Flow<List<ItemDomain>>

    suspend fun getItemById(id: UUID): Flow<ItemDomain>
}