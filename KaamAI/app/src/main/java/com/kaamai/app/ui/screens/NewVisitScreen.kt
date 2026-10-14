
package com.kaamai.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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

private val KaamBlue = Color(0xFF0866F5)
private val KaamBackground = Color(0xFFF7F9FC)
private val KaamText = Color(0xFF17243A)
private val KaamMuted = Color(0xFF78869B)

@Composable
fun NewVisitScreen(
    onBackClick: () -> Unit,
    onSaveClick: (String, String) -> Unit
) {
    var customer by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var speakMode by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(KaamBackground)
    ) {
        // Top bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = KaamText
                )
            }

            Spacer(Modifier.width(8.dp))

            Text(
                text = "New Visit Report",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = KaamText
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            Spacer(Modifier.height(18.dp))

            Text(
                text = "Capture your customer visit in seconds.",
                fontSize = 14.sp,
                color = KaamMuted
            )

            Spacer(Modifier.height(22.dp))

            // Input mode selector
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0xFFE8EEF7),
                        RoundedCornerShape(14.dp)
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
                    color = Color(0xFFDCEAFF)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Surface(
                            modifier = Modifier.size(72.dp),
                            shape = CircleShape,
                            color = KaamBlue
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Mic,
                                    contentDescription = "Microphone",
                                    tint = Color.White,
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
                    color = KaamMuted
                )
            }

            Spacer(Modifier.height(22.dp))

            // Visit form
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(18.dp))
                    .padding(16.dp)
            ) {
                Text(
                    text = "Customer name",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = KaamText
                )

                Spacer(Modifier.height(8.dp))

                OutlinedTextField(
                    value = customer,
                    onValueChange = { customer = it },
                    placeholder = { Text("Enter customer name") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    text = "Visit notes",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = KaamText
                )

                Spacer(Modifier.height(8.dp))

                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    placeholder = {
                        Text("What happened during the visit?")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
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
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = KaamBlue
                    )
                ) {
                    Text(
                        text = "Save Report",
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

            TextButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Cancel", color = KaamMuted)
            }
        }
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
    Surface(
        onClick = onClick,
        modifier = modifier.height(44.dp),
        shape = RoundedCornerShape(11.dp),
        color = if (selected) KaamBlue else Color.Transparent
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CompositionLocalProvider(
                LocalContentColor provides
                        if (selected) Color.White else KaamText
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
