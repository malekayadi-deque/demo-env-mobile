package com.embel.shop.data.local

import com.embel.shop.data.model.BrandOption
import com.embel.shop.data.model.CategoryOption
import com.embel.shop.data.model.ColorOption
import com.embel.shop.data.model.SizeOption
import com.embel.shop.data.model.SortingOption
import com.embel.shop.ui.theme.*

object FilterData {

    val colors = listOf(
        ColorOption("Navy", SwatchNavy),
        ColorOption("Gold", SwatchGold),
        ColorOption("Dark Gray", SwatchDarkGray),
        ColorOption("Light Blue", SwatchLightBlue),
        ColorOption("Brown", SwatchBrown),
        ColorOption("Tan", SwatchTan),
        ColorOption("Coral", SwatchCoral),
        ColorOption("Pink", SwatchPink),
        ColorOption("Gray", SwatchGray),
        ColorOption("Green", SwatchGreen),
    )

    val sizes = listOf(
        SizeOption("XS"),
        SizeOption("S"),
        SizeOption("M"),
        SizeOption("L"),
        SizeOption("XL"),
        SizeOption("XXL"),
    )

    val brands = listOf(
        BrandOption("Ikea", 15),
        BrandOption("Lazyboy", 18),
        BrandOption("Ember", 6),
        BrandOption("Mullberry", 9),
        BrandOption("Taggo", 1),
        BrandOption("Grounded", 2),
        BrandOption("Earthen", 4),
    )

    val categories = listOf(
        CategoryOption("Kitchen"),
        CategoryOption("Living room"),
        CategoryOption("Bedroom"),
        CategoryOption("Bathroom"),
        CategoryOption("Dining room"),
        CategoryOption("Storage"),
        CategoryOption("Outdoor"),
        CategoryOption("Accessories"),
    )

    val menuCategories = listOf(
        "All", "StayHome", "Jackets", "Hoodies", "Men", "Women", "Accessories", "Shoes"
    )

    val sortingOptions = listOf(
        SortingOption("Default Sorting", ""),
        SortingOption("Featured", "1"),
        SortingOption("Best selling", "2"),
        SortingOption("Alphabetically, A-Z", "3"),
        SortingOption("Alphabetically, Z-A", "4"),
        SortingOption("Price, low to high", "5"),
        SortingOption("Price, high to low", "6"),
        SortingOption("Date, old to new", "7"),
        SortingOption("Date, new to old", "8"),
    )

    const val PRICE_MIN = 0
    const val PRICE_MAX = 100000
    const val PRICE_DEFAULT_MIN = 20
    const val PRICE_DEFAULT_MAX = 70987
}
