package com.embel.shop.data.local

data class CollectionItem(
    val id: Int,
    val imageSrc: String,
    val title: String,
    val productCount: String,
)

object CollectionData {

    val collections = listOf(
        CollectionItem(id = 1, imageSrc = "images/home/demo9/collection_grid_1.jpg", title = "Furniture", productCount = "954 Products"),
        CollectionItem(id = 2, imageSrc = "images/home/demo9/collection_grid_2.jpg", title = "Clocks", productCount = "710 Products"),
        CollectionItem(id = 3, imageSrc = "images/home/demo9/collection_grid_3.jpg", title = "Accessories", productCount = "954 Products"),
        CollectionItem(id = 4, imageSrc = "images/home/demo9/collection_grid_4.jpg", title = "Lighting", productCount = "184 Products"),
        CollectionItem(id = 5, imageSrc = "images/home/demo9/collection_grid_5.jpg", title = "Toys", productCount = "245 Products"),
    )

    val instagramImages = listOf(
        "images/home/demo9/instagram_1.jpg",
        "images/home/demo9/instagram_2.jpg",
        "images/home/demo9/instagram_3.jpg",
        "images/home/demo9/instagram_4.jpg",
        "images/home/demo9/instagram_5.jpg",
        "images/home/demo9/instagram_6.jpg",
    )
}
