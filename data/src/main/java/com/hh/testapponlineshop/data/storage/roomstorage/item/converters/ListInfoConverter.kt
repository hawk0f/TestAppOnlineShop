package com.hh.testapponlineshop.data.storage.roomstorage.item.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.hh.testapponlineshop.domain.models.Info

class ListInfoConverter
{
    @TypeConverter
    fun fromList(value : List<Info>) = Gson().toJson(value)!!

    @TypeConverter
    fun toList(value: String) = Gson().fromJson(value, Array<Info>::class.java).toList()
}