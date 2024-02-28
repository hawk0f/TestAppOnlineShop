package com.hh.testapponlineshop.data.storage.roomstorage.item.converters

import androidx.room.TypeConverter
import com.google.gson.Gson

class ListStringConverter {
    @TypeConverter
    fun fromList(value : List<String>) = Gson().toJson(value)!!

    @TypeConverter
    fun toList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()
}