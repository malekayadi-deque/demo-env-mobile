package com.embel.shop.ui.components.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.embel.shop.data.model.CartItem
import com.embel.shop.ui.theme.EmbelDimens
import com.embel.shop.ui.theme.EmbelTextSecondary
import com.embel.shop.viewmodel.CartViewModel

@Composable
fun CartDrawer(
    isVisible: Boolean,
    cartViewModel: CartViewModel,
    onClose: () -> Unit,
    onViewCart: () -> Unit,
    onCheckout: () -> Unit,
    onExploreShop: () -> Unit,
) {
    val cartItems by cartViewModel.cartItems.collectAsState()
    val totalPrice by cartViewModel.totalPrice.collectAsState()

    // Scrim overlay
    if (isVisible) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f))
                .clickable(onClick = onClose)
        )
    }

    // Drawer sliding from right
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInHorizontally(initialOffsetX = { it }),
        exit = slideOutHorizontally(targetOffsetX = { it }),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.CenterEnd,
        ) {
            Column(
                modifier = Modifier
                    .width(EmbelDimens.asideWidth)
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(EmbelDimens.asidePadding)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "SHOPPING BAG (${cartItems.size})",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                    )
                    IconButton(onClick = onClose) {
                        Icon(Icons.Outlined.Close, contentDescription = "Close cart")
                    }
                }

                Divider()
                Spacer(modifier = Modifier.height(16.dp))

                if (cartItems.isEmpty()) {
                    // Empty state
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = "Your cart is currently empty.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = EmbelTextSecondary,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                onClose()
                                onExploreShop()
                            },
                            shape = MaterialTheme.shapes.small,
                        ) {
                            Text("EXPLORE SHOP")
                        }
                    }
                } else {
                    // Cart items
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        items(cartItems, key = { it.product.id }) { cartItem ->
                            CartDrawerItem(
                                cartItem = cartItem,
                                onIncrement = { cartViewModel.updateQuantity(cartItem.product.id, cartItem.quantity + 1) },
                                onDecrement = { cartViewModel.updateQuantity(cartItem.product.id, cartItem.quantity - 1) },
                                onRemove = { cartViewModel.removeItem(cartItem.product.id) },
                            )
                        }
                    }

                    // Footer with subtotal and buttons
                    Divider()
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = "SUBTOTAL:",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Text(
                            text = "$${totalPrice.toInt()}",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedButton(
                        onClick = {
                            onClose()
                            onViewCart()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.small,
                    ) {
                        Text("VIEW CART")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            onClose()
                            onCheckout()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.small,
                    ) {
                        Text("CHECKOUT")
                    }
                }
            }
        }
    }
}

@Composable
private fun CartDrawerItem(
    cartItem: CartItem,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    onRemove: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("file:///android_asset/${cartItem.product.imgSrc}")
                .crossfade(true)
                .build(),
            contentDescription = cartItem.product.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(80.dp)
        )

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = cartItem.product.title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
            )
            Spacer(modifier = Modifier.height(4.dp))
            QuantityControl(
                quantity = cartItem.quantity,
                onIncrement = onIncrement,
                onDecrement = onDecrement,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "$${cartItem.product.price.toInt()} x ${cartItem.quantity}",
                style = MaterialTheme.typography.bodySmall,
                color = EmbelTextSecondary,
            )
        }

        IconButton(onClick = onRemove) {
            Icon(
                Icons.Outlined.Delete,
                contentDescription = "Remove ${cartItem.product.title}",
                modifier = Modifier.size(18.dp)
            )
        }
    }
}
