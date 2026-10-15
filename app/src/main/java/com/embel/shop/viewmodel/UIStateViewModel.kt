package com.embel.shop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.embel.shop.data.datastore.PreferencesDataStore
import com.embel.shop.data.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UIStateViewModel @Inject constructor(
    private val preferencesDataStore: PreferencesDataStore,
) : ViewModel() {

    // Cart drawer
    private val _isCartDrawerOpen = MutableStateFlow(false)
    val isCartDrawerOpen: StateFlow<Boolean> = _isCartDrawerOpen.asStateFlow()

    // Search popup
    private val _isSearchOpen = MutableStateFlow(false)
    val isSearchOpen: StateFlow<Boolean> = _isSearchOpen.asStateFlow()

    // Filter panel
    private val _isFilterOpen = MutableStateFlow(false)
    val isFilterOpen: StateFlow<Boolean> = _isFilterOpen.asStateFlow()

    // Quick view
    private val _quickViewProduct = MutableStateFlow<Product?>(null)
    val quickViewProduct: StateFlow<Product?> = _quickViewProduct.asStateFlow()

    // Cookie consent
    val isCookieConsentVisible: StateFlow<Boolean> = preferencesDataStore.isCookieAccepted
        .map { !it }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    // Cart drawer
    fun openCartDrawer() {
        _isCartDrawerOpen.value = true
    }

    fun closeCartDrawer() {
        _isCartDrawerOpen.value = false
    }

    // Search
    fun openSearch() {
        _isSearchOpen.value = true
    }

    fun closeSearch() {
        _isSearchOpen.value = false
    }

    // Filter
    fun openFilter() {
        _isFilterOpen.value = true
    }

    fun closeFilter() {
        _isFilterOpen.value = false
    }

    // Quick view
    fun showQuickView(product: Product) {
        _quickViewProduct.value = product
    }

    fun hideQuickView() {
        _quickViewProduct.value = null
    }

    // Cookie consent
    fun dismissCookieConsent() {
        viewModelScope.launch {
            preferencesDataStore.setCookieAccepted(true)
        }
    }
}
