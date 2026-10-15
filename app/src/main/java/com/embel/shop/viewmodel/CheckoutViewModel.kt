package com.embel.shop.viewmodel

import androidx.lifecycle.ViewModel
import com.embel.shop.data.local.CountryData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor() : ViewModel() {

    // Form fields
    val firstName = MutableStateFlow("")
    val lastName = MutableStateFlow("")
    val companyName = MutableStateFlow("")
    val selectedCountry = MutableStateFlow("")
    val streetAddress = MutableStateFlow("")
    val streetAddress2 = MutableStateFlow("")
    val city = MutableStateFlow("")
    val zipCode = MutableStateFlow("")
    val province = MutableStateFlow("")
    val phone = MutableStateFlow("")
    val email = MutableStateFlow("")
    val orderNotes = MutableStateFlow("")

    // Checkboxes
    val createAccount = MutableStateFlow(false)
    val shipDifferentAddress = MutableStateFlow(false)

    // Payment method
    val paymentMethod = MutableStateFlow("bank_transfer")

    // Country dropdown
    private val _countrySearchQuery = MutableStateFlow("")
    val countrySearchQuery: StateFlow<String> = _countrySearchQuery.asStateFlow()

    private val _isCountryDropdownOpen = MutableStateFlow(false)
    val isCountryDropdownOpen: StateFlow<Boolean> = _isCountryDropdownOpen.asStateFlow()

    val filteredCountries: List<String>
        get() {
            val query = _countrySearchQuery.value
            return if (query.isBlank()) {
                CountryData.countries
            } else {
                CountryData.countries.filter {
                    it.contains(query, ignoreCase = true)
                }
            }
        }

    fun setCountrySearchQuery(query: String) {
        _countrySearchQuery.value = query
    }

    fun toggleCountryDropdown() {
        _isCountryDropdownOpen.value = !_isCountryDropdownOpen.value
    }

    fun selectCountry(country: String) {
        selectedCountry.value = country
        _isCountryDropdownOpen.value = false
        _countrySearchQuery.value = ""
    }

    fun setPaymentMethod(method: String) {
        paymentMethod.value = method
    }
}
