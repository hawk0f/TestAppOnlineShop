package com.hh.testapponlineshop.data.storage.mockystorage

import com.hh.testapponlineshop.data.storage.mockystorage.models.ItemObject
import retrofit2.http.GET

interface ItemApi
{
    @GET("97e721a7-0a66-4cae-b445-83cc0bcf9010")
    suspend fun getItemObject(): ItemObject
}