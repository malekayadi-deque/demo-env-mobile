package com.embel.shop.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CartItem(
    val product: Product,
    val quantity: Int = 1
) {
    val lineTotal: Double
        get() = product.price * quantity
}
