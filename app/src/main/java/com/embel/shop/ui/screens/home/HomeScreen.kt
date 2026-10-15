package com.embel.shop.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.embel.shop.data.local.BlogData
import com.embel.shop.data.local.BrandData
import com.embel.shop.data.local.CollectionData
import com.embel.shop.data.local.FeatureData
import com.embel.shop.data.local.HeroSlideData
import com.embel.shop.data.local.ProductData
import com.embel.shop.navigation.Screen
import com.embel.shop.ui.components.common.CartDrawer
import com.embel.shop.ui.components.common.CookieConsentBanner
import com.embel.shop.ui.components.common.EmbelBottomBar
import com.embel.shop.ui.components.common.EmbelTopAppBar
import com.embel.shop.ui.components.common.FooterSection
import com.embel.shop.ui.components.common.ProductCard
import com.embel.shop.ui.theme.EmbelTextSecondary
import com.embel.shop.viewmodel.CartViewModel
import com.embel.shop.viewmodel.UIStateViewModel
import com.embel.shop.viewmodel.WishlistViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    cartViewModel: CartViewModel = hiltViewModel(),
    wishlistViewModel: WishlistViewModel = hiltViewModel(),
    uiStateViewModel: UIStateViewModel = hiltViewModel(),
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val isCartDrawerOpen by uiStateViewModel.isCartDrawerOpen.collectAsState()
    val isCookieVisible by uiStateViewModel.isCookieConsentVisible.collectAsState()
    val wishlistIds by wishlistViewModel.wishlistIds.collectAsState()
    val cartItems by cartViewModel.cartItems.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = { EmbelTopAppBar(navController = navController, scrollBehavior = scrollBehavior) },
            bottomBar = { EmbelBottomBar(navController = navController) },
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            ) {
                // Hero Carousel
                item {
                    HeroCarousel()
                }

                // Collections Grid
                item {
                    SectionTitle("Our Collections")
                    CollectionsGrid(onNavigate = { navController.navigate(Screen.Shop.route) })
                }

                // Best Selling Products
                item {
                    SectionTitle("Best Selling")
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        items(ProductData.shopProducts.take(4)) { product ->
                            ProductCard(
                                product = product,
                                isInCart = cartItems.any { it.product.id == product.id },
                                isInWishlist = wishlistIds.contains(product.id),
                                onAddToCart = {
                                    cartViewModel.addProductToCart(product)
                                    uiStateViewModel.openCartDrawer()
                                },
                                onToggleWishlist = { wishlistViewModel.toggleWishlist(product.id) },
                                onNavigateToDetail = { navController.navigate(Screen.ProductDetail.createRoute(product.id)) },
                                modifier = Modifier.width(180.dp),
                            )
                        }
                    }
                }

                // Blog Preview
                item {
                    SectionTitle("From The Blog")
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        items(BlogData.blogs) { blog ->
                            Column(
                                modifier = Modifier
                                    .width(260.dp)
                                    .clickable { navController.navigate(Screen.BlogDetail.createRoute(blog.id)) }
                            ) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data("file:///android_asset/${blog.imgSrc}")
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = blog.title,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(180.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "${blog.category} | ${blog.date}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = EmbelTextSecondary,
                                )
                                Text(
                                    text = blog.title,
                                    style = MaterialTheme.typography.titleSmall,
                                    maxLines = 2,
                                )
                            }
                        }
                    }
                }

                // Brands
                item {
                    SectionTitle("Our Brands")
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(24.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        items(BrandData.brandImages) { brand ->
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data("file:///android_asset/${brand.imgSrc}")
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "Brand logo",
                                modifier = Modifier.height(36.dp),
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }

                // Features/Services
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 24.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        FeatureData.servicePromotions.forEach { feature ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.weight(1f).padding(horizontal = 4.dp),
                            ) {
                                Text(
                                    text = feature.title,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Center,
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = feature.content,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = EmbelTextSecondary,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                    }
                }

                // Footer
                item {
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
}

@OptIn(androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
private fun HeroCarousel() {
    val pagerState = rememberPagerState(pageCount = { HeroSlideData.slides.size })

    LaunchedEffect(pagerState) {
        while (true) {
            delay(5000)
            val next = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.animateScrollToPage(next)
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
    ) { page ->
        val slide = HeroSlideData.slides[page]
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("file:///android_asset/${slide.imageSrc}")
                    .crossfade(true)
                    .build(),
                contentDescription = slide.imageAlt,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(24.dp),
            ) {
                Text(
                    text = slide.subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    letterSpacing = 2.sp,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = slide.title,
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }
}

@Composable
private fun CollectionsGrid(onNavigate: () -> Unit) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        CollectionData.collections.chunked(2).forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                row.forEach { collection ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1.2f)
                            .clickable(onClick = onNavigate),
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("file:///android_asset/${collection.imageSrc}")
                                .crossfade(true)
                                .build(),
                            contentDescription = collection.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .background(Color.Black.copy(alpha = 0.4f))
                                .fillMaxWidth()
                                .padding(12.dp),
                        ) {
                            Text(
                                text = collection.title,
                                style = MaterialTheme.typography.titleSmall,
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                            )
                            Text(
                                text = collection.productCount,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.8f),
                            )
                        }
                    }
                }
                // Pad if odd number
                if (row.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Medium,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
    )
}
