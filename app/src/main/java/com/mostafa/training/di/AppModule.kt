package com.mostafa.training.di


import com.mostafa.training.data.repository.CartRepositoryImpl
import com.mostafa.training.data.repository.FavoritesRepositoryImpl
import com.mostafa.training.data.repository.HomeRepositoryImpl
import com.mostafa.training.data.repository.ProfileRepositoryImpl
import com.mostafa.training.data.repository.SearchRepositoryImpl
import com.mostafa.training.domain.repository.CartRepository
import com.mostafa.training.domain.repository.FavoritesRepository
import com.mostafa.training.domain.repository.HomeRepository
import com.mostafa.training.domain.repository.ProfileRepository
import com.mostafa.training.domain.repository.SearchRepository
import com.mostafa.training.domain.usecase.AddOrRemoveCartUseCase
import com.mostafa.training.domain.usecase.AddOrRemoveFavoritesUseCase
import com.mostafa.training.domain.usecase.GetBannerUseCase
import com.mostafa.training.domain.usecase.GetCartItemsUseCase
import com.mostafa.training.domain.usecase.GetCategoriesDetailUseCase
import com.mostafa.training.domain.usecase.GetCategoryUseCase
import com.mostafa.training.domain.usecase.GetContactUseCase
import com.mostafa.training.domain.usecase.GetFaqsUseCase
import com.mostafa.training.domain.usecase.GetFavoritesUseCase
import com.mostafa.training.domain.usecase.GetNotificationsUseCase
import com.mostafa.training.domain.usecase.GetProductByIdUseCase
import com.mostafa.training.domain.usecase.GetProductsUseCase
import com.mostafa.training.domain.usecase.GetProfileUseCase
import com.mostafa.training.domain.usecase.GetSearchUseCase
import com.mostafa.training.domain.usecase.UpdateCartUseCase
import com.mostafa.training.ui.screens.cart.CartViewModel
import com.mostafa.training.ui.screens.category.CategoryViewModel
import com.mostafa.training.ui.screens.favorites.FavoritesViewModel
import com.mostafa.training.ui.screens.home.HomeViewModel
import com.mostafa.training.ui.screens.notification.NotificationViewModel
import com.mostafa.training.ui.screens.product_detail.ProductDetailViewModel
import com.mostafa.training.ui.screens.productsByCategory.ProductsByCategoryViewModel
import com.mostafa.training.ui.screens.profile.ProfileViewModel
import com.mostafa.training.ui.screens.search.SearchViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module


val appModule = module {
    single<HomeRepository> { HomeRepositoryImpl(get()) }
    single<CartRepository> { CartRepositoryImpl(get()) }
    single<SearchRepository> { SearchRepositoryImpl(get()) }
    single<ProfileRepository> { ProfileRepositoryImpl(get()) }
    single<FavoritesRepository> { FavoritesRepositoryImpl(get()) }
    single { GetCategoriesDetailUseCase(get()) }
    single { AddOrRemoveCartUseCase(get()) }
    single { UpdateCartUseCase(get()) }
    single { GetCartItemsUseCase(get()) }
    single { GetNotificationsUseCase(get()) }
    single { GetCategoryUseCase(get()) }
    single { GetBannerUseCase(get()) }
    single { GetProductsUseCase(get()) }
    single { GetProductByIdUseCase(get()) }
    single { GetSearchUseCase(get()) }
    single { GetProfileUseCase(get()) }
    single { GetFavoritesUseCase(get()) }
    single { AddOrRemoveFavoritesUseCase(get()) }
    single { GetContactUseCase(get()) }
    single { GetFaqsUseCase(get()) }
    viewModel {
        HomeViewModel(get(), get(), get(),get())
    }
    viewModel {
        NotificationViewModel(get())
    }
    viewModel {
        SearchViewModel(get())
    }
    viewModel {
        ProductsByCategoryViewModel(get(), get())
    }
    viewModel {
        ProductDetailViewModel(get(), get())
    }
    viewModel {
        CategoryViewModel(get())
    }
    viewModel {
        CartViewModel(get(),get(),get())
    }
    viewModel {
        ProfileViewModel(get(),get(),get())
    }
    viewModel {
        FavoritesViewModel(get(),get())
    }

}




