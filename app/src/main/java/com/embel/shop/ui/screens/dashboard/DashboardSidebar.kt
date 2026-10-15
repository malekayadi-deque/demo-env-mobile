package com.embel.shop.ui.screens.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.embel.shop.navigation.Screen
import com.embel.shop.ui.theme.EmbelTextSecondary

data class DashboardNavItem(
    val title: String,
    val route: String,
)

val dashboardNavItems = listOf(
    DashboardNavItem("Dashboard", Screen.Dashboard.route),
    DashboardNavItem("Orders", Screen.AccountOrders.route),
    DashboardNavItem("Addresses", Screen.AccountEditAddress.route),
    DashboardNavItem("Account Details", Screen.AccountEdit.route),
    DashboardNavItem("Wishlist", Screen.AccountWishlist.route),
    DashboardNavItem("Logout", Screen.LoginRegister.route),
)

@Composable
fun DashboardSidebar(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        dashboardNavItems.forEach { item ->
            val isActive = currentRoute == item.route
            Text(
                text = item.title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal,
                color = if (isActive) MaterialTheme.colorScheme.primary else EmbelTextSecondary,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNavigate(item.route) }
                    .padding(vertical = 12.dp),
            )
            Divider()
        }
    }
}
