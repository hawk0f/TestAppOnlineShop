package com.hh.testapponlineshop.data.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hh.testapponlineshop.data.storage.roomstorage.item.ItemDao
import com.hh.testapponlineshop.data.storage.roomstorage.user.UserDao
import com.hh.testapponlineshop.data.storage.roomstorage.item.converters.ListInfoConverter
import com.hh.testapponlineshop.data.storage.roomstorage.item.converters.ListIntConverter
import com.hh.testapponlineshop.data.storage.roomstorage.item.converters.ListStringConverter
import com.hh.testapponlineshop.data.storage.roomstorage.item.models.ItemRoom
import com.hh.testapponlineshop.data.storage.roomstorage.user.models.UserRoom

@Database(entities = [ItemRoom::class, UserRoom::class], version = 1, exportSchema = false)
@TypeConverters(value = [ListIntConverter::class, ListStringConverter::class, ListInfoConverter::class])
abstract class AppDatabase : RoomDatabase()
{
    abstract val itemDao: ItemDao

    abstract val userDao: UserDao

    companion object
    {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase
        {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null)
                {
                    instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "MyDatabase").build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}