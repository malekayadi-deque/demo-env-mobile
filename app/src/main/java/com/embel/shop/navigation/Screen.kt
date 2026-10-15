package com.embel.shop.navigation

sealed class Screen(val route: String) {
    // Main screens
    data object Home : Screen("home")
    data object Shop : Screen("shop")

    // Product detail variants
    data object ProductDetail : Screen("product/{productId}") {
        fun createRoute(productId: Int) = "product/$productId"
    }
    data object ProductOnSale : Screen("product_onsale/{productId}") {
        fun createRoute(productId: Int) = "product_onsale/$productId"
    }
    data object ProductGrouped : Screen("product_grouped/{productId}") {
        fun createRoute(productId: Int) = "product_grouped/$productId"
    }
    data object ProductExternal : Screen("product_external/{productId}") {
        fun createRoute(productId: Int) = "product_external/$productId"
    }
    data object ProductOutOfStock : Screen("product_outofstock/{productId}") {
        fun createRoute(productId: Int) = "product_outofstock/$productId"
    }

    // Cart flow
    data object Cart : Screen("shop_cart")
    data object Checkout : Screen("shop_checkout")
    data object OrderConfirmation : Screen("order-confirmation")
    data object OrderTracking : Screen("shop_order_tracking")

    // Auth
    data object LoginRegister : Screen("login_register")
    data object ResetPassword : Screen("reset_password")

    // Dashboard
    data object Dashboard : Screen("account_dashboard")
    data object AccountOrders : Screen("account_orders")
    data object AccountEdit : Screen("account_edit")
    data object AccountEditAddress : Screen("account_edit_address")
    data object AccountWishlist : Screen("account_wishlist")

    // Blog
    data object BlogList : Screen("blog")
    data object BlogDetail : Screen("blog/{blogId}") {
        fun createRoute(blogId: Int) = "blog/$blogId"
    }

    // Other pages
    data object About : Screen("about")
    data object Contact : Screen("contact")
    data object Faq : Screen("faq")
    data object Terms : Screen("terms")
    data object StoreLocator : Screen("store_location")
    data object Lookbook : Screen("lookbook")
    data object ComingSoon : Screen("coming_soon")
    data object NotFound : Screen("page-not-found")
}
