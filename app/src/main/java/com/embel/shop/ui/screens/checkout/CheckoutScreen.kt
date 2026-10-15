package com.embel.shop.ui.screens.checkout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.embel.shop.navigation.Screen
import com.embel.shop.ui.components.common.BreadcrumbBar
import com.embel.shop.ui.components.common.EmbelBottomBar
import com.embel.shop.ui.components.common.EmbelTopAppBar
import com.embel.shop.ui.components.common.FooterSection
import com.embel.shop.ui.theme.EmbelTextSecondary
import com.embel.shop.viewmodel.CartViewModel
import com.embel.shop.viewmodel.CheckoutViewModel

private data class PaymentMethod(
    val id: String,
    val label: String,
    val description: String,
)

private val paymentMethods = listOf(
    PaymentMethod(
        "bank_transfer", "Direct bank transfer",
        "Make your payment directly into our bank account. Please use your Order ID as the payment reference. Your order will not be shipped until the funds have cleared in our account."
    ),
    PaymentMethod(
        "check", "Check payments",
        "Phasellus sed volutpat orci. Fusce eget lore mauris vehicula elementum gravida nec dui. Aenean aliquam varius ipsum, non ultricies tellus sodales eu."
    ),
    PaymentMethod(
        "cod", "Cash on delivery",
        "Phasellus sed volutpat orci. Fusce eget lore mauris vehicula elementum gravida nec dui. Aenean aliquam varius ipsum, non ultricies tellus sodales eu."
    ),
    PaymentMethod(
        "paypal", "Paypal",
        "Phasellus sed volutpat orci. Fusce eget lore mauris vehicula elementum gravida nec dui. Aenean aliquam varius ipsum, non ultricies tellus sodales eu."
    ),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    navController: NavController,
    checkoutViewModel: CheckoutViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel(),
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val cartItems by cartViewModel.cartItems.collectAsState()
    val totalPrice by cartViewModel.totalPrice.collectAsState()

    val firstName by checkoutViewModel.firstName.collectAsState()
    val lastName by checkoutViewModel.lastName.collectAsState()
    val companyName by checkoutViewModel.companyName.collectAsState()
    val selectedCountry by checkoutViewModel.selectedCountry.collectAsState()
    val streetAddress by checkoutViewModel.streetAddress.collectAsState()
    val streetAddress2 by checkoutViewModel.streetAddress2.collectAsState()
    val city by checkoutViewModel.city.collectAsState()
    val zipCode by checkoutViewModel.zipCode.collectAsState()
    val province by checkoutViewModel.province.collectAsState()
    val phone by checkoutViewModel.phone.collectAsState()
    val email by checkoutViewModel.email.collectAsState()
    val orderNotes by checkoutViewModel.orderNotes.collectAsState()
    val createAccount by checkoutViewModel.createAccount.collectAsState()
    val shipDifferentAddress by checkoutViewModel.shipDifferentAddress.collectAsState()
    val paymentMethod by checkoutViewModel.paymentMethod.collectAsState()
    val isCountryDropdownOpen by checkoutViewModel.isCountryDropdownOpen.collectAsState()

    val vat = if (totalPrice > 0) 19.0 else 0.0
    val orderTotal = totalPrice + vat

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
            // Breadcrumb
            item {
                BreadcrumbBar(
                    items = listOf(
                        "Home" to Screen.Home.route,
                        "Checkout" to null,
                    ),
                    onNavigate = { route -> navController.navigate(route) },
                )
            }

            // Billing details header
            item {
                Text(
                    text = "BILLING DETAILS",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                )
            }

            // Form fields
            item {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    // First & Last name
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        OutlinedTextField(
                            value = firstName,
                            onValueChange = { checkoutViewModel.firstName.value = it },
                            label = { Text("First Name") },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                        )
                        OutlinedTextField(
                            value = lastName,
                            onValueChange = { checkoutViewModel.lastName.value = it },
                            label = { Text("Last Name") },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Company
                    OutlinedTextField(
                        value = companyName,
                        onValueChange = { checkoutViewModel.companyName.value = it },
                        label = { Text("Company Name (optional)") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Country dropdown
                    Column {
                        OutlinedTextField(
                            value = selectedCountry,
                            onValueChange = {},
                            label = { Text("Country / Region *") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { checkoutViewModel.toggleCountryDropdown() },
                            readOnly = true,
                            singleLine = true,
                        )
                        DropdownMenu(
                            expanded = isCountryDropdownOpen,
                            onDismissRequest = { checkoutViewModel.toggleCountryDropdown() },
                            modifier = Modifier.fillMaxWidth(0.9f),
                        ) {
                            checkoutViewModel.filteredCountries.forEach { country ->
                                DropdownMenuItem(
                                    text = { Text(country) },
                                    onClick = { checkoutViewModel.selectCountry(country) },
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Street address
                    OutlinedTextField(
                        value = streetAddress,
                        onValueChange = { checkoutViewModel.streetAddress.value = it },
                        label = { Text("Street Address *") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = streetAddress2,
                        onValueChange = { checkoutViewModel.streetAddress2.value = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // City
                    OutlinedTextField(
                        value = city,
                        onValueChange = { checkoutViewModel.city.value = it },
                        label = { Text("Town / City *") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Zip
                    OutlinedTextField(
                        value = zipCode,
                        onValueChange = { checkoutViewModel.zipCode.value = it },
                        label = { Text("Postcode / ZIP *") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Province
                    OutlinedTextField(
                        value = province,
                        onValueChange = { checkoutViewModel.province.value = it },
                        label = { Text("Province *") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Phone
                    OutlinedTextField(
                        value = phone,
                        onValueChange = { checkoutViewModel.phone.value = it },
                        label = { Text("Phone *") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Email
                    OutlinedTextField(
                        value = email,
                        onValueChange = { checkoutViewModel.email.value = it },
                        label = { Text("Your Mail *") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Checkboxes
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = createAccount,
                            onCheckedChange = { checkoutViewModel.createAccount.value = it },
                        )
                        Text("CREATE AN ACCOUNT?", style = MaterialTheme.typography.bodyMedium)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = shipDifferentAddress,
                            onCheckedChange = { checkoutViewModel.shipDifferentAddress.value = it },
                        )
                        Text("SHIP TO A DIFFERENT ADDRESS?", style = MaterialTheme.typography.bodyMedium)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Order notes
                    OutlinedTextField(
                        value = orderNotes,
                        onValueChange = { checkoutViewModel.orderNotes.value = it },
                        label = { Text("Order Notes (optional)") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        maxLines = 5,
                    )
                }
            }

            // Order summary
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Your Order",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    // Order items header
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text("PRODUCT", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                        Text("SUBTOTAL", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                    }
                    Divider(modifier = Modifier.padding(vertical = 8.dp))

                    // Order items
                    cartItems.forEach { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                text = "${item.product.title} x ${item.quantity}",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.weight(1f),
                            )
                            Text(
                                text = "$${item.lineTotal.toInt()}",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }

                    Divider(modifier = Modifier.padding(vertical = 8.dp))

                    // Totals
                    OrderTotalRow("SUBTOTAL", "$${totalPrice.toInt()}")
                    OrderTotalRow("SHIPPING", "Free shipping")
                    OrderTotalRow("VAT", "$${vat.toInt()}")
                    Divider(modifier = Modifier.padding(vertical = 4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text("TOTAL", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                        Text("$${orderTotal.toInt()}", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                    }
                }
            }

            // Payment methods
            item {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Spacer(modifier = Modifier.height(16.dp))
                    paymentMethods.forEach { method ->
                        Row(
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { checkoutViewModel.setPaymentMethod(method.id) }
                                .padding(vertical = 8.dp),
                        ) {
                            RadioButton(
                                selected = paymentMethod == method.id,
                                onClick = { checkoutViewModel.setPaymentMethod(method.id) },
                            )
                            Column(modifier = Modifier.padding(start = 4.dp)) {
                                Text(
                                    text = method.label,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Medium,
                                )
                                AnimatedVisibility(visible = paymentMethod == method.id) {
                                    Text(
                                        text = method.description,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = EmbelTextSecondary,
                                        modifier = Modifier.padding(top = 4.dp),
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Your personal data will be used to process your order, support your experience throughout this website, and for other purposes described in our privacy policy.",
                        style = MaterialTheme.typography.bodySmall,
                        color = EmbelTextSecondary,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Place order button
                    Button(
                        onClick = { navController.navigate(Screen.OrderConfirmation.route) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.small,
                    ) {
                        Text(
                            text = "PLACE ORDER",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(vertical = 8.dp),
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            // Footer
            item {
                FooterSection(onNavigate = { route -> navController.navigate(route) })
            }
        }
    }
}

@Composable
private fun OrderTotalRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(label, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
        Text(value, style = MaterialTheme.typography.bodyMedium)
    }
}
