package com.embel.shop.ui.screens.other

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.embel.shop.ui.theme.EmbelTextSecondary
import kotlinx.coroutines.delay

@Composable
fun ComingSoonScreen(
    navController: NavController,
) {
    var days by remember { mutableIntStateOf(47) }
    var hours by remember { mutableIntStateOf(6) }
    var minutes by remember { mutableIntStateOf(57) }
    var seconds by remember { mutableIntStateOf(11) }
    var emailInput by remember { mutableStateOf("") }

    // Countdown timer
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            if (seconds > 0) {
                seconds--
            } else {
                seconds = 59
                if (minutes > 0) {
                    minutes--
                } else {
                    minutes = 59
                    if (hours > 0) {
                        hours--
                    } else {
                        hours = 23
                        if (days > 0) {
                            days--
                        }
                    }
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "COMING SOON",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Our website is currently undergoing scheduled maintenance. We should be back shortly. Thank you for your patience.",
            style = MaterialTheme.typography.bodyMedium,
            color = EmbelTextSecondary,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Countdown
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            CountdownItem(value = days, label = "Days")
            CountdownItem(value = hours, label = "Hours")
            CountdownItem(value = minutes, label = "Minutes")
            CountdownItem(value = seconds, label = "Seconds")
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Newsletter signup
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedTextField(
                value = emailInput,
                onValueChange = { emailInput = it },
                placeholder = { Text("Your email address") },
                modifier = Modifier.weight(1f),
                singleLine = true,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { /* Join newsletter */ },
                shape = MaterialTheme.shapes.small,
            ) {
                Text("JOIN", fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
private fun CountdownItem(value: Int, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value.toString().padStart(2, '0'),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = EmbelTextSecondary,
        )
    }
}
