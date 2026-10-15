package com.embel.shop.data.local

import com.embel.shop.data.model.Product

object ProductData {

    // products52 - Main shop page products (Kitchen/Home)
    private val products52 = listOf(
        Product(id = 249, category = "Kitchen", title = "End Grain Cutting Board", price = 120.0, imgSrc = "images/products/home/cutting-board.jpg", imgSrc2 = "images/products/home/cutting-board.jpg", reviews = "8k+ reviews", rating = 5.0),
        Product(id = 250, category = "Storage", title = "Storage Shelve Cabinet", price = 299.0, imgSrc = "images/products/home/storage-shelves.jpg", imgSrc2 = "images/products/home/storage-shelves.jpg", colors = true, discount = 67),
        Product(id = 251, category = "Storage", title = "Bookcase", price = 99.0, imgSrc = "images/products/home/bookcase.jpg", imgSrc2 = "images/products/home/bookcase.jpg"),
        Product(id = 252, category = "Kitchen", title = "Oak Burl Mixing Bowl", price = 99.0, priceOld = 129.0, imgSrc = "images/products/home/oak-burl-bowl.jpg", imgSrc2 = "images/products/home/oak-burl-bowl.jpg", isNew = true),
        Product(id = 253, category = "Bedroom", title = "Walnut Bed with Storage", price = 499.0, imgSrc = "images/products/home/walnut-bedframe.jpg", imgSrc2 = "images/products/home/walnut-bedframe.jpg"),
        Product(id = 254, category = "Dining Room", title = "Kitchen Table", price = 1920.0, imgSrc = "images/products/home/kitchen-table.jpg", imgSrc2 = "images/products/home/kitchen-table.jpg"),
        Product(id = 255, category = "Kitchen", title = "Stand Mixer", price = 174.0, imgSrc = "images/products/home/stand-mixer.jpg", imgSrc2 = "images/products/home/stand-mixer.jpg", reviews = "8k+ reviews", rating = 5.0),
        Product(id = 256, category = "Kitchen", title = "Modern Dishware", price = 220.0, imgSrc = "images/products/home/dishware.jpg", imgSrc2 = "images/products/home/dishware.jpg", reviews = "8k+ reviews", rating = 5.0),
    )

