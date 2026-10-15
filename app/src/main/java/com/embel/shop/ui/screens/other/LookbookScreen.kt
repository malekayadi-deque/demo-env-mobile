package com.embel.shop.ui.screens.other

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.embel.shop.navigation.Screen
import com.embel.shop.ui.components.common.BreadcrumbBar
import com.embel.shop.ui.components.common.EmbelBottomBar
import com.embel.shop.ui.components.common.EmbelTopAppBar
import com.embel.shop.ui.components.common.FooterSection

private data class LookbookItem(
    val imgSrc: String,
    val title: String,
    val price: String,
)

private val lookbookItems = listOf(
    LookbookItem("assets/images/lookbook/look-1.jpg", "Women's T-Shirts", "From $19"),
    LookbookItem("assets/images/lookbook/look-2.jpg", "Slim Fit Cotton Shorts", "From $21"),
    LookbookItem("assets/images/lookbook/look-3.jpg", "Men's Sportswear", "From $39"),
    LookbookItem("assets/images/lookbook/look-4.jpg", "Knee-length Cotton Shorts", "From $39"),
    LookbookItem("assets/images/lookbook/look-5.jpg", "Canvas Trainers", "From $19"),
    LookbookItem("assets/images/lookbook/look-6.jpg", "Summer Collection", "From $29"),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LookbookScreen(
    navController: NavController,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

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
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                BreadcrumbBar(
                    items = listOf("Home" to Screen.Home.route, "Lookbook" to null),
                    onNavigate = { route -> navController.navigate(route) },
                )
            }

            items(lookbookItems) { item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.75f),
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("file:///android_asset/${item.imgSrc}")
                            .crossfade(true)
                            .build(),
                        contentDescription = item.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(12.dp),
                    ) {
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Text(
                            text = item.price,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.8f),
                        )
                    }
                }
            }

            item(span = { GridItemSpan(maxLineSpan) }) {
                Spacer(modifier = Modifier.height(16.dp))
                FooterSection(onNavigate = { route -> navController.navigate(route) })
            }
        }
    }
}
