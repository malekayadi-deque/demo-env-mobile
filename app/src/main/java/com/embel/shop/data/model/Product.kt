package com.embel.shop.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val imgSrc: String,
    val imgSrc2: String? = null,
    val imgAlt: String? = null,
    val category: String = "",
    val title: String,
    val price: Double,
    val priceOld: Double? = null,
    val discount: Int? = null,
    val rating: Double? = null,
    val reviews: String? = null,
    val filterCategory: String? = null,
    val isNew: Boolean = false,
    val colors: Boolean = false,
    val onSale: Boolean = false,
    val isExternal: Boolean = false,
    val isGrouped: Boolean = false,
    val isOutOfStock: Boolean = false,
)
