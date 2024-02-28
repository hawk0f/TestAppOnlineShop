package com.hh.testapponlineshop.data.storage.interfaces

import com.hh.testapponlineshop.domain.models.ItemDomain
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface ReadOnlyItemStorage
{
    suspend fun loadItems() : Flow<List<ItemDomain>>

    suspend fun getItem(id: UUID): Flow<ItemDomain>
}