    // products51 - Fashion products with filters
    private val products51 = listOf(
        Product(id = 233, category = "Dresses", title = "Cropped Faux Leather Jacket", price = 29.0, imgSrc = "images/products/product_1.jpg", imgSrc2 = "images/products/product_1-1.jpg", reviews = "8k+ reviews", rating = 5.0, filterCategory = "New Arrivals"),
        Product(id = 234, category = "Dresses", title = "Calvin Shorts", price = 62.0, imgSrc = "images/products/product_2.jpg", imgSrc2 = "images/products/product_2-1.jpg", colors = true, discount = 67, filterCategory = "Best Seller"),
        Product(id = 235, category = "Dresses", title = "Kirby T-Shirt", price = 17.0, imgSrc = "images/products/product_3.jpg", imgSrc2 = "images/products/product_3-1.jpg", filterCategory = "New Arrivals"),
        Product(id = 236, category = "Dresses", title = "Cableknit Shawl", price = 99.0, priceOld = 129.0, imgSrc = "images/products/product_4.jpg", imgSrc2 = "images/products/product_4-1.jpg", isNew = true, filterCategory = "Best Seller"),
        Product(id = 237, category = "Dresses", title = "Colorful Jacket", price = 29.0, imgSrc = "images/products/product_5.jpg", imgSrc2 = "images/products/product_5-1.jpg", filterCategory = "New Arrivals"),
        Product(id = 238, category = "Dresses", title = "Shirt In Botanical Cheetah Print", price = 62.0, imgSrc = "images/products/product_6.jpg", imgSrc2 = "images/products/product_6-1.jpg", filterCategory = "New Arrivals"),
        Product(id = 239, category = "Dresses", title = "Cotton Jersey T-Shirt", price = 17.0, imgSrc = "images/products/product_7.jpg", imgSrc2 = "images/products/product_7-1.jpg", filterCategory = "Best Seller"),
        Product(id = 240, category = "Dresses", title = "Zessi Dresses", price = 99.0, priceOld = 129.0, imgSrc = "images/products/product_8.jpg", imgSrc2 = "images/products/product_8-1.jpg", filterCategory = "New Arrivals"),
        Product(id = 241, category = "Dresses", title = "Cropped Faux Leather Jacket", price = 29.0, imgSrc = "images/products/product_9.jpeg", imgSrc2 = "images/products/product_9-1.jpeg", reviews = "8k+ reviews", rating = 5.0, filterCategory = "Top Rated"),
        Product(id = 242, category = "Dresses", title = "Calvin Shorts", price = 62.0, imgSrc = "images/products/product_10.jpeg", imgSrc2 = "images/products/product_10-1.jpeg", filterCategory = "Top Rated"),
        Product(id = 243, category = "Dresses", title = "Kirby T-Shirt", price = 17.0, imgSrc = "images/products/product_11.jpeg", imgSrc2 = "images/products/product_11-1.jpeg", filterCategory = "New Arrivals"),
        Product(id = 244, category = "Dresses", title = "Cableknit Shawl", price = 99.0, priceOld = 129.0, imgSrc = "images/products/product_12.jpeg", imgSrc2 = "images/products/product_12-1.jpeg", filterCategory = "Top Rated"),
        Product(id = 245, category = "Dresses", title = "Colorful Jacket", price = 29.0, imgSrc = "images/products/product_13.jpeg", imgSrc2 = "images/products/product_13-1.jpeg", filterCategory = "Top Rated"),
        Product(id = 246, category = "Dresses", title = "Shirt In Botanical Cheetah Print", price = 62.0, imgSrc = "images/products/product_14.jpeg", imgSrc2 = "images/products/product_14-1.jpeg", filterCategory = "New Arrivals"),
        Product(id = 247, category = "Dresses", title = "Cotton Jersey T-Shirt", price = 17.0, imgSrc = "images/products/product_15.jpeg", imgSrc2 = "images/products/product_15-1.jpeg", filterCategory = "New Arrivals"),
        Product(id = 248, category = "Dresses", title = "Zessi Dresses", price = 99.0, priceOld = 129.0, imgSrc = "images/products/product_16.jpeg", imgSrc2 = "images/products/product_16-1.jpeg", filterCategory = "New Arrivals"),
    )

    // products1 - Basic fashion
    private val products1 = listOf(
        Product(id = 1, category = "Dresses", title = "Cropped Faux Leather Jacket", price = 29.0, imgSrc = "images/products/product_1.jpg", imgSrc2 = "images/products/product_1-1.jpg", reviews = "8k+ reviews", rating = 5.0),
        Product(id = 2, category = "Dresses", title = "Calvin Shorts", price = 62.0, imgSrc = "images/products/product_2.jpg", imgSrc2 = "images/products/product_2-1.jpg", colors = true, discount = 67),
        Product(id = 3, category = "Dresses", title = "Kirby T-Shirt", price = 17.0, imgSrc = "images/products/product_3.jpg", imgSrc2 = "images/products/product_3-1.jpg"),
        Product(id = 4, category = "Dresses", title = "Cableknit Shawl", price = 99.0, priceOld = 129.0, imgSrc = "images/products/product_4.jpg", imgSrc2 = "images/products/product_4-1.jpg", isNew = true),
        Product(id = 5, category = "Dresses", title = "Colorful Jacket", price = 29.0, imgSrc = "images/products/product_5.jpg", imgSrc2 = "images/products/product_5-1.jpg"),
        Product(id = 6, category = "Dresses", title = "Shirt In Botanical Cheetah Print", price = 62.0, imgSrc = "images/products/product_6.jpg", imgSrc2 = "images/products/product_6-1.jpg"),
        Product(id = 7, category = "Dresses", title = "Cotton Jersey T-Shirt", price = 17.0, imgSrc = "images/products/product_7.jpg", imgSrc2 = "images/products/product_7-1.jpg"),
        Product(id = 8, category = "Dresses", title = "Zessi Dresses", price = 99.0, priceOld = 129.0, imgSrc = "images/products/product_8.jpg", imgSrc2 = "images/products/product_8-1.jpg"),
    )

