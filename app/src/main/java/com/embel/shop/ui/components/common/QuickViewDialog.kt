package com.embel.shop.ui.components.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.embel.shop.data.model.Product
import com.embel.shop.ui.theme.EmbelTextMuted
import com.embel.shop.ui.theme.EmbelTextSecondary

@Composable
fun QuickViewDialog(
    product: Product,
    isInCart: Boolean,
    onAddToCart: () -> Unit,
    onViewDetail: () -> Unit,
    onDismiss: () -> Unit,
) {
    var quantity by remember { mutableIntStateOf(1) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface,
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Product image
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("file:///android_asset/${product.imgSrc}")
                        .crossfade(true)
                        .build(),
                    contentDescription = product.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Category
                Text(
                    text = product.category,
                    style = MaterialTheme.typography.bodySmall,
                    color = EmbelTextSecondary,
                )

                // Title
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Price
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "$${product.price.toInt()}",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                    )
                    product.priceOld?.let { oldPrice ->
                        Text(
                            text = "$${oldPrice.toInt()}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = EmbelTextMuted,
                            textDecoration = TextDecoration.LineThrough,
                        )
                    }
                }

                // Rating
                if (product.rating != null) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        StarRating(rating = product.rating)
                        product.reviews?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.bodySmall,
                                color = EmbelTextSecondary,
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Quantity + Add to cart
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    QuantityControl(
                        quantity = quantity,
                        onIncrement = { quantity++ },
                        onDecrement = { if (quantity > 1) quantity-- },
                    )
                    Button(
                        onClick = onAddToCart,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isInCart) EmbelTextMuted else MaterialTheme.colorScheme.primary,
                        ),
                        shape = MaterialTheme.shapes.small,
                    ) {
                        Text(
                            text = if (isInCart) "ALREADY ADDED" else "ADD TO CART",
                            style = MaterialTheme.typography.labelLarge,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // View full details button
                Button(
                    onClick = {
                        onDismiss()
                        onViewDetail()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    ),
                    shape = MaterialTheme.shapes.small,
                ) {
                    Text("View Full Details")
                }
            }
        }
    }
}
