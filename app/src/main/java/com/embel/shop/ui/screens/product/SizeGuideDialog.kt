package com.embel.shop.ui.screens.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.embel.shop.ui.theme.EmbelTextSecondary

private data class SizeRow(
    val size: String,
    val chest: String,
    val waist: String,
    val hips: String,
)

private val sizeGuideData = listOf(
    SizeRow("XS", "32-34", "24-26", "33-35"),
    SizeRow("S", "34-36", "26-28", "35-37"),
    SizeRow("M", "36-38", "28-30", "37-39"),
    SizeRow("L", "38-40", "30-32", "39-41"),
    SizeRow("XL", "40-42", "32-34", "41-43"),
)

@Composable
fun SizeGuideDialog(
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Size Guide",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
            )
        },
        text = {
            Column {
                Text(
                    text = "This is an approximate guide to help you choose the right size.",
                    style = MaterialTheme.typography.bodySmall,
                    color = EmbelTextSecondary,
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Header
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text("Size", fontWeight = FontWeight.Bold, modifier = Modifier.width(50.dp), style = MaterialTheme.typography.bodySmall)
                    Text("Chest", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodySmall)
                    Text("Waist", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodySmall)
                    Text("Hips", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodySmall)
                }
                Divider(modifier = Modifier.padding(vertical = 4.dp))

                sizeGuideData.forEach { row ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Text(row.size, modifier = Modifier.width(50.dp), style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Medium)
                        Text(row.chest, modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodySmall, color = EmbelTextSecondary)
                        Text(row.waist, modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodySmall, color = EmbelTextSecondary)
                        Text(row.hips, modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodySmall, color = EmbelTextSecondary)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "All measurements are in inches.",
                    style = MaterialTheme.typography.bodySmall,
                    color = EmbelTextSecondary,
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Close")
            }
        },
    )
}
