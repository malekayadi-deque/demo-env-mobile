package com.embel.shop.ui.components.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.embel.shop.ui.theme.EmbelAccent
import com.embel.shop.ui.theme.EmbelTextMuted
import com.embel.shop.ui.theme.EmbelTextSecondary

private val tabTitles = listOf("Description", "Additional Information", "Reviews (2)")

@Composable
fun ProductTabs(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary,
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { onTabSelected(index) },
                    text = {
                        Text(
                            text = title,
                            fontWeight = if (selectedTab == index) FontWeight.SemiBold else FontWeight.Normal,
                        )
                    },
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (selectedTab) {
            0 -> DescriptionTab()
            1 -> AdditionalInfoTab()
            2 -> ReviewsTab()
        }
    }
}

@Composable
private fun DescriptionTab() {
    Column {
        Text(
            text = "Sed do eiusmod tempor incididunt ut labore",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.",
            style = MaterialTheme.typography.bodyMedium,
            color = EmbelTextSecondary,
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Two columns of lists
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Why choose product?",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(modifier = Modifier.height(8.dp))
                BulletPoint("Creat by cotton fibric with soft and smooth")
                BulletPoint("Simple, Configurable (e.g. size, color, etc.), bundled")
                BulletPoint("Downloadable/Digital Products, Virtual Products")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Sample Number List",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(modifier = Modifier.height(8.dp))
                NumberPoint(1, "Create Store-specific attrittbutes on the fly")
                NumberPoint(2, "Simple, Configurable (e.g. size, color, etc.), bundled")
                NumberPoint(3, "Downloadable/Digital Products, Virtual Products")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Lining",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "100% Polyester, Main: 100% Polyester.",
            style = MaterialTheme.typography.bodyMedium,
            color = EmbelTextSecondary,
        )
    }
}

@Composable
private fun BulletPoint(text: String) {
    Row(modifier = Modifier.padding(vertical = 2.dp)) {
        Text(text = "•", modifier = Modifier.padding(end = 8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = EmbelTextSecondary,
        )
    }
}

@Composable
private fun NumberPoint(number: Int, text: String) {
    Row(modifier = Modifier.padding(vertical = 2.dp)) {
        Text(
            text = "$number.",
            modifier = Modifier.padding(end = 8.dp),
            fontWeight = FontWeight.Medium,
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = EmbelTextSecondary,
        )
    }
}

@Composable
private fun AdditionalInfoTab() {
    Column {
        InfoRow("Weight", "1.25 kg")
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        InfoRow("Dimensions", "90 x 60 x 90 cm")
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        InfoRow("Size", "XS, S, M, L, XL")
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        InfoRow("Color", "Black, Orange, White")
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        InfoRow("Storage", "Relaxed fit shirt-style dress with a rugged")
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.width(120.dp),
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = EmbelTextSecondary,
        )
    }
}

private data class ReviewData(
    val name: String,
    val date: String,
    val text: String,
    val rating: Int,
)

private val sampleReviews = listOf(
    ReviewData(
        name = "Janice Miller",
        date = "April 06, 2023",
        text = "Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est…",
        rating = 5,
    ),
    ReviewData(
        name = "Benjam Porter",
        date = "April 06, 2023",
        text = "Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est…",
        rating = 5,
    ),
)

@Composable
private fun ReviewsTab() {
    var ratingLength by remember { mutableIntStateOf(0) }
    var reviewText by remember { mutableStateOf("") }
    var reviewerName by remember { mutableStateOf("") }
    var reviewerEmail by remember { mutableStateOf("") }
    var saveInfo by remember { mutableStateOf(false) }

    Column {
        Text(
            text = "Reviews",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Existing reviews
        sampleReviews.forEach { review ->
            ReviewItem(review = review)
            Spacer(modifier = Modifier.height(16.dp))
        }

        Divider(modifier = Modifier.padding(vertical = 16.dp))

        // Review form
        Text(
            text = "Be the first to review \"Message Cotton T-Shirt\"",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Your email address will not be published. Required fields are marked *",
            style = MaterialTheme.typography.bodySmall,
            color = EmbelTextSecondary,
        )
        Spacer(modifier = Modifier.height(12.dp))

        // Star rating input
        Text(
            text = "Your rating *",
            style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            (1..5).forEach { index ->
                IconButton(
                    onClick = { ratingLength = index },
                    modifier = Modifier.size(32.dp),
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Rate $index stars",
                        tint = if (index <= ratingLength) EmbelAccent else EmbelTextMuted,
                        modifier = Modifier.size(20.dp),
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = reviewText,
            onValueChange = { reviewText = it },
            label = { Text("Your Review") },
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
            maxLines = 8,
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = reviewerName,
            onValueChange = { reviewerName = it },
            label = { Text("Name *") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = reviewerEmail,
            onValueChange = { reviewerEmail = it },
            label = { Text("Email address *") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = saveInfo,
                onCheckedChange = { saveInfo = it },
            )
            Text(
                text = "Save my name, email, and website in this browser for the next time I comment.",
                style = MaterialTheme.typography.bodySmall,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(onClick = { /* Submit review */ }) {
            Text("Submit")
        }
    }
}

@Composable
private fun ReviewItem(review: ReviewData) {
    Row {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("file:///android_asset/assets/images/avatar.jpg")
                .crossfade(true)
                .build(),
            contentDescription = review.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = review.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(modifier = Modifier.width(8.dp))
                repeat(review.rating) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = EmbelAccent,
                        modifier = Modifier.size(12.dp),
                    )
                }
            }
            Text(
                text = review.date,
                style = MaterialTheme.typography.bodySmall,
                color = EmbelTextSecondary,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = review.text,
                style = MaterialTheme.typography.bodyMedium,
                color = EmbelTextSecondary,
            )
        }
    }
}
