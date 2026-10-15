package com.embel.shop.ui.components.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.embel.shop.ui.components.common.QuantityControl
import com.embel.shop.ui.theme.EmbelSaleBadge
import com.embel.shop.ui.theme.EmbelTextMuted
import com.embel.shop.ui.theme.EmbelTextSecondary

@Composable
fun AddToCartSection(
    quantity: Int,
    isInCart: Boolean,
    isInWishlist: Boolean,
    isOutOfStock: Boolean = false,
    isExternal: Boolean = false,
    onQuantityChange: (Int) -> Unit,
    onAddToCart: () -> Unit,
    onToggleWishlist: () -> Unit,
    onShare: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Quantity control
            if (!isExternal && !isOutOfStock) {
                QuantityControl(
                    quantity = quantity,
                    onIncrement = { onQuantityChange(quantity + 1) },
                    onDecrement = { if (quantity > 1) onQuantityChange(quantity - 1) },
                )
            }

            // Add to cart button
            Button(
                onClick = onAddToCart,
                modifier = Modifier.weight(1f),
                enabled = !isOutOfStock,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isInCart) EmbelTextMuted else MaterialTheme.colorScheme.primary,
                    disabledContainerColor = EmbelTextMuted,
                ),
                shape = MaterialTheme.shapes.small,
            ) {
                Text(
                    text = when {
                        isOutOfStock -> "OUT OF STOCK"
                        isExternal -> "BUY ON AMAZON.COM"
                        isInCart -> "ALREADY ADDED"
                        else -> "ADD TO CART"
                    },
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(vertical = 8.dp),
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Wishlist + Share
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            TextButton(onClick = onToggleWishlist) {
                Icon(
                    imageVector = if (isInWishlist) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    tint = if (isInWishlist) EmbelSaleBadge else EmbelTextSecondary,
                )
                Text(
                    text = if (isInWishlist) "In Wishlist" else "Add to Wishlist",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 4.dp),
                )
            }
            TextButton(onClick = onShare) {
                Icon(
                    imageVector = Icons.Outlined.Share,
                    contentDescription = "Share",
                    tint = EmbelTextSecondary,
                )
                Text(
                    text = "Share",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 4.dp),
                )
            }
        }
    }
}