    // products58 - Electronics
    private val products58 = listOf(
        Product(id = 582, category = "Television & Video", title = "Toshiba TF-43A810U21 43-inch 4K UHD TV", price = 29.0, imgSrc = "images/products/product-37-1.jpg", rating = 5.0, reviews = "321975"),
        Product(id = 583, category = "Camera & Photo", title = "Wyze Cam 1080p HD Indoor Smart Camera", price = 62.0, imgSrc = "images/products/product-35-1.jpg", rating = 5.0, reviews = "321975"),
        Product(id = 584, category = "Audio", title = "NUBWO Gaming Headsets PS4 N7 Stereo", price = 17.0, imgSrc = "images/products/product-36-1.jpg", rating = 5.0, reviews = "321975"),
        Product(id = 585, category = "Bluetooth Speakers", title = "Echo Dot (3rd Gen) Smart Speaker", price = 99.0, imgSrc = "images/products/product-37-1.jpg", rating = 5.0, reviews = "321975"),
    )

    // products59 - Grocery/Fruits
    private val products59 = listOf(
        Product(id = 586, category = "Fruits", title = "Organic Bartlett Pear", price = 35.9, imgSrc = "images/products/product-38-1.jpg", rating = 5.0, reviews = "321,975"),
        Product(id = 587, category = "Fruits", title = "Organic Strawberries, 1 lb", price = 79.99, imgSrc = "images/products/product-39-1.jpg", rating = 5.0, reviews = "321,975"),
        Product(id = 588, category = "Fruits", title = "Blueberry Organic, 6 Ounce", price = 929.0, imgSrc = "images/products/product-40-1.jpg", rating = 5.0, reviews = "321,975"),
        Product(id = 589, category = "Fruits", title = "Kiwi Organic, 1 Each", price = 729.0, imgSrc = "images/products/product-41-1.jpg", rating = 5.0, reviews = "321,975"),
        Product(id = 590, category = "Fruits", title = "Organic Banana", price = 399.0, imgSrc = "images/products/product-42-1.jpg", rating = 5.0, reviews = "321,975"),
    )

    // products54 - Fashion with second images
    private val products54 = listOf(
        Product(id = 566, category = "Dresses", title = "Colorful Jacket", price = 29.0, imgSrc = "images/products/product-26-1.jpeg", imgSrc2 = "images/products/product-26-2.jpg"),
        Product(id = 567, category = "Dresses", title = "Shirt In Botanical Cheetah Print", price = 62.0, imgSrc = "images/products/product-27-1.jpeg", imgSrc2 = "images/products/product-27-2.jpeg"),
        Product(id = 568, category = "Dresses", title = "Cotton Jersey T-Shirt", price = 17.0, imgSrc = "images/products/product-28-1.jpeg", imgSrc2 = "images/products/product-28-2.jpg"),
        Product(id = 569, category = "Dresses", title = "Zessi Dresses", price = 99.0, imgSrc = "images/products/product-29-1.jpeg", imgSrc2 = "images/products/product-29-2.jpg"),
    )

    // products55 - Fashion with filter categories
    private val products55 = listOf(
        Product(id = 570, category = "Dresses", title = "Cropped Faux Leather Jacket", price = 29.0, imgSrc = "images/products/product_9.jpeg", imgSrc2 = "images/products/product_9-1.jpeg", reviews = "8k+ reviews", rating = 5.0, filterCategory = "Top Rated"),
        Product(id = 571, category = "Dresses", title = "Calvin Shorts", price = 62.0, imgSrc = "images/products/product_10.jpeg", imgSrc2 = "images/products/product_10-1.jpeg", filterCategory = "Top Rated"),
        Product(id = 572, category = "Dresses", title = "Kirby T-Shirt", price = 17.0, imgSrc = "images/products/product_11.jpeg", imgSrc2 = "images/products/product_11-1.jpeg", filterCategory = "New Arrivals"),
        Product(id = 573, category = "Dresses", title = "Cableknit Shawl", price = 99.0, priceOld = 129.0, imgSrc = "images/products/product_12.jpeg", imgSrc2 = "images/products/product_12-1.jpeg", filterCategory = "Top Rated"),
    )

