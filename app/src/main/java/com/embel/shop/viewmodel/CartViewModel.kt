package com.embel.shop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.embel.shop.data.model.CartItem
import com.embel.shop.data.model.Product
import com.embel.shop.data.repository.CartRepository
import com.embel.shop.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val productRepository: ProductRepository,
) : ViewModel() {

    val cartItems: StateFlow<List<CartItem>> = cartRepository.cartItems
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val totalPrice: StateFlow<Double> = cartRepository.cartItems
        .map { items -> items.sumOf { it.product.price * it.quantity } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

    val cartCount: StateFlow<Int> = cartRepository.cartItems
        .map { it.size }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    fun addToCart(productId: Int) {
        viewModelScope.launch {
            val product = productRepository.getProductById(productId) ?: return@launch
            cartRepository.addItem(product)
        }
    }

    fun addProductToCart(product: Product) {
        viewModelScope.launch {
            cartRepository.addItem(product)
        }
    }

    fun addToCart(product: Product, quantity: Int) {
        viewModelScope.launch {
            cartRepository.addItem(product, quantity)
        }
    }

    fun removeItem(productId: Int) {
        viewModelScope.launch {
            cartRepository.removeItem(productId)
        }
    }

    fun updateQuantity(productId: Int, quantity: Int) {
        viewModelScope.launch {
            cartRepository.updateQuantity(productId, quantity)
        }
    }

    fun isInCart(productId: Int): Boolean {
        return cartItems.value.any { it.product.id == productId }
    }

    fun clearCart() {
        viewModelScope.launch {
            cartRepository.clearCart()
        }
    }
}
