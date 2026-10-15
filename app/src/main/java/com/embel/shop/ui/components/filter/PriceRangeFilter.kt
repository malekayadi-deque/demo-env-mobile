package com.embel.shop.ui.components.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.embel.shop.data.local.FilterData
import com.embel.shop.ui.theme.EmbelTextSecondary

@Composable
fun PriceRangeFilter(
    range: IntRange,
    onRangeChanged: (IntRange) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        RangeSlider(
            value = range.first.toFloat()..range.last.toFloat(),
            onValueChange = { sliderRange ->
                onRangeChanged(sliderRange.start.toInt()..sliderRange.endInclusive.toInt())
            },
            valueRange = FilterData.PRICE_MIN.toFloat()..FilterData.PRICE_MAX.toFloat(),
            modifier = Modifier.fillMaxWidth(),
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "$$${range.first}",
                style = MaterialTheme.typography.bodySmall,
                color = EmbelTextSecondary,
            )
            Text(
                text = "$$${range.last}",
                style = MaterialTheme.typography.bodySmall,
                color = EmbelTextSecondary,
            )
        }
    }
}
