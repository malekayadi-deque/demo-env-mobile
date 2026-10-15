package com.embel.shop.ui.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.embel.shop.data.local.ProductData
import com.embel.shop.navigation.Screen
import com.embel.shop.ui.components.common.BreadcrumbBar
import com.embel.shop.ui.components.common.EmbelBottomBar
import com.embel.shop.ui.components.common.EmbelTopAppBar
import com.embel.shop.ui.components.common.FooterSection
import com.embel.shop.ui.components.common.ProductCard
import com.embel.shop.viewmodel.CartViewModel
import com.embel.shop.viewmodel.UIStateViewModel
import com.embel.shop.viewmodel.WishlistViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistScreen(
    navController: NavController,
    wishlistViewModel: WishlistViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel(),
    uiStateViewModel: UIStateViewModel = hiltViewModel(),
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val wishlistIds by wishlistViewModel.wishlistIds.collectAsState()
    val cartItems by cartViewModel.cartItems.collectAsState()

    val wishlistProducts = ProductData.allProducts.filter { wishlistIds.contains(it.id) }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { EmbelTopAppBar(navController = navController, scrollBehavior = scrollBehavior) },
        bottomBar = { EmbelBottomBar(navController = navController) },
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(horizontal = 8.dp),
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                BreadcrumbBar(
                    items = listOf("Home" to Screen.Home.route, "My Account" to Screen.Dashboard.route, "Wishlist" to null),
                    onNavigate = { route -> navController.navigate(route) },
                )
            }

            item(span = { GridItemSpan(maxLineSpan) }) {
                DashboardSidebar(
                    currentRoute = Screen.AccountWishlist.route,
                    onNavigate = { route -> navController.navigate(route) },
                )
            }

            if (wishlistProducts.isEmpty()) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(48.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "No products added to wishlist yet",
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            } else {
                items(
                    items = wishlistProducts,
                    key = { it.id },
                ) { product ->
                    ProductCard(
                        product = product,
                        isInCart = cartItems.any { it.product.id == product.id },
                        isInWishlist = true,
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
            }

            item(span = { GridItemSpan(maxLineSpan) }) {
                Spacer(modifier = Modifier.height(16.dp))
                FooterSection(onNavigate = { route -> navController.navigate(route) })
            }
        }
    }
}
