package com.embel.shop.ui.components.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.embel.shop.data.model.Product
import com.embel.shop.ui.components.common.StarRating
import com.embel.shop.ui.theme.EmbelSaleBadge
import com.embel.shop.ui.theme.EmbelTextMuted
import com.embel.shop.ui.theme.EmbelTextSecondary

@Composable
fun ProductInfo(
    product: Product,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        // Title
        Text(
            text = product.title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Medium,
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Rating
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            StarRating(rating = product.rating ?: 5.0)
            Text(
                text = product.reviews ?: "8k+ reviews",
                style = MaterialTheme.typography.bodySmall,
                color = EmbelTextSecondary,
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Price
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "$${product.price.toInt()}",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.SemiBold,
            )
            product.priceOld?.let { oldPrice ->
                Text(
                    text = "$${oldPrice.toInt()}",
                    style = MaterialTheme.typography.titleLarge,
                    color = EmbelTextMuted,
                    textDecoration = TextDecoration.LineThrough,
                )
            }
            product.discount?.let { discount ->
                Text(
                    text = "-${discount}%",
                    style = MaterialTheme.typography.titleMedium,
                    color = EmbelSaleBadge,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Description
        Text(
            text = "Phasellus sed volutpat orci. Fusce eget lore mauris vehicula elementum gravida nec dui. Aenean aliquam varius ipsum, non ultricies tellus sodales eu. Donec dignissim viverra nunc, ut aliquet magna posuere eget.",
            style = MaterialTheme.typography.bodyMedium,
            color = EmbelTextSecondary,
        )
    }
}
