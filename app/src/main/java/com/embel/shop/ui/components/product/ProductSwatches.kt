package com.embel.shop.ui.components.product

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.embel.shop.ui.theme.EmbelBorder
import com.embel.shop.ui.theme.EmbelDimens

data class ColorSwatch(
    val id: String,
    val label: String,
    val color: Color,
)

val defaultColorSwatches = listOf(
    ColorSwatch("swatch-11", "Black", Color(0xFF222222)),
    ColorSwatch("swatch-12", "Red", Color(0xFFC93A3E)),
    ColorSwatch("swatch-13", "Grey", Color(0xFFE4E4E4)),
)

val defaultSizes = listOf("XS", "S", "M", "L", "XL")

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProductSwatches(
    selectedSize: String?,
    selectedColor: String?,
    onSizeSelected: (String) -> Unit,
    onColorSelected: (String) -> Unit,
    onSizeGuideClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    sizes: List<String> = defaultSizes,
    colorSwatches: List<ColorSwatch> = defaultColorSwatches,
) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        // Sizes
        Text(
            text = "Sizes",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.height(8.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            sizes.forEach { size ->
                FilterChip(
                    selected = selectedSize == size,
                    onClick = { onSizeSelected(size) },
                    label = {
                        Text(
                            text = size,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = Color.White,
                    ),
                    shape = MaterialTheme.shapes.small,
                )
            }
        }
        TextButton(onClick = onSizeGuideClick) {
            Text(
                text = "Size Guide",
                style = MaterialTheme.typography.bodySmall,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Colors
        Text(
            text = "Color",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.height(8.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            colorSwatches.forEach { swatch ->
                val isSelected = selectedColor == swatch.id
                Box(
                    modifier = Modifier
                        .size(EmbelDimens.swatchSize)
                        .clip(CircleShape)
                        .background(swatch.color)
                        .then(
                            if (isSelected) {
                                Modifier.border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                            } else {
                                Modifier.border(1.dp, EmbelBorder, CircleShape)
                            }
                        )
                        .clickable { onColorSelected(swatch.id) }
                )
            }
        }
    }
}
