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
import com.kaamai.app.components.ModeButton

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
            .imePadding()
            .background(colors.background)
            .verticalScroll(rememberScrollState())
            .padding(all = 16.dp)
    ) {


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
        // Recording UI
        if (speakMode) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Microphone circle
                Box(
                    modifier = Modifier
                        .size(112.dp)
                        .background(
                            color = colors.primary.copy(alpha = 0.08f),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Surface(
                        modifier = Modifier.size(82.dp),
                        shape = CircleShape,
                        color = colors.primary
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.Mic,
                                contentDescription = "Microphone",
                                tint = colors.onPrimary,
                                modifier = Modifier.size(36.dp)
                            )
                        }
                    }
                }

                Spacer(Modifier.height(20.dp))

                Text(
                    text = "Ready to record your visit?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = colors.onSurface
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Tap below and describe your customer visit",
                    fontSize = 13.sp,
                    color = colors.onSurfaceVariant
                )

                Spacer(Modifier.height(24.dp))

                // Recording waveform placeholder
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val heights = listOf(
                        12, 20, 30, 18, 38, 26, 44, 22, 34,
                        16, 40, 24, 32, 14, 28, 42, 20, 12
                    )

                    heights.forEach { barHeight ->
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 3.dp)
                                .width(4.dp)
                                .height(barHeight.dp)
                                .background(
                                    colors.primary.copy(alpha = 0.75f),
                                    RoundedCornerShape(50.dp)
                                )
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                Text(
                    text = "00:00",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = colors.onSurface
                )

                Spacer(Modifier.height(20.dp))

                Button(
                    onClick = {
                        // Connect microphone recording here
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(50.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Mic,
                        contentDescription = null
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("Start Recording")
                }

                Spacer(Modifier.height(12.dp))

                Text(
                    text = "You can review and edit the transcript before saving",
                    fontSize = 12.sp,
                    color = colors.onSurfaceVariant
                )
            }
        }

        else {
            Text(
                text = "Enter your visit details below",
                fontSize = 13.sp,
                color = colors.onSurfaceVariant
            )

            Spacer(Modifier.height(22.dp))

            // Visit form
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colors.surface, RoundedCornerShape(8.dp))
                    .padding(16.dp)
            )
            {
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


