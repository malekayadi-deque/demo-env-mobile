package com.embel.shop.viewmodel

import androidx.lifecycle.ViewModel
import com.embel.shop.data.model.Product
import com.embel.shop.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productRepository: ProductRepository,
) : ViewModel() {

    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> = _product.asStateFlow()

    private val _quantity = MutableStateFlow(1)
    val quantity: StateFlow<Int> = _quantity.asStateFlow()

    private val _selectedSize = MutableStateFlow<String?>(null)
    val selectedSize: StateFlow<String?> = _selectedSize.asStateFlow()

    private val _selectedColor = MutableStateFlow<String?>(null)
    val selectedColor: StateFlow<String?> = _selectedColor.asStateFlow()

    private val _selectedTab = MutableStateFlow(0)
    val selectedTab: StateFlow<Int> = _selectedTab.asStateFlow()

    private val _relatedProducts = MutableStateFlow<List<Product>>(emptyList())
    val relatedProducts: StateFlow<List<Product>> = _relatedProducts.asStateFlow()

    fun loadProduct(productId: Int) {
        val found = productRepository.getProductById(productId)
        _product.value = found
        _quantity.value = 1
        found?.let {
            _relatedProducts.value = productRepository.getRelatedProducts(it)
        }
    }

    fun incrementQuantity() {
        _quantity.value += 1
    }

    fun decrementQuantity() {
        if (_quantity.value > 1) {
            _quantity.value -= 1
        }
    }

    fun setQuantity(qty: Int) {
        _quantity.value = maxOf(1, qty)
    }

    fun selectSize(size: String) {
        _selectedSize.value = size
    }

    fun selectColor(color: String) {
        _selectedColor.value = color
    }

    fun selectTab(index: Int) {
        _selectedTab.value = index
    }
}
