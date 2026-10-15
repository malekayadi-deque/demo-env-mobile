package com.embel.shop.data.local

import com.embel.shop.data.model.MenuItem

object MenuData {

    val mainMenu = listOf(
        MenuItem(id = 1, label = "Shop", route = "shop"),
        MenuItem(id = 2, label = "Blog", route = "blog"),
        MenuItem(id = 3, label = "About", route = "about"),
        MenuItem(id = 4, label = "Contact", route = "contact"),
    )

    val dashboardMenu = listOf(
        MenuItem(id = 1, label = "Dashboard", route = "account_dashboard"),
        MenuItem(id = 2, label = "Orders", route = "account_orders"),
        MenuItem(id = 3, label = "Addresses", route = "account_edit_address"),
        MenuItem(id = 4, label = "Account Details", route = "account_edit"),
        MenuItem(id = 5, label = "Wishlist", route = "account_wishlist"),
        MenuItem(id = 6, label = "Logout", route = "login_register"),
    )

    val searchQuickLinks = listOf(
        "New Arrivals", "Living room", "Bedroom", "Kitchen", "Bathroom", "Dining room"
    )
}