    // products56
    private val products56 = listOf(
        Product(id = 574, category = "Dresses", title = "Colorful Jacket", price = 29.0, imgSrc = "images/products/product_13.jpeg", imgSrc2 = "images/products/product-22-1.jpg", filterCategory = "Top Rated"),
        Product(id = 575, category = "Dresses", title = "Shirt In Botanical Cheetah Print", price = 62.0, imgSrc = "images/products/product_14.jpeg", imgSrc2 = "images/products/product_14-1.jpeg", filterCategory = "New Arrivals"),
        Product(id = 576, category = "Dresses", title = "Cotton Jersey T-Shirt", price = 17.0, imgSrc = "images/products/product_15.jpeg", imgSrc2 = "images/products/product_15-1.jpeg", filterCategory = "New Arrivals"),
        Product(id = 577, category = "Dresses", title = "Zessi Dresses", price = 99.0, priceOld = 129.0, imgSrc = "images/products/product_16.jpeg", imgSrc2 = "images/products/product_16-1.jpeg", filterCategory = "New Arrivals"),
    )

    // products57 - Sport brands
    private val products57 = listOf(
        Product(id = 578, category = "NIKE", title = "Wildhorse 6", price = 29.0, imgSrc = "images/products/product-30-1.jpg", imgSrc2 = "images/products/product-30-1.jpg"),
        Product(id = 579, category = "PUMA", title = "Gray Vintage Chair", price = 62.0, imgSrc = "images/products/product-31-1.jpg", imgSrc2 = "images/products/product_6-1.jpg"),
        Product(id = 580, category = "REEBOK", title = "Indy Icon Clash", price = 99.0, imgSrc = "images/products/product-32-1.jpg", imgSrc2 = "images/products/product_7-1.jpg"),
        Product(id = 581, category = "Dresses", title = "Colorful Jacket", price = 29.0, imgSrc = "images/products/product-33-1.jpg", imgSrc2 = "images/products/product_10.jpeg"),
    )

    // products53 - Additional fashion
    private val products53 = listOf(
        Product(id = 257, category = "Dresses", title = "Cropped Faux Leather Jacket", price = 29.0, imgSrc = "images/products/product-23-1.jpg", colors = true, reviews = "8k+ reviews", rating = 5.0),
        Product(id = 258, category = "Dresses", title = "Calvin Shorts", price = 62.0, imgSrc = "images/products/product-18-1.jpg", discount = 67),
        Product(id = 259, category = "Dresses", title = "Kirby T-Shirt", price = 17.0, imgSrc = "images/products/product_2.jpg"),
        Product(id = 260, category = "Dresses", title = "Cableknit Shawl", price = 99.0, priceOld = 129.0, imgSrc = "images/products/product-24-1.jpg", isNew = true),
        Product(id = 261, category = "Dresses", title = "Colorful Jacket", price = 29.0, imgSrc = "images/products/product-22-1.jpg"),
        Product(id = 262, category = "Dresses", title = "Shirt In Botanical Cheetah Print", price = 62.0, imgSrc = "images/products/product_9.jpeg"),
        Product(id = 263, category = "Dresses", title = "Cotton Jersey T-Shirt", price = 17.0, imgSrc = "images/products/product_8.jpg"),
        Product(id = 264, category = "Dresses", title = "Zessi Dresses", price = 99.0, priceOld = 129.0, imgSrc = "images/products/product-25-1.jpg"),
        Product(id = 265, category = "Dresses", title = "Cropped Faux Leather Jacket", price = 29.0, imgSrc = "images/products/product_7.jpg", reviews = "8k+ reviews", rating = 5.0),
        Product(id = 266, category = "Dresses", title = "Calvin Shorts", price = 62.0, imgSrc = "images/products/product_7.jpg"),
        Product(id = 267, category = "Dresses", title = "Kirby T-Shirt", price = 17.0, imgSrc = "images/products/product_15.jpeg"),
        Product(id = 268, category = "Dresses", title = "Cableknit Shawl", price = 99.0, priceOld = 129.0, imgSrc = "images/products/product_16.jpeg"),
    )

    /**
     * All products combined - mirrors the allProducts export from index.js
     * The shop page uses products52 as the primary display set
     */
    val allProducts: List<Product> = products1 + products51 + products52 + products53 +
        products54 + products55 + products56 + products57 + products58 + products59

    /**
     * Shop page products (the 9 products displayed on the main shop grid)
     * These come from products52 in the source
     */
    val shopProducts: List<Product> = products52

    /**
     * Fashion products with filter categories for the filtered shop view
     */
    val fashionProducts: List<Product> = products51
}
