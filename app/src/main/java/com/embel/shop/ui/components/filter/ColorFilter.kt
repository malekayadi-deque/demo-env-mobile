package com.embel.shop.ui.components.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.embel.shop.data.model.ColorOption
import com.embel.shop.ui.theme.EmbelDimens

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ColorFilter(
    colors: List<ColorOption>,
    selectedColor: ColorOption?,
    onColorSelected: (ColorOption) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        colors.forEach { colorOption ->
            val isSelected = selectedColor == colorOption
            Box(
                modifier = Modifier
                    .size(EmbelDimens.swatchSize)
                    .clip(CircleShape)
                    .background(colorOption.color)
                    .then(
                        if (isSelected) {
                            Modifier.border(2.dp, Color.Black, CircleShape)
                        } else {
                            Modifier.border(1.dp, Color.LightGray, CircleShape)
                        }
                    )
                    .clickable(role = Role.RadioButton) { onColorSelected(colorOption) }
            )
        }
    }
}
