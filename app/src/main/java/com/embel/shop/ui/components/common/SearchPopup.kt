package com.embel.shop.ui.components.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.embel.shop.data.local.MenuData
import com.embel.shop.data.model.Product
import com.embel.shop.ui.theme.EmbelTextSecondary
import com.embel.shop.viewmodel.SearchViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SearchPopup(
    searchViewModel: SearchViewModel,
    onDismiss: () -> Unit,
    onProductClick: (Int) -> Unit,
    onCategoryClick: (String) -> Unit,
) {
    val searchQuery by searchViewModel.searchQuery.collectAsState()
    val searchResults by searchViewModel.searchResults.collectAsState()

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchViewModel.setSearchQuery(it) },
                placeholder = { Text("Search products...") },
                leadingIcon = {
                    Icon(Icons.Outlined.Search, contentDescription = "Search")
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchViewModel.setSearchQuery("") }) {
                            Icon(Icons.Outlined.Close, contentDescription = "Clear")
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )
        },
        text = {
            Column {
                if (searchResults.isNotEmpty()) {
                    Text(
                        text = "${searchResults.size} results found",
                        style = MaterialTheme.typography.bodySmall,
                        color = EmbelTextSecondary,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyColumn(modifier = Modifier.height(300.dp)) {
                        items(searchResults.take(10)) { product ->
                            Text(
                                text = product.title,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onProductClick(product.id) }
                                    .padding(vertical = 8.dp),
                            )
                            Divider()
                        }
                    }
                } else if (searchQuery.length >= 2) {
                    Text(
                        text = "No results found",
                        style = MaterialTheme.typography.bodyMedium,
                        color = EmbelTextSecondary,
                    )
                } else {
                    // Quick links
                    Text(
                        text = "Quick Links",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    FlowRow {
                        MenuData.searchQuickLinks.forEach { link ->
                            AssistChip(
                                onClick = { onCategoryClick(link) },
                                label = { Text(link) },
                                modifier = Modifier.padding(end = 4.dp, bottom = 4.dp),
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {},
    )
}
