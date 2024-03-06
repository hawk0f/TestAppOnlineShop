package com.hh.testapponlineshop.di

import com.hh.testapponlineshop.Session
import com.hh.testapponlineshop.viewModels.CatalogItemInfoViewModel
import com.hh.testapponlineshop.viewModels.CatalogViewModel
import com.hh.testapponlineshop.viewModels.FavouriteListViewModel
import com.hh.testapponlineshop.viewModels.ProfileViewModel
import com.hh.testapponlineshop.viewModels.RegistrationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Session>(createdAtStart = true) {
        Session()
    }
    viewModel<RegistrationViewModel> {
        RegistrationViewModel(session = get(), checkIfUserExistsUseCase = get(), addNewUserUseCase = get())
    }
    viewModel<CatalogViewModel> {
        CatalogViewModel(loadItemsUseCase = get(), session = get(), updateUserFavouriteList = get())
    }
    viewModel<FavouriteListViewModel> {
        FavouriteListViewModel(session = get(), loadFavouriteItemsUseCase = get(), updateUserUseCase = get())
    }
    viewModel<ProfileViewModel> {
        ProfileViewModel(session = get(), loadFavouriteItemsUseCase = get(), deleteUserUseCase = get())
    }
    viewModel<CatalogItemInfoViewModel> {
        CatalogItemInfoViewModel(session = get(), updateUserUseCase = get())
    }
}