package com.embel.shop.ui.screens.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.embel.shop.navigation.Screen
import com.embel.shop.ui.components.common.BreadcrumbBar
import com.embel.shop.ui.components.common.EmbelBottomBar
import com.embel.shop.ui.components.common.EmbelTopAppBar
import com.embel.shop.ui.components.common.FooterSection
import com.embel.shop.ui.theme.EmbelTextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAddressScreen(
    navController: NavController,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { EmbelTopAppBar(navController = navController, scrollBehavior = scrollBehavior) },
        bottomBar = { EmbelBottomBar(navController = navController) },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            item {
                BreadcrumbBar(
                    items = listOf("Home" to Screen.Home.route, "My Account" to Screen.Dashboard.route, "Addresses" to null),
                    onNavigate = { route -> navController.navigate(route) },
                )
            }

            item {
                DashboardSidebar(
                    currentRoute = Screen.AccountEditAddress.route,
                    onNavigate = { route -> navController.navigate(route) },
                )
            }

            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "The following addresses will be used on the checkout page by default.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = EmbelTextSecondary,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Address cards
                    Row(modifier = Modifier.fillMaxWidth()) {
                        AddressCard(
                            title = "Billing Address",
                            name = "Daniel Robinson",
                            address = "1418 River Drive, Suite 35 Cottonhall, CA 9622",
                            country = "United States",
                            email = "sale@embel.com",
                            phone = "+1 497-112-1999",
                            modifier = Modifier.weight(1f),
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        AddressCard(
                            title = "Shipping Address",
                            name = "Daniel Robinson",
                            address = "1418 River Drive, Suite 35 Cottonhall, CA 9622",
                            country = "United States",
                            email = "sale@embel.com",
                            phone = "+1 497-112-1999",
                            modifier = Modifier.weight(1f),
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
                FooterSection(onNavigate = { route -> navController.navigate(route) })
            }
        }
    }
}

@Composable
private fun AddressCard(
    title: String,
    name: String,
    address: String,
    country: String,
    email: String,
    phone: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f),
                )
                TextButton(onClick = { /* Edit address */ }) {
                    Text("Edit", style = MaterialTheme.typography.bodySmall)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(name, style = MaterialTheme.typography.bodyMedium)
            Text(address, style = MaterialTheme.typography.bodySmall, color = EmbelTextSecondary)
            Text(country, style = MaterialTheme.typography.bodySmall, color = EmbelTextSecondary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(email, style = MaterialTheme.typography.bodySmall, color = EmbelTextSecondary)
            Text(phone, style = MaterialTheme.typography.bodySmall, color = EmbelTextSecondary)
        }
    }
}
