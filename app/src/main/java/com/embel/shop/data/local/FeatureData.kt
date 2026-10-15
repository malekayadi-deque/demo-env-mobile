package com.embel.shop.data.local

import com.embel.shop.data.model.Feature

object FeatureData {

    val servicePromotions = listOf(
        Feature(
            id = 1,
            icon = "shipping",
            title = "Fast And Free Delivery",
            content = "Free delivery for all orders over \$140"
        ),
        Feature(
            id = 2,
            icon = "headphone",
            title = "24/7 Customer Support",
            content = "Friendly 24/7 customer support"
        ),
        Feature(
            id = 3,
            icon = "shield",
            title = "Money Back Guarantee",
            content = "We return money within 30 days"
        ),
    )
}
