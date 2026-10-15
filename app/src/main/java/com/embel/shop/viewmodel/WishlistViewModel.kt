package com.embel.shop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.embel.shop.data.repository.WishlistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val wishlistRepository: WishlistRepository,
) : ViewModel() {

    val wishlistIds: StateFlow<List<Int>> = wishlistRepository.wishlistIds
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun toggleWishlist(productId: Int) {
        viewModelScope.launch {
            wishlistRepository.toggleWishlist(productId)
        }
    }

    fun isInWishlist(productId: Int): Boolean {
        return wishlistIds.value.contains(productId)
    }
}
