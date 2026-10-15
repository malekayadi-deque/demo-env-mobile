package com.embel.shop.viewmodel

import androidx.lifecycle.ViewModel
import com.embel.shop.data.local.FilterData
import com.embel.shop.data.model.BrandOption
import com.embel.shop.data.model.ColorOption
import com.embel.shop.data.model.Product
import com.embel.shop.data.model.SortingOption
import com.embel.shop.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val productRepository: ProductRepository,
) : ViewModel() {

    // Filter state
    private val _activeColor = MutableStateFlow<ColorOption?>(null)
    val activeColor: StateFlow<ColorOption?> = _activeColor.asStateFlow()

    private val _activeSizes = MutableStateFlow<List<String>>(emptyList())
    val activeSizes: StateFlow<List<String>> = _activeSizes.asStateFlow()

    private val _activeBrands = MutableStateFlow<List<BrandOption>>(emptyList())
    val activeBrands: StateFlow<List<BrandOption>> = _activeBrands.asStateFlow()

    private val _priceRange = MutableStateFlow(FilterData.PRICE_DEFAULT_MIN..FilterData.PRICE_DEFAULT_MAX)
    val priceRange: StateFlow<IntRange> = _priceRange.asStateFlow()

    private val _activeCategory = MutableStateFlow<String?>(null)
    val activeCategory: StateFlow<String?> = _activeCategory.asStateFlow()

    private val _sortOption = MutableStateFlow(FilterData.sortingOptions.first())
    val sortOption: StateFlow<SortingOption> = _sortOption.asStateFlow()

    private val _gridColumns = MutableStateFlow(2)
    val gridColumns: StateFlow<Int> = _gridColumns.asStateFlow()

    private val _currentPage = MutableStateFlow(1)
    val currentPage: StateFlow<Int> = _currentPage.asStateFlow()

    private val _brandSearchQuery = MutableStateFlow("")
    val brandSearchQuery: StateFlow<String> = _brandSearchQuery.asStateFlow()

    val itemsPerPage = 9

    // All shop products
    private val _allProducts = MutableStateFlow(productRepository.getShopProducts())

    // Filtered products (applying all filters)
    private val _filteredProducts = MutableStateFlow(productRepository.getShopProducts())
    val filteredProducts: StateFlow<List<Product>> = _filteredProducts.asStateFlow()

    // Paginated products
    private val _pagedProducts = MutableStateFlow(productRepository.getShopProducts())
    val pagedProducts: StateFlow<List<Product>> = _pagedProducts.asStateFlow()

    val totalPages: Int
        get() {
            val total = _filteredProducts.value.size
            return if (total == 0) 1 else (total + itemsPerPage - 1) / itemsPerPage
        }

    init {
        applyFilters()
    }

    fun setColor(color: ColorOption?) {
        _activeColor.value = if (_activeColor.value == color) null else color
        applyFilters()
    }

    fun toggleSize(size: String) {
        val current = _activeSizes.value.toMutableList()
        if (current.contains(size)) current.remove(size) else current.add(size)
        _activeSizes.value = current
        applyFilters()
    }

    fun toggleBrand(brand: BrandOption) {
        val current = _activeBrands.value.toMutableList()
        if (current.contains(brand)) current.remove(brand) else current.add(brand)
        _activeBrands.value = current
        applyFilters()
    }

    fun setPriceRange(range: IntRange) {
        _priceRange.value = range
        applyFilters()
    }

    fun setCategory(category: String?) {
        _activeCategory.value = if (_activeCategory.value == category) null else category
        applyFilters()
    }

    fun setSortOption(option: SortingOption) {
        _sortOption.value = option
        applyFilters()
    }

    fun setGridColumns(columns: Int) {
        _gridColumns.value = columns
    }

    fun setPage(page: Int) {
        _currentPage.value = page
        updatePagedProducts()
    }

    fun setBrandSearchQuery(query: String) {
        _brandSearchQuery.value = query
    }

    fun clearAllFilters() {
        _activeColor.value = null
        _activeSizes.value = emptyList()
        _activeBrands.value = emptyList()
        _priceRange.value = FilterData.PRICE_DEFAULT_MIN..FilterData.PRICE_DEFAULT_MAX
        _activeCategory.value = null
        _sortOption.value = FilterData.sortingOptions.first()
        _currentPage.value = 1
        applyFilters()
    }

    private fun applyFilters() {
        var products = _allProducts.value

        // Filter by category
        _activeCategory.value?.let { category ->
            products = products.filter { it.category.equals(category, ignoreCase = true) }
        }

        // Filter by price range
        val range = _priceRange.value
        products = products.filter { it.price >= range.first && it.price <= range.last }

        // Sort
        products = when (_sortOption.value.value) {
            "3" -> products.sortedBy { it.title }        // A-Z
            "4" -> products.sortedByDescending { it.title } // Z-A
            "5" -> products.sortedBy { it.price }         // Price low to high
            "6" -> products.sortedByDescending { it.price } // Price high to low
            else -> products
        }

        _filteredProducts.value = products
        _currentPage.value = 1
        updatePagedProducts()
    }

    private fun updatePagedProducts() {
        val page = _currentPage.value
        val startIndex = (page - 1) * itemsPerPage
        val endIndex = minOf(startIndex + itemsPerPage, _filteredProducts.value.size)
        _pagedProducts.value = if (startIndex < _filteredProducts.value.size) {
            _filteredProducts.value.subList(startIndex, endIndex)
        } else {
            emptyList()
        }
    }
}
