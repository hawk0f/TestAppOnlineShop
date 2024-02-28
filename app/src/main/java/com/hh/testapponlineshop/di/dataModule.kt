package com.hh.testapponlineshop.di

import com.hh.testapponlineshop.data.repository.ItemsRepositoryImpl
import com.hh.testapponlineshop.data.repository.UserRepositoryImpl
import com.hh.testapponlineshop.data.storage.AppDatabase
import com.hh.testapponlineshop.data.storage.mockystorage.MockyItemStorage
import com.hh.testapponlineshop.data.storage.roomstorage.item.ItemDao
import com.hh.testapponlineshop.data.storage.interfaces.ReadOnlyItemStorage
import com.hh.testapponlineshop.data.storage.interfaces.ReadWriteItemStorage
import com.hh.testapponlineshop.data.storage.roomstorage.item.RoomItemStorage
import com.hh.testapponlineshop.data.storage.roomstorage.user.RoomUserStorage
import com.hh.testapponlineshop.data.storage.roomstorage.user.UserDao
import com.hh.testapponlineshop.data.storage.interfaces.ReadWriteUserStorage
import com.hh.testapponlineshop.domain.repository.ItemRepository
import com.hh.testapponlineshop.domain.repository.UserRepository
import org.koin.dsl.module

val dataModule = module {
    single<AppDatabase> {
        AppDatabase.getInstance(context = get())
    }
    single<ItemDao> {
        get<AppDatabase>().itemDao
    }
    single<UserDao> {
        get<AppDatabase>().userDao
    }
    single<ReadOnlyItemStorage> {
        MockyItemStorage()
    }
    single<ReadWriteItemStorage> {
        RoomItemStorage(itemDao = get())
    }
    single<ReadWriteUserStorage> {
        RoomUserStorage(userDao = get())
    }
    single<ItemRepository> {
        ItemsRepositoryImpl(itemStorage = get(), writableItemStorage = get())
    }
    single<UserRepository> {
        UserRepositoryImpl(writableItemStorage = get())
    }
}