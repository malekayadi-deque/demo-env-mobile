package com.embel.shop.di

import android.content.Context
import com.embel.shop.data.datastore.CartDataStore
import com.embel.shop.data.datastore.PreferencesDataStore
import com.embel.shop.data.datastore.WishlistDataStore
import com.embel.shop.data.repository.CartRepository
import com.embel.shop.data.repository.ProductRepository
import com.embel.shop.data.repository.WishlistRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCartDataStore(@ApplicationContext context: Context): CartDataStore =
        CartDataStore(context)

    @Provides
    @Singleton
    fun provideWishlistDataStore(@ApplicationContext context: Context): WishlistDataStore =
        WishlistDataStore(context)

    @Provides
    @Singleton
    fun providePreferencesDataStore(@ApplicationContext context: Context): PreferencesDataStore =
        PreferencesDataStore(context)

    @Provides
    @Singleton
    fun provideProductRepository(): ProductRepository =
        ProductRepository()

    @Provides
    @Singleton
    fun provideCartRepository(cartDataStore: CartDataStore): CartRepository =
        CartRepository(cartDataStore)

    @Provides
    @Singleton
    fun provideWishlistRepository(wishlistDataStore: WishlistDataStore): WishlistRepository =
        WishlistRepository(wishlistDataStore)
}
