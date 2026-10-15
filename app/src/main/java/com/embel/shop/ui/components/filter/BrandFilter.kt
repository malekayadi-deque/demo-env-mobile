package com.embel.shop.ui.components.filter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.embel.shop.data.model.BrandOption
import com.embel.shop.ui.theme.EmbelTextSecondary

@Composable
fun BrandFilter(
    brands: List<BrandOption>,
    selectedBrands: List<BrandOption>,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onBrandToggled: (BrandOption) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChanged,
            placeholder = { Text("Search") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            textStyle = MaterialTheme.typography.bodySmall,
        )

        Spacer(modifier = Modifier.height(8.dp))

        val filteredBrands = if (searchQuery.isBlank()) {
            brands
        } else {
            brands.filter { it.name.contains(searchQuery, ignoreCase = true) }
        }

        filteredBrands.forEach { brand ->
            val isSelected = selectedBrands.contains(brand)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onBrandToggled(brand) }
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = isSelected,
                        onCheckedChange = { onBrandToggled(brand) },
                    )
                    Text(
                        text = brand.name,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
                Text(
                    text = "(${brand.count})",
                    style = MaterialTheme.typography.bodySmall,
                    color = EmbelTextSecondary,
                )
            }
        }
    }
}
