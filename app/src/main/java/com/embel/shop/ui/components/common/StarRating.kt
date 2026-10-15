package com.embel.shop.ui.components.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.embel.shop.ui.theme.EmbelRatingStarActive
import com.embel.shop.ui.theme.EmbelRatingStarInactive

@Composable
fun StarRating(
    rating: Double,
    modifier: Modifier = Modifier,
    starSize: Int = 14,
) {
    Row(modifier = modifier) {
        for (i in 1..5) {
            val icon = when {
                i <= rating.toInt() -> Icons.Filled.Star
                i - 0.5 <= rating -> Icons.Filled.StarHalf
                else -> Icons.Outlined.Star
            }
            val tint = if (i <= rating.toInt() || i - 0.5 <= rating) {
                EmbelRatingStarActive
            } else {
                EmbelRatingStarInactive
            }
            Icon(
                imageVector = icon,
                contentDescription = "Star $i",
                tint = tint,
                modifier = Modifier.size(starSize.dp)
            )
        }
    }
}
