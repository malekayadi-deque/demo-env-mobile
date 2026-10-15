package com.embel.shop.ui.screens.other

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import com.embel.shop.ui.theme.EmbelTextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    navController: NavController,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

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
            item {
                BreadcrumbBar(
                    items = listOf("Home" to Screen.Home.route, "About" to null),
                    onNavigate = { route -> navController.navigate(route) },
                )
            }

            // Banner image
            item {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("file:///android_asset/assets/images/about/about-1.jpg")
                        .crossfade(true)
                        .build(),
                    contentDescription = "About us banner",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                )
            }

            // Our Story
            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "OUR STORY",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = EmbelTextSecondary,
                    )
                }
            }

            // Mission & Vision
            item {
                Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "OUR MISSION",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                            style = MaterialTheme.typography.bodySmall,
                            color = EmbelTextSecondary,
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "OUR VISION",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                            style = MaterialTheme.typography.bodySmall,
                            color = EmbelTextSecondary,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            // The Company
            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "The Company",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Amet sapien dignissim a vestibulum division. Vel rhoncus non purus felis pretium aliquam congue. Duis nec in vestibulum et ante aliquet vulputate. Sed tincidunt enim sit id a quam sed lacus. Dignissim bibendum cursus a quis tellus. Sagittis at magna sed.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = EmbelTextSecondary,
                    )
                }
            }

            item {
                FooterSection(onNavigate = { route -> navController.navigate(route) })
            }
        }
    }
}
