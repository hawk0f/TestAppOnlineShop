package com.hh.testapponlineshop.data.storage.mockystorage

import com.hh.data.R
import com.hh.testapponlineshop.data.storage.RetrofitClient
import com.hh.testapponlineshop.data.storage.interfaces.IReadableItemStorage
import com.hh.testapponlineshop.data.storage.mockystorage.models.toDomain
import com.hh.testapponlineshop.domain.models.ItemDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID

class MockyItemStorage : IReadableItemStorage
{
    private val retrofit = RetrofitClient.getInstance()
    private val itemApiService = retrofit.create(ItemApi::class.java)

    private val dictionary: Map<UUID, List<Int>> = mapOf(UUID.fromString("cbf0c984-7c6c-4ada-82da-e29dc698bb50") to listOf(R.drawable.vox, R.drawable.eveline), UUID.fromString("54a876a5-2205-48ba-9498-cfecff4baa6e") to listOf(R.drawable.deep, R.drawable.coenzyme), UUID.fromString("75c84407-52e1-4cce-a73a-ff2d3ac031b3") to listOf(R.drawable.eveline, R.drawable.vox), UUID.fromString("16f88865-ae74-4b7c-9d85-b68334bb97db") to listOf(R.drawable.deco, R.drawable.lp_care), UUID.fromString("26f88856-ae74-4b7c-9d85-b68334bb97db") to listOf(R.drawable.coenzyme, R.drawable.deco), UUID.fromString("15f88865-ae74-4b7c-9d81-b78334bb97db") to listOf(R.drawable.vox, R.drawable.deep), UUID.fromString("88f88865-ae74-4b7c-9d81-b78334bb97db") to listOf(R.drawable.lp_care, R.drawable.deco), UUID.fromString("55f58865-ae74-4b7c-9d81-b78334bb97db") to listOf(R.drawable.deep, R.drawable.eveline))

    override suspend fun getItems(): Flow<List<ItemDomain>> = flow {
        val itemsDomain = ArrayList<ItemDomain>()
        val obj = itemApiService.getItemObject()
        obj.items.forEach {
            itemsDomain.add(it.toDomain(dictionary[it.id]!!))
        }
        emit(itemsDomain)
    }

    override suspend fun getItemById(id: UUID): Flow<ItemDomain> = flow {
        getItems().collect { listDomain ->
            emit(listDomain.first { it.id == id })
        }
    }
}