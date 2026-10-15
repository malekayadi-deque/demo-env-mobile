package com.embel.shop.ui.screens.product

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.embel.shop.navigation.Screen
import com.embel.shop.ui.components.common.BreadcrumbBar
import com.embel.shop.ui.components.common.CartDrawer
import com.embel.shop.ui.components.common.CookieConsentBanner
import com.embel.shop.ui.components.common.EmbelBottomBar
import com.embel.shop.ui.components.common.EmbelTopAppBar
import com.embel.shop.ui.components.common.FooterSection
import com.embel.shop.ui.components.product.AddToCartSection
import com.embel.shop.ui.components.product.ProductImageSlider
import com.embel.shop.ui.components.product.ProductInfo
import com.embel.shop.ui.components.product.ProductMetaInfo
import com.embel.shop.ui.components.product.ProductSwatches
import com.embel.shop.ui.components.product.ProductTabs
import com.embel.shop.ui.components.product.RelatedProducts
import com.embel.shop.viewmodel.CartViewModel
import com.embel.shop.viewmodel.ProductDetailViewModel
import com.embel.shop.viewmodel.UIStateViewModel
import com.embel.shop.viewmodel.WishlistViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    productId: Int,
    navController: NavController,
    variant: String = "standard",
    productDetailViewModel: ProductDetailViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel(),
    wishlistViewModel: WishlistViewModel = hiltViewModel(),
    uiStateViewModel: UIStateViewModel = hiltViewModel(),
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val context = LocalContext.current

    // Load product
    LaunchedEffect(productId) {
        productDetailViewModel.loadProduct(productId)
    }

    val product by productDetailViewModel.product.collectAsState()
    val quantity by productDetailViewModel.quantity.collectAsState()
    val selectedSize by productDetailViewModel.selectedSize.collectAsState()
    val selectedColor by productDetailViewModel.selectedColor.collectAsState()
    val selectedTab by productDetailViewModel.selectedTab.collectAsState()
    val relatedProducts by productDetailViewModel.relatedProducts.collectAsState()

    val isCartDrawerOpen by uiStateViewModel.isCartDrawerOpen.collectAsState()
    val isCookieVisible by uiStateViewModel.isCookieConsentVisible.collectAsState()
    val wishlistIds by wishlistViewModel.wishlistIds.collectAsState()
    val cartItems by cartViewModel.cartItems.collectAsState()

    var showSizeGuide by remember { mutableStateOf(false) }

    // Determine variant properties
    val isOutOfStock = variant == "outofstock" || (product?.isOutOfStock == true)
    val isExternal = variant == "external" || (product?.isExternal == true)
    val isGrouped = variant == "grouped" || (product?.isGrouped == true)
    val isOnSale = variant == "onsale" || (product?.onSale == true)

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = { EmbelTopAppBar(navController = navController, scrollBehavior = scrollBehavior) },
            bottomBar = { EmbelBottomBar(navController = navController) },
        ) { paddingValues ->
            product?.let { currentProduct ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                ) {
                    // Breadcrumb
                    item {
                        BreadcrumbBar(
                            items = listOf(
                                "Home" to Screen.Home.route,
                                "Shop" to Screen.Shop.route,
                                currentProduct.title to null,
                            ),
                            onNavigate = { route -> navController.navigate(route) },
                        )
                    }

                    // Product image slider
                    item {
                        ProductImageSlider(
                            product = currentProduct,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }

                    // Product info
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        ProductInfo(product = currentProduct)
                    }

                    // Swatches (size & color selection) - not for grouped/external
                    if (!isGrouped) {
                        item {
                            Spacer(modifier = Modifier.height(16.dp))
                            ProductSwatches(
                                selectedSize = selectedSize,
                                selectedColor = selectedColor,
                                onSizeSelected = { productDetailViewModel.selectSize(it) },
                                onColorSelected = { productDetailViewModel.selectColor(it) },
                                onSizeGuideClick = { showSizeGuide = true },
                            )
                        }
                    }

                    // Grouped product options
                    if (isGrouped) {
                        item {
                            Spacer(modifier = Modifier.height(16.dp))
                            GroupedProductOptions()
                        }
                    }

                    // Add to cart section
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        AddToCartSection(
                            quantity = quantity,
                            isInCart = cartViewModel.isInCart(currentProduct.id),
                            isInWishlist = wishlistIds.contains(currentProduct.id),
                            isOutOfStock = isOutOfStock,
                            isExternal = isExternal,
                            onQuantityChange = { productDetailViewModel.setQuantity(it) },
                            onAddToCart = {
                                if (isExternal) {
                                    // Open external URL (Amazon)
                                    val intent = Intent(Intent.ACTION_VIEW).apply {
                                        data = android.net.Uri.parse("https://www.amazon.com")
                                    }
                                    context.startActivity(intent)
                                } else if (!isOutOfStock) {
                                    cartViewModel.addToCart(currentProduct, quantity)
                                    uiStateViewModel.openCartDrawer()
                                }
                            },
                            onToggleWishlist = { wishlistViewModel.toggleWishlist(currentProduct.id) },
                            onShare = {
                                val sendIntent = Intent(Intent.ACTION_SEND).apply {
                                    putExtra(Intent.EXTRA_TEXT, "Check out ${currentProduct.title} on Embel Shop!")
                                    type = "text/plain"
                                }
                                context.startActivity(Intent.createChooser(sendIntent, "Share via"))
                            },
                        )
                    }

                    // Meta info
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        ProductMetaInfo()
                    }

                    // Tabs (Description, Additional Info, Reviews)
                    item {
                        Spacer(modifier = Modifier.height(24.dp))
                        ProductTabs(
                            selectedTab = selectedTab,
                            onTabSelected = { productDetailViewModel.selectTab(it) },
                        )
                    }

                    // Related products
                    item {
                        Spacer(modifier = Modifier.height(32.dp))
                        RelatedProducts(
                            products = relatedProducts,
                            cartProductIds = cartItems.map { it.product.id }.toSet(),
                            wishlistIds = wishlistIds.toSet(),
                            onAddToCart = { product ->
                                cartViewModel.addProductToCart(product)
                                uiStateViewModel.openCartDrawer()
                            },
                            onToggleWishlist = { id -> wishlistViewModel.toggleWishlist(id) },
                            onNavigateToProduct = { id ->
                                navController.navigate(Screen.ProductDetail.createRoute(id))
                            },
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                    }

                    // Footer
                    item {
                        FooterSection(onNavigate = { route -> navController.navigate(route) })
                    }
                }
            } ?: run {
                // Loading/empty state
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "Product not found",
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                    )
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
            onExploreShop = { navController.navigate(Screen.Shop.route) },
        )

        // Cookie consent
        if (isCookieVisible) {
            CookieConsentBanner(
                onAccept = { uiStateViewModel.dismissCookieConsent() },
                modifier = Modifier.align(Alignment.BottomCenter),
            )
        }
    }

    // Size guide dialog
    if (showSizeGuide) {
        SizeGuideDialog(onDismiss = { showSizeGuide = false })
    }
}

@Composable
private fun GroupedProductOptions() {
    // Grouped product table with quantity selectors
    val groupedItems = listOf(
        Triple("Embel Logo Collection", 9.0, 1),
        Triple("Embel Triblend Tee", 16.0, 1),
        Triple("Embel Premium Tee", 20.0, 1),
    )

    androidx.compose.foundation.layout.Column(
        modifier = Modifier.padding(horizontal = 16.dp),
    ) {
        Text(
            text = "Grouped Products",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 12.dp),
        )

        groupedItems.forEach { (name, price, qty) ->
            var quantity by remember { mutableStateOf(qty) }
            androidx.compose.foundation.layout.Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                com.embel.shop.ui.components.common.QuantityControl(
                    quantity = quantity,
                    onIncrement = { quantity++ },
                    onDecrement = { if (quantity > 0) quantity-- },
                )
                androidx.compose.foundation.layout.Spacer(modifier = Modifier.weight(1f))
                androidx.compose.foundation.layout.Column {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Text(
                        text = "$${"%.2f".format(price)}",
                        style = MaterialTheme.typography.bodySmall,
                        color = com.embel.shop.ui.theme.EmbelTextSecondary,
                    )
                }
            }
            androidx.compose.material3.Divider()
        }
    }
}
