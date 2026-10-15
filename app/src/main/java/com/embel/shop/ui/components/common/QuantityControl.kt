package com.embel.shop.ui.components.common

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.embel.shop.ui.theme.EmbelBorder
import com.embel.shop.ui.theme.EmbelDimens

@Composable
fun QuantityControl(
    quantity: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .border(1.dp, EmbelBorder),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "-",
            modifier = Modifier
                .clickable(role = Role.Button, onClick = onDecrement)
                .size(EmbelDimens.minTouchTarget)
                .padding(horizontal = 12.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            text = quantity.toString(),
            modifier = Modifier
                .widthIn(min = 40.dp)
                .padding(horizontal = 8.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            text = "+",
            modifier = Modifier
                .clickable(role = Role.Button, onClick = onIncrement)
                .size(EmbelDimens.minTouchTarget)
                .padding(horizontal = 12.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}
