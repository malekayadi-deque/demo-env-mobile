package com.embel.shop.ui.screens.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.embel.shop.navigation.Screen
import com.embel.shop.ui.components.common.BreadcrumbBar
import com.embel.shop.ui.components.common.EmbelBottomBar
import com.embel.shop.ui.components.common.EmbelTopAppBar
import com.embel.shop.ui.components.common.FooterSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAccountScreen(
    navController: NavController,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var displayName by remember { mutableStateOf("") }
    var emailAddress by remember { mutableStateOf("") }
    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

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
                    items = listOf("Home" to Screen.Home.route, "My Account" to Screen.Dashboard.route, "Edit Account" to null),
                    onNavigate = { route -> navController.navigate(route) },
                )
            }

            item {
                DashboardSidebar(
                    currentRoute = Screen.AccountEdit.route,
                    onNavigate = { route -> navController.navigate(route) },
                )
            }

            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Name row
                    Row(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = firstName,
                            onValueChange = { firstName = it },
                            label = { Text("First Name") },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlinedTextField(
                            value = lastName,
                            onValueChange = { lastName = it },
                            label = { Text("Last Name") },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = displayName,
                        onValueChange = { displayName = it },
                        label = { Text("Display Name") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = emailAddress,
                        onValueChange = { emailAddress = it },
                        label = { Text("Email Address") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "PASSWORD CHANGE",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = currentPassword,
                        onValueChange = { currentPassword = it },
                        label = { Text("Current password") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = newPassword,
                        onValueChange = { newPassword = it },
                        label = { Text("New password") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        label = { Text("Confirm new password") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = { /* Save changes */ },
                        shape = MaterialTheme.shapes.small,
                    ) {
                        Text("Save Changes", fontWeight = FontWeight.SemiBold)
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                FooterSection(onNavigate = { route -> navController.navigate(route) })
            }
        }
    }
}
