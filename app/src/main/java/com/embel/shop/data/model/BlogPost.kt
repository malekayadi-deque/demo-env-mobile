package com.embel.shop.data.model

data class BlogPost(
    val id: Int,
    val imgSrc: String,
    val category: String = "",
    val author: String = "",
    val date: String = "",
    val title: String,
    val content: String = "",
    val comments: Int = 0,
)
