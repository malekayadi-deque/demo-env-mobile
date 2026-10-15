package com.embel.shop.data.model

data class StoreLocation(
    val id: Int,
    val city: String,
    val address: String,
    val country: String,
    val phone: String,
    val hours: String = "",
    val email: String = "",
    val lat: Double,
    val lng: Double,
)
