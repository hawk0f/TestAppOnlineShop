package com.hh.testapponlineshop.data.storage.roomstorage.item.converters

import androidx.room.TypeConverter
import com.google.gson.Gson

class ListIntConverter
{
    @TypeConverter
    fun fromList(value : List<Int>) = Gson().toJson(value)!!

    @TypeConverter
    fun toList(value: String) = Gson().fromJson(value, Array<Int>::class.java).toList()
}