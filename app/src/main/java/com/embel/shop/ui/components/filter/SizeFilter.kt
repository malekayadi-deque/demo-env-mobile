package com.embel.shop.ui.components.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.embel.shop.data.model.SizeOption

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SizeFilter(
    sizes: List<SizeOption>,
    selectedSizes: List<String>,
    onSizeToggled: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        sizes.forEach { size ->
            FilterChip(
                selected = selectedSizes.contains(size.label),
                onClick = { onSizeToggled(size.label) },
                label = {
                    Text(
                        text = size.label,
                        style = MaterialTheme.typography.bodySmall,
                    )
                },
                shape = MaterialTheme.shapes.small,
            )
        }
    }
}
