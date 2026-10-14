package com.kaamai.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ReviewConfirmScreen(
    customerName: String,
    visitNotes: String,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    onEditClick: () -> Unit
) {
    var customer by remember(customerName) {
        mutableStateOf(customerName)
    }
    var notes by remember(visitNotes) {
        mutableStateOf(visitNotes)
    }
    var isEditing by remember { mutableStateOf(false) }

    val colors = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // AI information card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = colors.surfaceVariant
            ),
            elevation = CardDefaults.cardElevation(0.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(colors.surface, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "✦",
                        fontSize = 24.sp,
                        color = colors.primary
                    )
                }

                Spacer(Modifier.width(12.dp))

                Column {
                    Text(
                        text = "AI has extracted the key details",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = colors.onSurface
                    )

                    Spacer(Modifier.height(4.dp))

                    Text(
                        text = "Review everything before saving.",
                        fontSize = 12.sp,
                        color = colors.onSurfaceVariant
                    )
                }
            }
        }

        // Report details
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = colors.surface
            ),
            elevation = CardDefaults.cardElevation(1.dp)
        ) {
            Column {
                DetailRow(
                    icon = {
                        Icon(
                            Icons.Default.Storefront,
                            contentDescription = null,
                            tint = colors.primary
                        )
                    },
                    label = "Customer",
                    value = customer,
                    editable = isEditing,
                    onValueChange = { customer = it }
                )

                HorizontalDivider(color = colors.outline)

                DetailRow(
                    icon = {
                        Icon(
                            Icons.Default.CalendarMonth,
                            contentDescription = null,
                            tint = colors.primary
                        )
                    },
                    label = "Visit date",
                    value = "Today",
                    editable = false,
                    onValueChange = {}
                )

                HorizontalDivider(color = colors.outline)

                DetailRow(
                    icon = {
                        Icon(
                            Icons.Default.Inventory2,
                            contentDescription = null,
                            tint = colors.primary
                        )
                    },
                    label = "Visit summary",
                    value = notes,
                    editable = isEditing,
                    onValueChange = { notes = it }
                )

                HorizontalDivider(color = colors.outline)

                DetailRow(
                    icon = {
                        Icon(
                            Icons.Default.WarningAmber,
                            contentDescription = null,
                            tint = Color(0xFFDE8A13)
                        )
                    },
                    label = "Follow-up",
                    value = "Review notes and schedule if needed",
                    editable = false,
                    onValueChange = {}
                )
            }
        }

        Text(
            text = "Please verify the customer name and visit summary. " +
                    "AI-generated details may need correction.",
            fontSize = 12.sp,
            color = colors.onSurfaceVariant,
            lineHeight = 18.sp
        )

        Spacer(Modifier.height(8.dp))

        // Action buttons
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = onSaveClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.primary
                )
            ) {
                Icon(
                    Icons.Default.CheckCircle,
                    contentDescription = null
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    "Save Report",
                    fontWeight = FontWeight.SemiBold
                )
            }

            OutlinedButton(
                onClick = {
                    isEditing = !isEditing
                    onEditClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(46.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    if (isEditing) "Finish Editing" else "Edit Details",
                    color = colors.primary
                )
            }
        }
    }
}

@Composable
private fun DetailRow(
    icon: @Composable () -> Unit,
    label: String,
    value: String,
    editable: Boolean,
    onValueChange: (String) -> Unit
) {
    val colors = MaterialTheme.colorScheme

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .background(
                    colors.surfaceVariant,
                    RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            icon()
        }

        Spacer(Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                fontSize = 12.sp,
                color = colors.onSurfaceVariant
            )

            Spacer(Modifier.height(5.dp))

            if (editable) {
                OutlinedTextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 14.sp,
                        color = colors.onSurface
                    )
                )
            } else {
                Text(
                    text = value.ifBlank { "Not provided" },
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.onSurface,
                    lineHeight = 20.sp
                )
            }
        }
    }
}
