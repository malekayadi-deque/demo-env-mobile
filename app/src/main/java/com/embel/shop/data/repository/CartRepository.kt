package com.embel.shop.data.repository

import com.embel.shop.data.datastore.CartDataStore
import com.embel.shop.data.model.CartItem
import com.embel.shop.data.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepository @Inject constructor(
    private val cartDataStore: CartDataStore
) {
    val cartItems: Flow<List<CartItem>> = cartDataStore.cartItems

    suspend fun addItem(product: Product, quantity: Int = 1) {
        val currentItems = cartDataStore.cartItems.first()
        if (currentItems.none { it.product.id == product.id }) {
            val updatedItems = currentItems + CartItem(product = product, quantity = maxOf(1, quantity))
            cartDataStore.saveCart(updatedItems)
        }
    }

    suspend fun removeItem(productId: Int) {
        val currentItems = cartDataStore.cartItems.first()
        val updatedItems = currentItems.filter { it.product.id != productId }
        cartDataStore.saveCart(updatedItems)
    }

    suspend fun updateQuantity(productId: Int, quantity: Int) {
        val currentItems = cartDataStore.cartItems.first()
        val updatedItems = currentItems.map { item ->
            if (item.product.id == productId) {
                item.copy(quantity = maxOf(1, quantity))
            } else {
                item
            }
        }
        cartDataStore.saveCart(updatedItems)
    }

    suspend fun clearCart() {
        cartDataStore.saveCart(emptyList())
    }

    suspend fun isInCart(productId: Int): Boolean {
        val currentItems = cartDataStore.cartItems.first()
        return currentItems.any { it.product.id == productId }
    }
}
