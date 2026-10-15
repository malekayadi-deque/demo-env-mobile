package com.embel.shop.ui.components.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.embel.shop.data.local.FooterData
import com.embel.shop.ui.theme.EmbelTextSecondary

@Composable
fun FooterSection(
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var email by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Divider()
        Spacer(modifier = Modifier.height(24.dp))

        // Logo
        Text(
            text = "Embel",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Medium,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "1418 River Drive, Suite 35 Cottonhall, CA 9622",
            style = MaterialTheme.typography.bodySmall,
            color = EmbelTextSecondary,
        )
        Text(
            text = "sale@embel.com",
            style = MaterialTheme.typography.bodySmall,
            color = EmbelTextSecondary,
        )
        Text(
            text = "+1 246-345-0695",
            style = MaterialTheme.typography.bodySmall,
            color = EmbelTextSecondary,
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Company links
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            // Column 1 - Company
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "COMPANY",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(modifier = Modifier.height(8.dp))
                FooterData.footerLinks1.forEach { link ->
                    Text(
                        text = link.text,
                        style = MaterialTheme.typography.bodySmall,
                        color = EmbelTextSecondary,
                        modifier = Modifier
                            .clickable { onNavigate(link.route) }
                            .padding(vertical = 4.dp)
                    )
                }
            }

            // Column 2 - Shop
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "SHOP",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(modifier = Modifier.height(8.dp))
                FooterData.footerLinks2.forEach { link ->
                    Text(
                        text = link.text,
                        style = MaterialTheme.typography.bodySmall,
                        color = EmbelTextSecondary,
                        modifier = Modifier
                            .clickable { onNavigate(link.route) }
                            .padding(vertical = 4.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Help links
        Text(
            text = "HELP",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.height(8.dp))
        FooterData.footerLinks3.forEach { link ->
            Text(
                text = link.text,
                style = MaterialTheme.typography.bodySmall,
                color = EmbelTextSecondary,
                modifier = Modifier
                    .clickable { onNavigate(link.route) }
                    .padding(vertical = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Newsletter
        Text(
            text = "NEWSLETTER",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Be the first to hear about deals, offers, and exciting new collections.",
            style = MaterialTheme.typography.bodySmall,
            color = EmbelTextSecondary,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Your email address") },
                modifier = Modifier.weight(1f),
                singleLine = true,
                textStyle = MaterialTheme.typography.bodySmall,
            )
            TextButton(onClick = { /* newsletter signup */ }) {
                Text("JOIN")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Divider()
        Spacer(modifier = Modifier.height(16.dp))

        // Copyright
        Text(
            text = "\u00A9 2024 Embel. All rights reserved.",
            style = MaterialTheme.typography.bodySmall,
            color = EmbelTextSecondary,
        )
    }
}
