package com.embel.shop.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.embel.shop.ui.screens.auth.LoginRegisterScreen
import com.embel.shop.ui.screens.auth.ResetPasswordScreen
import com.embel.shop.ui.screens.blog.BlogDetailScreen
import com.embel.shop.ui.screens.blog.BlogListScreen
import com.embel.shop.ui.screens.cart.CartScreen
import com.embel.shop.ui.screens.checkout.CheckoutScreen
import com.embel.shop.ui.screens.checkout.OrderConfirmationScreen
import com.embel.shop.ui.screens.checkout.OrderTrackingScreen
import com.embel.shop.ui.screens.dashboard.DashboardScreen
import com.embel.shop.ui.screens.dashboard.EditAccountScreen
import com.embel.shop.ui.screens.dashboard.EditAddressScreen
import com.embel.shop.ui.screens.dashboard.OrdersScreen
import com.embel.shop.ui.screens.dashboard.WishlistScreen
import com.embel.shop.ui.screens.home.HomeScreen
import com.embel.shop.ui.screens.other.AboutScreen
import com.embel.shop.ui.screens.other.ComingSoonScreen
import com.embel.shop.ui.screens.other.ContactScreen
import com.embel.shop.ui.screens.other.FaqScreen
import com.embel.shop.ui.screens.other.LookbookScreen
import com.embel.shop.ui.screens.other.NotFoundScreen
import com.embel.shop.ui.screens.other.StoreLocatorScreen
import com.embel.shop.ui.screens.other.TermsScreen
import com.embel.shop.ui.screens.product.ProductDetailScreen
import com.embel.shop.ui.screens.shop.ShopScreen

@Composable
fun EmbelNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        // Main screens
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.Shop.route) {
            ShopScreen(navController = navController)
        }

        // Product detail screens
        composable(
            route = Screen.ProductDetail.route,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
            ProductDetailScreen(productId = productId, navController = navController)
        }
        composable(
            route = Screen.ProductOnSale.route,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
            ProductDetailScreen(productId = productId, navController = navController, variant = "onsale")
        }
        composable(
            route = Screen.ProductGrouped.route,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
            ProductDetailScreen(productId = productId, navController = navController, variant = "grouped")
        }
        composable(
            route = Screen.ProductExternal.route,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
            ProductDetailScreen(productId = productId, navController = navController, variant = "external")
        }
        composable(
            route = Screen.ProductOutOfStock.route,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
            ProductDetailScreen(productId = productId, navController = navController, variant = "outofstock")
        }

        // Cart flow
        composable(Screen.Cart.route) {
            CartScreen(navController = navController)
        }
        composable(Screen.Checkout.route) {
            CheckoutScreen(navController = navController)
        }
        composable(Screen.OrderConfirmation.route) {
            OrderConfirmationScreen(navController = navController)
        }
        composable(Screen.OrderTracking.route) {
            OrderTrackingScreen(navController = navController)
        }

        // Auth
        composable(Screen.LoginRegister.route) {
            LoginRegisterScreen(navController = navController)
        }
        composable(Screen.ResetPassword.route) {
            ResetPasswordScreen(navController = navController)
        }

        // Dashboard
        composable(Screen.Dashboard.route) {
            DashboardScreen(navController = navController)
        }
        composable(Screen.AccountOrders.route) {
            OrdersScreen(navController = navController)
        }
        composable(Screen.AccountEdit.route) {
            EditAccountScreen(navController = navController)
        }
        composable(Screen.AccountEditAddress.route) {
            EditAddressScreen(navController = navController)
        }
        composable(Screen.AccountWishlist.route) {
            WishlistScreen(navController = navController)
        }

        // Blog
        composable(Screen.BlogList.route) {
            BlogListScreen(navController = navController)
        }
        composable(
            route = Screen.BlogDetail.route,
            arguments = listOf(navArgument("blogId") { type = NavType.IntType })
        ) { backStackEntry ->
            val blogId = backStackEntry.arguments?.getInt("blogId") ?: 0
            BlogDetailScreen(blogId = blogId, navController = navController)
        }

        // Other pages
        composable(Screen.About.route) {
            AboutScreen(navController = navController)
        }
        composable(Screen.Contact.route) {
            ContactScreen(navController = navController)
        }
        composable(Screen.Faq.route) {
            FaqScreen(navController = navController)
        }
        composable(Screen.Terms.route) {
            TermsScreen(navController = navController)
        }
        composable(Screen.StoreLocator.route) {
            StoreLocatorScreen(navController = navController)
        }
        composable(Screen.Lookbook.route) {
            LookbookScreen(navController = navController)
        }
        composable(Screen.ComingSoon.route) {
            ComingSoonScreen(navController = navController)
        }
        composable(Screen.NotFound.route) {
            NotFoundScreen(navController = navController)
        }
    }
}
