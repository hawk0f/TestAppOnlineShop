package com.hh.testapponlineshop.di

import com.hh.testapponlineshop.domain.usecases.AddNewUserUseCase
import com.hh.testapponlineshop.domain.usecases.CheckIfUserExistsUseCase
import com.hh.testapponlineshop.domain.usecases.DeleteUserUseCase
import com.hh.testapponlineshop.domain.usecases.LoadFavouriteItemsUseCase
import com.hh.testapponlineshop.domain.usecases.LoadItemsUseCase
import com.hh.testapponlineshop.domain.usecases.UpdateUserUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<LoadItemsUseCase> {
        LoadItemsUseCase(itemRepository = get())
    }
    factory<CheckIfUserExistsUseCase> {
        CheckIfUserExistsUseCase(userRepository = get())
    }
    factory<AddNewUserUseCase> {
        AddNewUserUseCase(userRepository = get())
    }
    factory<UpdateUserUseCase> {
        UpdateUserUseCase(userRepository = get())
    }
    factory<LoadFavouriteItemsUseCase> {
        LoadFavouriteItemsUseCase(itemRepository = get())
    }
    factory<DeleteUserUseCase> {
        DeleteUserUseCase(userRepository = get())
    }
}