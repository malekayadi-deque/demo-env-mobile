package com.embel.shop.ui.screens.blog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.embel.shop.data.local.BlogData
import com.embel.shop.navigation.Screen
import com.embel.shop.ui.components.common.BreadcrumbBar
import com.embel.shop.ui.components.common.EmbelBottomBar
import com.embel.shop.ui.components.common.EmbelTopAppBar
import com.embel.shop.ui.components.common.FooterSection
import com.embel.shop.ui.theme.EmbelTextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogDetailScreen(
    blogId: Int,
    navController: NavController,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val blog = BlogData.blogs.find { it.id == blogId }

    var commentName by remember { mutableStateOf("") }
    var commentEmail by remember { mutableStateOf("") }
    var commentText by remember { mutableStateOf("") }

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
                    items = listOf<Pair<String, String?>>(
                        "Home" to Screen.Home.route,
                        "Blog" to Screen.BlogList.route,
                        (blog?.title ?: "Post") to null,
                    ),
                    onNavigate = { route -> navController.navigate(route) },
                )
            }

            blog?.let { post ->
                // Featured image
                item {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("file:///android_asset/${post.imgSrc}")
                            .crossfade(true)
                            .build(),
                        contentDescription = post.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                    )
                }

                // Title and meta
                item {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = post.title,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "by ${post.author} | ${post.date} | ${post.category}",
                            style = MaterialTheme.typography.bodySmall,
                            color = EmbelTextSecondary,
                        )
                    }
                }

                // Content
                item {
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        Text(
                            text = post.content,
                            style = MaterialTheme.typography.bodyMedium,
                            color = EmbelTextSecondary,
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Sed do eiusmod tempor incididunt",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = EmbelTextSecondary,
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Bullet points
                        listOf(
                            "Creat by cotton fibric with soft and smooth",
                            "Simple, Configurable (e.g. size, color, etc.), bundled",
                            "Downloadable/Digital Products, Virtual Products",
                        ).forEach { point ->
                            Row(modifier = Modifier.padding(vertical = 2.dp)) {
                                Text("• ", style = MaterialTheme.typography.bodyMedium)
                                Text(point, style = MaterialTheme.typography.bodyMedium, color = EmbelTextSecondary)
                            }
                        }
                    }
                }

                // Comments section
                item {
                    Divider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp))

                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        Text(
                            text = "${post.comments} Comments",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = "Leave a Comment",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        OutlinedTextField(
                            value = commentText,
                            onValueChange = { commentText = it },
                            label = { Text("Comment *") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp),
                            maxLines = 5,
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(modifier = Modifier.fillMaxWidth()) {
                            OutlinedTextField(
                                value = commentName,
                                onValueChange = { commentName = it },
                                label = { Text("Name *") },
                                modifier = Modifier.weight(1f),
                                singleLine = true,
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            OutlinedTextField(
                                value = commentEmail,
                                onValueChange = { commentEmail = it },
                                label = { Text("Email *") },
                                modifier = Modifier.weight(1f),
                                singleLine = true,
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedButton(onClick = { /* Submit comment */ }) {
                            Text("Submit")
                        }
                    }
                }
            }

            // Footer
            item {
                Spacer(modifier = Modifier.height(32.dp))
                FooterSection(onNavigate = { route -> navController.navigate(route) })
            }
        }
    }
}
