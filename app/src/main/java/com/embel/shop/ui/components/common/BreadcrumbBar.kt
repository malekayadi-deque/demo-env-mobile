package com.embel.shop.ui.components.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.embel.shop.ui.theme.EmbelTextSecondary

@Composable
fun BreadcrumbBar(
    items: List<Pair<String, String?>>,
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit = {},
) {
    Row(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items.forEachIndexed { index, (label, route) ->
            if (index > 0) {
                Text(
                    text = " / ",
                    style = MaterialTheme.typography.bodySmall,
                    color = EmbelTextSecondary,
                )
            }
            if (route != null && index < items.size - 1) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodySmall,
                    color = EmbelTextSecondary,
                    modifier = Modifier.clickable { onNavigate(route) }
                )
            } else {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}
