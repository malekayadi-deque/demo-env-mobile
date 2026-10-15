package com.embel.shop.data.repository

import com.embel.shop.data.datastore.WishlistDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WishlistRepository @Inject constructor(
    private val wishlistDataStore: WishlistDataStore
) {
    val wishlistIds: Flow<List<Int>> = wishlistDataStore.wishlistIds

    suspend fun toggleWishlist(productId: Int) {
        val currentIds = wishlistDataStore.wishlistIds.first()
        val updatedIds = if (currentIds.contains(productId)) {
            currentIds.filter { it != productId }
        } else {
            currentIds + productId
        }
        wishlistDataStore.saveWishlist(updatedIds)
    }

    suspend fun isInWishlist(productId: Int): Boolean {
        val currentIds = wishlistDataStore.wishlistIds.first()
        return currentIds.contains(productId)
    }
}
