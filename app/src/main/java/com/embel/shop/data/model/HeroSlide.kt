package com.embel.shop.data.model

data class HeroSlide(
    val id: Int,
    val imageSrc: String,
    val imageAlt: String = "",
    val subtitle: String = "",
    val title: String = "",
    val description: String = "",
)
