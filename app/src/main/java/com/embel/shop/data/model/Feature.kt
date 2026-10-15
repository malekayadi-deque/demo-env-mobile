package com.embel.shop.data.model

data class Feature(
    val id: Int,
    val icon: String,
    val title: String,
    val content: String,
)

data class Banner(
    val id: Int,
    val imgSrc: String,
    val imgAlt: String = "",
    val title: String = "",
    val subtitle: String = "",
    val route: String = "",
)

data class Testimonial(
    val id: Int,
    val text: String,
    val author: String,
    val role: String = "",
)

data class BrandItem(
    val id: Int,
    val imgSrc: String,
    val width: Int = 0,
    val height: Int = 0,
)
