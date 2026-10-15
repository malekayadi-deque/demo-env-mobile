package com.embel.shop.data.model

data class FooterLink(
    val text: String,
    val route: String,
)

data class SocialLink(
    val name: String,
    val url: String,
    val iconName: String,
)
