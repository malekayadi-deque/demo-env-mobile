package com.embel.shop.data.model

data class MenuItem(
    val id: Int,
    val label: String,
    val route: String,
    val children: List<MenuItem> = emptyList(),
)
