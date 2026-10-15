package com.embel.shop.ui.components.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.embel.shop.ui.theme.EmbelTextSecondary

@Composable
fun ProductMetaInfo(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        MetaRow(label = "SKU:", value = "N/A")
        Spacer(modifier = Modifier.height(4.dp))
        MetaRow(label = "Categories:", value = "Casual & Urban Wear, Jackets, Men")
        Spacer(modifier = Modifier.height(4.dp))
        MetaRow(label = "Tags:", value = "biker, black, bomber, leather")
    }
}

@Composable
private fun MetaRow(label: String, value: String) {
    Row {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = EmbelTextSecondary,
        )
    }
}
