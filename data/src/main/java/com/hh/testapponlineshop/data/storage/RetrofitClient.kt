package com.hh.testapponlineshop.data.storage

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient
{
    private const val BASE_URL = "https://run.mocky.io/v3/"

    private var retrofitClient: Retrofit? = null

    fun getInstance() : Retrofit
    {
        if (retrofitClient == null)
        {
            retrofitClient = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        }

        return retrofitClient!!
    }
}