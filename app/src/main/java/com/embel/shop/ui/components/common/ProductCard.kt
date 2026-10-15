package com.embel.shop.ui.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.embel.shop.data.model.Product
import com.embel.shop.ui.theme.EmbelDimens
import com.embel.shop.ui.theme.EmbelNewBadge
import com.embel.shop.ui.theme.EmbelSaleBadge
import com.embel.shop.ui.theme.EmbelTextMuted
import com.embel.shop.ui.theme.EmbelTextSecondary

@Composable
fun ProductCard(
    product: Product,
    isInCart: Boolean,
    isInWishlist: Boolean,
    onAddToCart: () -> Unit,
    onToggleWishlist: () -> Unit,
    onNavigateToDetail: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clickable(onClick = onNavigateToDetail)
            .padding(EmbelDimens.cardPadding)
    ) {
        // Image with badges and wishlist
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("file:///android_asset/${product.imgSrc}")
                    .crossfade(true)
                    .build(),
                contentDescription = product.imgAlt ?: product.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(EmbelDimens.cardImageHeight)
            )

            // Badges
            Column(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                if (product.isNew) {
                    Text(
                        text = "NEW",
                        modifier = Modifier
                            .background(EmbelNewBadge)
                            .padding(horizontal = 8.dp, vertical = 2.dp),
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }
                product.discount?.let {
                    Text(
                        text = "-${it}%",
                        modifier = Modifier
                            .background(EmbelSaleBadge)
                            .padding(horizontal = 8.dp, vertical = 2.dp),
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }

            // Wishlist icon
            IconButton(
                onClick = onToggleWishlist,
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = if (isInWishlist) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = if (isInWishlist) "Remove from wishlist" else "Add to wishlist",
                    tint = if (isInWishlist) EmbelSaleBadge else EmbelTextMuted,
                    modifier = Modifier.size(20.dp)
                )
            }

            // Add to cart button overlay at bottom
            Button(
                onClick = onAddToCart,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isInCart) EmbelTextMuted else MaterialTheme.colorScheme.primary,
                ),
                shape = MaterialTheme.shapes.small,
            ) {
                Text(
                    text = if (isInCart) "ALREADY ADDED" else "ADD TO CART",
                    style = MaterialTheme.typography.labelLarge,
                    fontSize = 12.sp,
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Category
        Text(
            text = product.category,
            style = MaterialTheme.typography.bodySmall,
            color = EmbelTextSecondary,
        )

        // Title
        Text(
            text = product.title,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Price
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "$${product.price.toInt()}",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
            )
            product.priceOld?.let { oldPrice ->
                Text(
                    text = "$${oldPrice.toInt()}",
                    style = MaterialTheme.typography.bodySmall,
                    color = EmbelTextMuted,
                    textDecoration = TextDecoration.LineThrough,
                )
            }
        }

        // Rating and reviews
        if (product.rating != null && product.reviews != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                StarRating(rating = product.rating)
                Text(
                    text = product.reviews,
                    style = MaterialTheme.typography.bodySmall,
                    color = EmbelTextSecondary,
                )
            }
        }
    }
}
