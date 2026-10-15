package com.embel.shop.ui.components.filter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.embel.shop.data.local.FilterData
import com.embel.shop.viewmodel.ShopViewModel

@Composable
fun FilterPanel(
    shopViewModel: ShopViewModel,
    modifier: Modifier = Modifier,
) {
    val activeColor by shopViewModel.activeColor.collectAsState()
    val activeSizes by shopViewModel.activeSizes.collectAsState()
    val activeBrands by shopViewModel.activeBrands.collectAsState()
    val priceRange by shopViewModel.priceRange.collectAsState()
    val activeCategory by shopViewModel.activeCategory.collectAsState()
    val brandSearchQuery by shopViewModel.brandSearchQuery.collectAsState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "PRODUCT CATEGORIES",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.height(12.dp))
        CategoryFilter(
            categories = FilterData.categories,
            selectedCategory = activeCategory,
            onCategorySelected = { shopViewModel.setCategory(it) },
        )

        Spacer(modifier = Modifier.height(24.dp))
        Divider()
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "COLOR",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.height(12.dp))
        ColorFilter(
            colors = FilterData.colors,
            selectedColor = activeColor,
            onColorSelected = { shopViewModel.setColor(it) },
        )

        Spacer(modifier = Modifier.height(24.dp))
        Divider()
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "SIZE",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.height(12.dp))
        SizeFilter(
            sizes = FilterData.sizes,
            selectedSizes = activeSizes,
            onSizeToggled = { shopViewModel.toggleSize(it) },
        )

        Spacer(modifier = Modifier.height(24.dp))
        Divider()
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "BRANDS",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.height(12.dp))
        BrandFilter(
            brands = FilterData.brands,
            selectedBrands = activeBrands,
            searchQuery = brandSearchQuery,
            onSearchQueryChanged = { shopViewModel.setBrandSearchQuery(it) },
            onBrandToggled = { shopViewModel.toggleBrand(it) },
        )

        Spacer(modifier = Modifier.height(24.dp))
        Divider()
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "PRICE",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.height(12.dp))
        PriceRangeFilter(
            range = priceRange,
            onRangeChanged = { shopViewModel.setPriceRange(it) },
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}
