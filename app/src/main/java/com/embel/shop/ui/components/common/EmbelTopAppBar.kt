package com.embel.shop.ui.components.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.embel.shop.navigation.Screen
import com.embel.shop.ui.theme.EmbelAccent
import com.embel.shop.viewmodel.CartViewModel
import com.embel.shop.viewmodel.UIStateViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmbelTopAppBar(
    navController: NavController,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    cartViewModel: CartViewModel = hiltViewModel(),
    uiStateViewModel: UIStateViewModel = hiltViewModel(),
) {
    val cartCount by cartViewModel.cartCount.collectAsState()

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Embel",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.clickable { navController.navigate(Screen.Home.route) }
            )
        },
        actions = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 8.dp)
            ) {
                IconButton(onClick = { uiStateViewModel.openSearch() }) {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "Search",
                        modifier = Modifier.size(22.dp)
                    )
                }
                IconButton(onClick = { navController.navigate(Screen.LoginRegister.route) }) {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "Account",
                        modifier = Modifier.size(22.dp)
                    )
                }
                IconButton(onClick = { navController.navigate(Screen.AccountWishlist.route) }) {
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = "Wishlist",
                        modifier = Modifier.size(22.dp)
                    )
                }
                IconButton(onClick = { uiStateViewModel.openCartDrawer() }) {
                    BadgedBox(
                        badge = {
                            if (cartCount > 0) {
                                Badge(containerColor = EmbelAccent) {
                                    Text(
                                        text = cartCount.toString(),
                                        fontSize = 10.sp
                                    )
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ShoppingBag,
                            contentDescription = "Shopping cart, $cartCount items",
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
            }
        },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            scrolledContainerColor = MaterialTheme.colorScheme.background,
        ),
    )
}
