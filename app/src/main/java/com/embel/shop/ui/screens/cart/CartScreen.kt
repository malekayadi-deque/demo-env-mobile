package com.embel.shop.ui.screens.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.embel.shop.data.model.CartItem
import com.embel.shop.navigation.Screen
import com.embel.shop.ui.components.common.BreadcrumbBar
import com.embel.shop.ui.components.common.EmbelBottomBar
import com.embel.shop.ui.components.common.EmbelTopAppBar
import com.embel.shop.ui.components.common.FooterSection
import com.embel.shop.ui.components.common.QuantityControl
import com.embel.shop.ui.theme.EmbelTextMuted
import com.embel.shop.ui.theme.EmbelTextSecondary
import com.embel.shop.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    navController: NavController,
    cartViewModel: CartViewModel = hiltViewModel(),
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val cartItems by cartViewModel.cartItems.collectAsState()
    val totalPrice by cartViewModel.totalPrice.collectAsState()

    var couponCode by remember { mutableStateOf("") }
    val shippingOptions = remember {
        mutableStateMapOf(
            "free_shipping" to false,
            "flat_rate" to false,
            "local_pickup" to false,
        )
    }

    val shippingCost = (if (shippingOptions["flat_rate"] == true) 49.0 else 0.0) +
            (if (shippingOptions["local_pickup"] == true) 8.0 else 0.0)
    val vat = 19.0
    val grandTotal = totalPrice + shippingCost + vat

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
                        "Cart" to null,
                    ),
                    onNavigate = { route -> navController.navigate(route) },
                )
            }

            if (cartItems.isEmpty()) {
                // Empty cart
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(48.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "Shop cart is empty",
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedButton(
                            onClick = { navController.navigate(Screen.Shop.route) },
                        ) {
                            Text("Explore Products")
                        }
                    }
                }
            } else {
                // Cart items header
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                    ) {
                        Text("Product", fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(2f), style = MaterialTheme.typography.bodySmall)
                        Text("Price", fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodySmall, textAlign = TextAlign.Center)
                        Text("Qty", fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1.2f), style = MaterialTheme.typography.bodySmall, textAlign = TextAlign.Center)
                        Text("Total", fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodySmall, textAlign = TextAlign.End)
                        Spacer(modifier = Modifier.width(40.dp))
                    }
                    Divider()
                }

                // Cart items
                items(
                    items = cartItems,
                    key = { it.product.id },
                ) { item ->
                    CartItemRow(
                        item = item,
                        onQuantityChange = { qty -> cartViewModel.updateQuantity(item.product.id, qty) },
                        onRemove = { cartViewModel.removeItem(item.product.id) },
                    )
                    Divider(modifier = Modifier.padding(horizontal = 16.dp))
                }

                // Coupon code
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        OutlinedTextField(
                            value = couponCode,
                            onValueChange = { couponCode = it },
                            placeholder = { Text("Coupon Code") },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                        )
                        OutlinedButton(onClick = { /* Apply coupon */ }) {
                            Text("APPLY COUPON")
                        }
                    }
                }

                // Cart totals
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                    ) {
                        Text(
                            text = "Cart Totals",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        // Subtotal
                        TotalsRow("Subtotal", "$${totalPrice.toInt()}")

                        Divider(modifier = Modifier.padding(vertical = 8.dp))

                        // Shipping
                        Text(
                            text = "Shipping",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        ShippingCheckbox(
                            label = "Free shipping",
                            checked = shippingOptions["free_shipping"] == true,
                            onCheckedChange = { shippingOptions["free_shipping"] = it },
                        )
                        ShippingCheckbox(
                            label = "Flat rate: \$49",
                            checked = shippingOptions["flat_rate"] == true,
                            onCheckedChange = { shippingOptions["flat_rate"] = it },
                        )
                        ShippingCheckbox(
                            label = "Local pickup: \$8",
                            checked = shippingOptions["local_pickup"] == true,
                            onCheckedChange = { shippingOptions["local_pickup"] = it },
                        )

                        Text(
                            text = "Shipping to AL.",
                            style = MaterialTheme.typography.bodySmall,
                            color = EmbelTextSecondary,
                            modifier = Modifier.padding(top = 4.dp),
                        )

                        Divider(modifier = Modifier.padding(vertical = 8.dp))

                        // VAT
                        TotalsRow("VAT", "$${vat.toInt()}")

                        Divider(modifier = Modifier.padding(vertical = 8.dp))

                        // Total
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                text = "Total",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = "$${grandTotal.toInt()}",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Proceed to checkout
                        Button(
                            onClick = { navController.navigate(Screen.Checkout.route) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = MaterialTheme.shapes.small,
                        ) {
                            Text(
                                text = "PROCEED TO CHECKOUT",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(vertical = 8.dp),
                            )
                        }
                    }
                }
            }

            // Footer
            item {
                FooterSection(onNavigate = { route -> navController.navigate(route) })
            }
        }
    }
}

@Composable
private fun CartItemRow(
    item: CartItem,
    onQuantityChange: (Int) -> Unit,
    onRemove: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Product image + details
        Row(
            modifier = Modifier.weight(2f),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("file:///android_asset/${item.product.imgSrc}")
                    .crossfade(true)
                    .build(),
                contentDescription = item.product.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(60.dp),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = item.product.title,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Medium,
                    maxLines = 2,
                )
                Text(
                    text = "Color: Yellow",
                    style = MaterialTheme.typography.bodySmall,
                    color = EmbelTextSecondary,
                )
                Text(
                    text = "Size: L",
                    style = MaterialTheme.typography.bodySmall,
                    color = EmbelTextSecondary,
                )
            }
        }

        // Price
        Text(
            text = "$${item.product.price.toInt()}",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
        )

        // Quantity
        Box(modifier = Modifier.weight(1.2f)) {
            QuantityControl(
                quantity = item.quantity,
                onIncrement = { onQuantityChange(item.quantity + 1) },
                onDecrement = { if (item.quantity > 1) onQuantityChange(item.quantity - 1) },
            )
        }

        // Subtotal
        Text(
            text = "$${item.lineTotal.toInt()}",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End,
        )

        // Remove
        IconButton(
            onClick = onRemove,
            modifier = Modifier.size(40.dp),
        ) {
            Icon(
                imageVector = Icons.Outlined.Close,
                contentDescription = "Remove item",
                tint = EmbelTextMuted,
                modifier = Modifier.size(16.dp),
            )
        }
    }
}

@Composable
private fun TotalsRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
private fun ShippingCheckbox(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 2.dp),
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.size(24.dp),
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}
