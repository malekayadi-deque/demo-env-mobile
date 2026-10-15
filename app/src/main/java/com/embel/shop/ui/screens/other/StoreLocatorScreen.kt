package com.embel.shop.ui.screens.other

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.embel.shop.data.local.StoreLocationData
import com.embel.shop.navigation.Screen
import com.embel.shop.ui.components.common.BreadcrumbBar
import com.embel.shop.ui.components.common.EmbelBottomBar
import com.embel.shop.ui.components.common.EmbelTopAppBar
import com.embel.shop.ui.components.common.FooterSection
import com.embel.shop.ui.theme.EmbelTextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreLocatorScreen(
    navController: NavController,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var searchQuery by remember { mutableStateOf("") }

    val filteredStores = if (searchQuery.isBlank()) {
        StoreLocationData.storeLocations
    } else {
        StoreLocationData.storeLocations.filter {
            it.city.contains(searchQuery, ignoreCase = true) ||
                    it.country.contains(searchQuery, ignoreCase = true)
        }
    }

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
                    items = listOf("Home" to Screen.Home.route, "Store Locator" to null),
                    onNavigate = { route -> navController.navigate(route) },
                )
            }

            // Search
            item {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("Search by country or city") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    singleLine = true,
                )
            }

            // Store list
            items(filteredStores) { store ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = store.city,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(store.address, style = MaterialTheme.typography.bodySmall, color = EmbelTextSecondary)
                        Text(store.country, style = MaterialTheme.typography.bodySmall, color = EmbelTextSecondary)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Phone: ${store.phone}", style = MaterialTheme.typography.bodySmall, color = EmbelTextSecondary)
                        Text("Hours: ${store.hours}", style = MaterialTheme.typography.bodySmall, color = EmbelTextSecondary)
                        Text("Email: ${store.email}", style = MaterialTheme.typography.bodySmall, color = EmbelTextSecondary)
                        Spacer(modifier = Modifier.height(4.dp))
                        TextButton(onClick = { /* Open on map */ }) {
                            Text("See on the map")
                        }
                    }
                }
            }

            // Map placeholder
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(16.dp),
                ) {
                    Text(
                        text = "Map",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Card(
                        modifier = Modifier.fillMaxSize(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    ) {
                        // Google Maps integration placeholder
                        // In production, replace with GoogleMap composable
                        Text(
                            text = "Google Maps will be displayed here.\nIntegrate with Maps Compose SDK for full functionality.",
                            style = MaterialTheme.typography.bodySmall,
                            color = EmbelTextSecondary,
                            modifier = Modifier.padding(16.dp),
                        )
                    }
                }
            }

            item {
                FooterSection(onNavigate = { route -> navController.navigate(route) })
            }
        }
    }
}
