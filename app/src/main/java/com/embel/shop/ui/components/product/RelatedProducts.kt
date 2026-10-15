package com.embel.shop.ui.components.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.embel.shop.data.model.Product
import com.embel.shop.ui.components.common.ProductCard

@Composable
fun RelatedProducts(
    products: List<Product>,
    cartProductIds: Set<Int>,
    wishlistIds: Set<Int>,
    onAddToCart: (Product) -> Unit,
    onToggleWishlist: (Int) -> Unit,
    onNavigateToProduct: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (products.isEmpty()) return

    Column(modifier = modifier) {
        Text(
            text = "Related Products",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(horizontal = 16.dp),
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                items = products,
                key = { it.id },
            ) { product ->
                ProductCard(
                    product = product,
                    isInCart = cartProductIds.contains(product.id),
                    isInWishlist = wishlistIds.contains(product.id),
                    onAddToCart = { onAddToCart(product) },
                    onToggleWishlist = { onToggleWishlist(product.id) },
                    onNavigateToDetail = { onNavigateToProduct(product.id) },
                    modifier = Modifier.width(180.dp),
                )
            }
        }
    }
}
