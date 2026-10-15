package com.embel.shop.data.model

import androidx.compose.ui.graphics.Color

data class ColorOption(
    val name: String,
    val color: Color,
)

data class SizeOption(
    val label: String,
)

data class BrandOption(
    val name: String,
    val count: Int,
)

data class CategoryOption(
    val name: String,
    val count: Int = 0,
)

data class SortingOption(
    val label: String,
    val value: String,
)

data class ActiveFilter(
    val label: String,
    val type: FilterType,
)

enum class FilterType {
    COLOR,
    SIZE,
    BRAND,
    CATEGORY,
    PRICE,
}
