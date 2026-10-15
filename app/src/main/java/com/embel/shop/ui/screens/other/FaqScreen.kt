package com.embel.shop.ui.screens.other

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.embel.shop.navigation.Screen
import com.embel.shop.ui.components.common.BreadcrumbBar
import com.embel.shop.ui.components.common.EmbelBottomBar
import com.embel.shop.ui.components.common.EmbelTopAppBar
import com.embel.shop.ui.components.common.FooterSection
import com.embel.shop.ui.theme.EmbelTextSecondary

private data class FaqItem(val question: String, val answer: String)
private data class FaqSection(val title: String, val items: List<FaqItem>)

private val faqSections = listOf(
    FaqSection(
        "Orders",
        listOf(
            FaqItem("How can I track my order?", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec tempus, sapien id lobortis accumsan, felis dui hendrerit ante, ut venenatis ipsum eros sed urna."),
            FaqItem("Can I change or cancel my order?", "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."),
            FaqItem("What if my order arrives damaged?", "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur."),
        ),
    ),
    FaqSection(
        "Shipping",
        listOf(
            FaqItem("How long does shipping take?", "Standard shipping typically takes 5-7 business days. Express shipping is available for an additional fee."),
            FaqItem("Do you ship internationally?", "Yes, we ship to most countries worldwide. International shipping times may vary."),
            FaqItem("What are the shipping costs?", "Shipping costs depend on the destination and weight of your order. Free shipping is available for orders over \$100."),
        ),
    ),
    FaqSection(
        "Payment",
        listOf(
            FaqItem("What payment methods do you accept?", "We accept Visa, MasterCard, American Express, PayPal, and direct bank transfer."),
            FaqItem("Is my payment information secure?", "Yes, all payment transactions are encrypted and processed through secure payment gateways."),
            FaqItem("Do you offer refunds?", "Yes, we offer full refunds within 30 days of purchase for unused items in original packaging."),
        ),
    ),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FaqScreen(
    navController: NavController,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val expandedItems = remember { mutableStateMapOf<String, Boolean>() }

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
                    items = listOf("Home" to Screen.Home.route, "FAQ" to null),
                    onNavigate = { route -> navController.navigate(route) },
                )
            }

            faqSections.forEach { section ->
                item {
                    Text(
                        text = section.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                    )
                }

                items(section.items) { faq ->
                    val key = "${section.title}_${faq.question}"
                    val isExpanded = expandedItems[key] == true

                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { expandedItems[key] = !isExpanded }
                                .padding(vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = faq.question,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.weight(1f),
                            )
                            Icon(
                                imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                contentDescription = if (isExpanded) "Collapse" else "Expand",
                            )
                        }
                        AnimatedVisibility(visible = isExpanded) {
                            Text(
                                text = faq.answer,
                                style = MaterialTheme.typography.bodyMedium,
                                color = EmbelTextSecondary,
                                modifier = Modifier.padding(bottom = 12.dp),
                            )
                        }
                        Divider()
                    }
                }

                item { Spacer(modifier = Modifier.height(16.dp)) }
            }

            item {
                FooterSection(onNavigate = { route -> navController.navigate(route) })
            }
        }
    }
}
