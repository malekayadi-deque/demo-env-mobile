package com.embel.shop.ui.screens.shop

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.embel.shop.data.local.FilterData
import com.embel.shop.navigation.Screen
import com.embel.shop.ui.components.common.BreadcrumbBar
import com.embel.shop.ui.components.common.CartDrawer
import com.embel.shop.ui.components.common.CookieConsentBanner
import com.embel.shop.ui.components.common.EmbelBottomBar
import com.embel.shop.ui.components.common.EmbelTopAppBar
import com.embel.shop.ui.components.common.FooterSection
import com.embel.shop.ui.components.common.PaginationBar
import com.embel.shop.ui.components.common.ProductCard
import com.embel.shop.ui.components.filter.FilterPanel
import com.embel.shop.ui.theme.EmbelTextSecondary
import com.embel.shop.viewmodel.CartViewModel
import com.embel.shop.viewmodel.ShopViewModel
import com.embel.shop.viewmodel.UIStateViewModel
import com.embel.shop.viewmodel.WishlistViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopScreen(
    navController: NavController,
    shopViewModel: ShopViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel(),
    wishlistViewModel: WishlistViewModel = hiltViewModel(),
    uiStateViewModel: UIStateViewModel = hiltViewModel(),
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val isCartDrawerOpen by uiStateViewModel.isCartDrawerOpen.collectAsState()
    val isCookieVisible by uiStateViewModel.isCookieConsentVisible.collectAsState()
    val wishlistIds by wishlistViewModel.wishlistIds.collectAsState()
    val cartItems by cartViewModel.cartItems.collectAsState()

    val pagedProducts by shopViewModel.pagedProducts.collectAsState()
    val filteredProducts by shopViewModel.filteredProducts.collectAsState()
    val gridColumns by shopViewModel.gridColumns.collectAsState()
    val currentPage by shopViewModel.currentPage.collectAsState()
    val sortOption by shopViewModel.sortOption.collectAsState()

    var showSortDropdown by remember { mutableStateOf(false) }
    var showFilterSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    val totalPages = shopViewModel.totalPages

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = { EmbelTopAppBar(navController = navController, scrollBehavior = scrollBehavior) },
            bottomBar = { EmbelBottomBar(navController = navController) },
        ) { paddingValues ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(gridColumns),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(horizontal = 8.dp),
            ) {
                // Breadcrumb - full width
                item(span = { GridItemSpan(maxLineSpan) }) {
                    BreadcrumbBar(
                        items = listOf(
                            "Home" to Screen.Home.route,
                            "Shop" to null,
                        ),
                        onNavigate = { route -> navController.navigate(route) },
                    )
                }

                // Toolbar: Sort + View + Filter - full width
                item(span = { GridItemSpan(maxLineSpan) }) {
                    ShopToolbar(
                        sortLabel = sortOption.label,
                        selectedColumns = gridColumns,
                        totalProducts = filteredProducts.size,
                        onSortClick = { showSortDropdown = true },
                        isSortDropdownExpanded = showSortDropdown,
                        onDismissSortDropdown = { showSortDropdown = false },
                        onSortSelected = { option ->
                            shopViewModel.setSortOption(option)
                            showSortDropdown = false
                        },
                        onColumnsChanged = { shopViewModel.setGridColumns(it) },
                        onFilterClick = { showFilterSheet = true },
                    )
                }

                // Product grid
                items(
                    items = pagedProducts,
                    key = { it.id },
                ) { product ->
                    ProductCard(
                        product = product,
                        isInCart = cartItems.any { it.product.id == product.id },
                        isInWishlist = wishlistIds.contains(product.id),
                        onAddToCart = {
                            cartViewModel.addProductToCart(product)
                            uiStateViewModel.openCartDrawer()
                        },
                        onToggleWishlist = { wishlistViewModel.toggleWishlist(product.id) },
                        onNavigateToDetail = {
                            navController.navigate(Screen.ProductDetail.createRoute(product.id))
                        },
                    )
                }

                // Pagination - full width
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        PaginationBar(
                            currentPage = currentPage,
                            totalPages = totalPages,
                            onPageSelected = { shopViewModel.setPage(it) },
                        )
                    }
                }

                // Footer - full width
                item(span = { GridItemSpan(maxLineSpan) }) {
                    FooterSection(onNavigate = { route -> navController.navigate(route) })
                }
            }
        }

        // Cart drawer overlay
        CartDrawer(
            isVisible = isCartDrawerOpen,
            cartViewModel = cartViewModel,
            onClose = { uiStateViewModel.closeCartDrawer() },
            onViewCart = { navController.navigate(Screen.Cart.route) },
            onCheckout = { navController.navigate(Screen.Checkout.route) },
            onExploreShop = { /* Already on shop */ },
        )

        // Cookie consent
        if (isCookieVisible) {
            CookieConsentBanner(
                onAccept = { uiStateViewModel.dismissCookieConsent() },
                modifier = Modifier.align(Alignment.BottomCenter),
            )
        }
    }

    // Filter bottom sheet
    if (showFilterSheet) {
        ModalBottomSheet(
            onDismissRequest = { showFilterSheet = false },
            sheetState = sheetState,
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "FILTER BY",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                    )
                    TextButton(onClick = {
                        shopViewModel.clearAllFilters()
                        scope.launch {
                            sheetState.hide()
                            showFilterSheet = false
                        }
                    }) {
                        Text("Clear All")
                    }
                }
                FilterPanel(
                    shopViewModel = shopViewModel,
                    modifier = Modifier.height(500.dp),
                )
            }
        }
    }
}

@Composable
private fun ShopToolbar(
    sortLabel: String,
    selectedColumns: Int,
    totalProducts: Int,
    onSortClick: () -> Unit,
    isSortDropdownExpanded: Boolean,
    onDismissSortDropdown: () -> Unit,
    onSortSelected: (com.embel.shop.data.model.SortingOption) -> Unit,
    onColumnsChanged: (Int) -> Unit,
    onFilterClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Sort dropdown
        Box {
            TextButton(onClick = onSortClick) {
                Text(
                    text = sortLabel,
                    style = MaterialTheme.typography.bodyMedium,
                    color = EmbelTextSecondary,
                )
            }
            DropdownMenu(
                expanded = isSortDropdownExpanded,
                onDismissRequest = onDismissSortDropdown,
            ) {
                FilterData.sortingOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option.label) },
                        onClick = { onSortSelected(option) },
                    )
                }
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // View columns selector
            Text(
                text = "VIEW",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Medium,
                color = EmbelTextSecondary,
            )
            Spacer(modifier = Modifier.width(8.dp))
            listOf(2, 3, 4).forEach { cols ->
                Text(
                    text = cols.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = if (selectedColumns == cols) FontWeight.Bold else FontWeight.Normal,
                    color = if (selectedColumns == cols) MaterialTheme.colorScheme.primary else EmbelTextSecondary,
                    modifier = Modifier
                        .clickable { onColumnsChanged(cols) }
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Filter button
            IconButton(onClick = onFilterClick) {
                Icon(
                    imageVector = Icons.Outlined.FilterList,
                    contentDescription = "Filter products",
                )
            }
        }
    }
}
