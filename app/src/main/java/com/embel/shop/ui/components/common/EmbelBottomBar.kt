package com.embel.shop.ui.components.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.Store
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.embel.shop.navigation.Screen
import com.embel.shop.ui.theme.EmbelAccent

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String,
)

@Composable
fun EmbelBottomBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("Home", Icons.Outlined.Home, Screen.Home.route),
        BottomNavItem("Shop", Icons.Outlined.Store, Screen.Shop.route),
        BottomNavItem("Wishlist", Icons.Outlined.FavoriteBorder, Screen.AccountWishlist.route),
        BottomNavItem("Cart", Icons.Outlined.ShoppingBag, Screen.Cart.route),
        BottomNavItem("Account", Icons.Outlined.Person, Screen.Dashboard.route),
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(Screen.Home.route) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },
                label = { Text(text = item.label, style = MaterialTheme.typography.labelSmall) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = EmbelAccent,
                    selectedTextColor = EmbelAccent,
                    indicatorColor = MaterialTheme.colorScheme.background,
                ),
            )
        }
    }
}
