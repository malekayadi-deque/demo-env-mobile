package com.embel.shop.data.local

import com.embel.shop.data.model.HeroSlide

object HeroSlideData {

    val slides = listOf(
        HeroSlide(
            id = 1,
            imageSrc = "images/home/demo9/slider1.jpg",
            imageAlt = "Collection slide 1",
            subtitle = "New Collection",
            title = "Furniture Collection",
            description = "Beautifully crafted furniture for your home"
        ),
        HeroSlide(
            id = 2,
            imageSrc = "images/home/demo9/slider1.jpg",
            imageAlt = "Collection slide 2",
            subtitle = "Living Room",
            title = "Modern Living",
            description = "Discover our latest living room collection"
        ),
        HeroSlide(
            id = 3,
            imageSrc = "images/home/demo9/slider1.jpg",
            imageAlt = "Collection slide 3",
            subtitle = "Bedroom",
            title = "Cozy Bedroom",
            description = "Create your perfect bedroom sanctuary"
        ),
    )
}
