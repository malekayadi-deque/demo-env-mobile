package com.embel.shop.ui.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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

private data class OrderItem(
    val orderNumber: String,
    val date: String,
    val status: String,
    val total: String,
)

private val sampleOrders = listOf(
    OrderItem("#2416", "October 1, 2023", "On hold", "$1,200.65 for 3 items"),
    OrderItem("#2417", "October 2, 2023", "On hold", "$1,200.65 for 3 items"),
    OrderItem("#2418", "October 3, 2023", "On hold", "$1,200.65 for 3 items"),
    OrderItem("#2419", "October 4, 2023", "On hold", "$1,200.65 for 3 items"),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersScreen(
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
                    items = listOf("Home" to Screen.Home.route, "My Account" to Screen.Dashboard.route, "Orders" to null),
                    onNavigate = { route -> navController.navigate(route) },
                )
            }

            item {
                DashboardSidebar(
                    currentRoute = Screen.AccountOrders.route,
                    onNavigate = { route -> navController.navigate(route) },
                )
            }

            // Orders header
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                ) {
                    Text("Order", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodySmall)
                    Text("Date", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f), style = MaterialTheme.typography.bodySmall)
                    Text("Status", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodySmall)
                    Text("Total", fontWeight = FontWeight.Bold, modifier = Modifier.weight(2f), style = MaterialTheme.typography.bodySmall)
                    Text("", modifier = Modifier.weight(1f))
                }
                Divider()
            }

            items(sampleOrders) { order ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(order.orderNumber, modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodySmall)
                    Text(order.date, modifier = Modifier.weight(1.5f), style = MaterialTheme.typography.bodySmall, color = EmbelTextSecondary)
                    Text(order.status, modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodySmall, color = EmbelTextSecondary)
                    Text(order.total, modifier = Modifier.weight(2f), style = MaterialTheme.typography.bodySmall)
                    OutlinedButton(
                        onClick = { /* View order */ },
                        modifier = Modifier.weight(1f),
                    ) {
                        Text("VIEW", style = MaterialTheme.typography.labelSmall)
                    }
                }
                Divider(modifier = Modifier.padding(horizontal = 16.dp))
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
                FooterSection(onNavigate = { route -> navController.navigate(route) })
            }
        }
    }
}
