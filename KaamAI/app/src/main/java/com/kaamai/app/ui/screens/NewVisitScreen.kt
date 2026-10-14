package com.kaamai.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NewVisitScreen(
    onBackClick: () -> Unit,
    onSaveClick: (String, String) -> Unit
) {
    var customer by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var speakMode by remember { mutableStateOf(true) }

    val colors = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(18.dp))

        Text(
            text = "Capture your customer visit in seconds.",
            fontSize = 14.sp,
            color = colors.onSurfaceVariant
        )

        Spacer(Modifier.height(22.dp))

        // Input mode selector
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    colors.surfaceVariant,
                    RoundedCornerShape(50.dp)
                )
                .padding(4.dp)
        ) {
            ModeButton(
                text = "Type",
                icon = {
                    Icon(
                        Icons.Default.Keyboard,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                },
                selected = !speakMode,
                modifier = Modifier.weight(1f),
                onClick = { speakMode = false }
            )

            ModeButton(
                text = "Speak",
                icon = {
                    Icon(
                        Icons.Default.Mic,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                },
                selected = speakMode,
                modifier = Modifier.weight(1f),
                onClick = { speakMode = true }
            )
        }

        Spacer(Modifier.height(24.dp))

        // Microphone illustration
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier.size(100.dp),
                shape = CircleShape,
                color = colors.surfaceVariant
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Surface(
                        modifier = Modifier.size(72.dp),
                        shape = CircleShape,
                        color = colors.primary
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.Mic,
                                contentDescription = "Microphone",
                                tint = colors.onPrimary,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            Text(
                text = if (speakMode) {
                    "Voice recording coming next"
                } else {
                    "Enter your visit details below"
                },
                fontSize = 13.sp,
                color = colors.onSurfaceVariant
            )
        }

        Spacer(Modifier.height(22.dp))

        // Visit form
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.surface, RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text(
                text = "Customer name",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = colors.onSurface
            )

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = customer,
                onValueChange = { customer = it },
                placeholder = { Text("Enter customer name") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(50.dp),
                singleLine = true
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Visit notes",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = colors.onSurface
            )

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                placeholder = {
                    Text("What happened during the visit?")
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(50.dp),
                minLines = 3
            )

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = {
                    onSaveClick(customer.trim(), notes.trim())
                },
                enabled = customer.isNotBlank() && notes.isNotBlank(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.primary
                )
            ) {
                Text(
                    text = "Save Report",
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        TextButton(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Cancel", color = colors.onSurfaceVariant)
        }

        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun ModeButton(
    text: String,
    icon: @Composable () -> Unit,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val colors = MaterialTheme.colorScheme

    Surface(
        onClick = onClick,
        modifier = modifier.height(44.dp),
        shape = RoundedCornerShape(50.dp),
        color = if (selected) colors.primary else Color.Transparent
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CompositionLocalProvider(
                LocalContentColor provides
                        if (selected) colors.onPrimary else colors.onSurface
            ) {
                icon()
                Spacer(Modifier.width(7.dp))
                Text(
                    text = text,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
