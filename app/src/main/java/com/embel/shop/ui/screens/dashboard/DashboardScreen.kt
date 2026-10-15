package com.embel.shop.ui.screens.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
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
fun DashboardScreen(
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
                    items = listOf("Home" to Screen.Home.route, "My Account" to null),
                    onNavigate = { route -> navController.navigate(route) },
                )
            }

            // Sidebar
            item {
                DashboardSidebar(
                    currentRoute = Screen.Dashboard.route,
                    onNavigate = { route -> navController.navigate(route) },
                )
            }

            // Dashboard content
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Text(
                        text = buildAnnotatedString {
                            append("Hello ")
                            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("alitfn58")
                            }
                            append(" (not ")
                            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("alitfn58")
                            }
                            append("? ")
                        },
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    TextButton(onClick = { navController.navigate(Screen.LoginRegister.route) }) {
                        Text("Log out")
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "From your account dashboard you can view your recent orders, manage your shipping and billing addresses, and edit your password and account details.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = EmbelTextSecondary,
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TextButton(onClick = { navController.navigate(Screen.AccountOrders.route) }) {
                        Text("View recent orders")
                    }
                    TextButton(onClick = { navController.navigate(Screen.AccountEditAddress.route) }) {
                        Text("Manage addresses")
                    }
                    TextButton(onClick = { navController.navigate(Screen.AccountEdit.route) }) {
                        Text("Edit account details")
                    }
                }
            }

            item {
                FooterSection(onNavigate = { route -> navController.navigate(route) })
            }
        }
    }
}
