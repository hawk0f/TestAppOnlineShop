package com.hh.testapponlineshop.di

import com.hh.testapponlineshop.data.repository.ItemRepositoryImpl
import com.hh.testapponlineshop.data.repository.UserRepositoryImpl
import com.hh.testapponlineshop.data.storage.AppDatabase
import com.hh.testapponlineshop.data.storage.mockystorage.MockyItemStorage
import com.hh.testapponlineshop.data.storage.roomstorage.item.ItemDao
import com.hh.testapponlineshop.data.storage.interfaces.IReadableItemStorage
import com.hh.testapponlineshop.data.storage.interfaces.IReadableWritableItemStorage
import com.hh.testapponlineshop.data.storage.roomstorage.item.RoomItemStorage
import com.hh.testapponlineshop.data.storage.roomstorage.user.RoomUserStorage
import com.hh.testapponlineshop.data.storage.roomstorage.user.UserDao
import com.hh.testapponlineshop.data.storage.interfaces.IReadableWritableUserStorage
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
    single<IReadableItemStorage> {
        MockyItemStorage()
    }
    single<IReadableWritableItemStorage> {
        RoomItemStorage(itemDao = get())
    }
    single<IReadableWritableUserStorage> {
        RoomUserStorage(userDao = get())
    }
    single<ItemRepository> {
        ItemRepositoryImpl(itemStorage = get(), writableItemStorage = get())
    }
    single<UserRepository> {
        UserRepositoryImpl(writableItemStorage = get())
    }
}