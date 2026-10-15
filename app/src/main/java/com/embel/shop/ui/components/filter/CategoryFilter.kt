package com.embel.shop.ui.components.filter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.embel.shop.data.model.CategoryOption

@Composable
fun CategoryFilter(
    categories: List<CategoryOption>,
    selectedCategory: String?,
    onCategorySelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        categories.forEach { category ->
            val isSelected = selectedCategory == category.name
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onCategorySelected(category.name) }
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = isSelected,
                    onCheckedChange = { onCategorySelected(category.name) },
                )
                Text(
                    text = category.name,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}
