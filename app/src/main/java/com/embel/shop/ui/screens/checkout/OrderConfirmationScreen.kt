package com.embel.shop.ui.screens.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.embel.shop.navigation.Screen
import com.embel.shop.ui.components.common.BreadcrumbBar
import com.embel.shop.ui.components.common.EmbelBottomBar
import com.embel.shop.ui.components.common.EmbelTopAppBar
import com.embel.shop.ui.components.common.FooterSection
import com.embel.shop.ui.theme.EmbelAccent
import com.embel.shop.ui.theme.EmbelTextSecondary
import com.embel.shop.viewmodel.CartViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderConfirmationScreen(
    navController: NavController,
    cartViewModel: CartViewModel = hiltViewModel(),
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val cartItems by cartViewModel.cartItems.collectAsState()
    val totalPrice by cartViewModel.totalPrice.collectAsState()

    val vat = if (totalPrice > 0) 19.0 else 0.0
    val orderTotal = totalPrice + vat
    val dateFormatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    val todayDate = dateFormatter.format(Date())

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
            // Breadcrumb
            item {
                BreadcrumbBar(
                    items = listOf(
                        "Home" to Screen.Home.route,
                        "Order Confirmation" to null,
                    ),
                    onNavigate = { route -> navController.navigate(route) },
                )
            }

            // Success message
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    // Checkmark circle
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Order completed",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(EmbelAccent)
                            .padding(16.dp),
                        tint = Color.White,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Your order is completed!",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Thank you. Your order has been received.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = EmbelTextSecondary,
                        textAlign = TextAlign.Center,
                    )
                }
            }

            // Order info
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    OrderInfoItem("Order Number", "13119")
                    OrderInfoItem("Date", todayDate)
                    OrderInfoItem("Total", "$${orderTotal.toInt()}")
                    OrderInfoItem("Payment Method", "Direct Bank Transfer")
                }
            }

            // Order details
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Order Details",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    // Header
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text("PRODUCT", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                        Text("SUBTOTAL", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                    }
                    Divider(modifier = Modifier.padding(vertical = 8.dp))

                    // Items
                    cartItems.forEach { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                text = "${item.product.title} x ${item.quantity}",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.weight(1f),
                            )
                            Text(
                                text = "$${item.product.price.toInt()}",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }

                    Divider(modifier = Modifier.padding(vertical = 8.dp))

                    // Totals
                    TotalRow("SUBTOTAL", "$${totalPrice.toInt()}")
                    TotalRow("SHIPPING", "Free shipping")
                    TotalRow("VAT", "$${vat.toInt()}")
                    Divider(modifier = Modifier.padding(vertical = 4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text("TOTAL", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                        Text("$${orderTotal.toInt()}", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                    }
                }
            }

            // Footer
            item {
                Spacer(modifier = Modifier.height(32.dp))
                FooterSection(onNavigate = { route -> navController.navigate(route) })
            }
        }
    }
}

@Composable
private fun OrderInfoItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = EmbelTextSecondary,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

@Composable
private fun TotalRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(label, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
        Text(value, style = MaterialTheme.typography.bodyMedium)
    }
}
