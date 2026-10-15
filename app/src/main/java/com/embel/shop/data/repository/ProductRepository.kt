package com.embel.shop.data.repository

import com.embel.shop.data.local.ProductData
import com.embel.shop.data.model.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor() {

    fun getAllProducts(): List<Product> = ProductData.allProducts

    fun getShopProducts(): List<Product> = ProductData.shopProducts

    fun getProductById(id: Int): Product? =
        ProductData.allProducts.find { it.id == id }

    fun getProductsByCategory(category: String): List<Product> =
        ProductData.allProducts.filter {
            it.category.equals(category, ignoreCase = true)
        }

    fun searchProducts(query: String): List<Product> =
        ProductData.allProducts.filter {
            it.title.contains(query, ignoreCase = true) ||
                it.category.contains(query, ignoreCase = true)
        }

    fun getRelatedProducts(product: Product, limit: Int = 4): List<Product> =
        ProductData.allProducts
            .filter { it.category == product.category && it.id != product.id }
            .take(limit)
}
