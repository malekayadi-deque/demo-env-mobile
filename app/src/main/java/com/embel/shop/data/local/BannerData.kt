package com.embel.shop.data.local

import com.embel.shop.data.model.Banner

object BannerData {

    val banners = listOf(
        Banner(id = 1, imgSrc = "images/home/demo9/banner-1.jpg", imgAlt = "Shop Women", title = "Shop Women", subtitle = "Starting at \$19", route = "shop"),
        Banner(id = 2, imgSrc = "images/home/demo9/banner-2.jpg", imgAlt = "Shop Men", title = "Shop Men", subtitle = "Starting at \$29", route = "shop"),
        Banner(id = 3, imgSrc = "images/home/demo9/banner-3.jpg", imgAlt = "Shop Kids", title = "Shop Kids", subtitle = "Starting at \$9", route = "shop"),
    )
}
