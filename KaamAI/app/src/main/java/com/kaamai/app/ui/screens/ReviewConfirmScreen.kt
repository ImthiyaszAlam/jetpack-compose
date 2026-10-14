
package com.kaamai.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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

private val KaamBlue = Color(0xFF0866F5)
private val KaamText = Color(0xFF17243A)
private val KaamMuted = Color(0xFF78869B)
private val KaamBackground = Color(0xFFF7F9FC)

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

    Scaffold(
        containerColor = KaamBackground,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = KaamText
                    )
                }

                Spacer(Modifier.width(8.dp))

                Text(
                    text = "Review & Confirm",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = KaamText
                )
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = onSaveClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = KaamBlue
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
                        color = KaamBlue
                    )
                }
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // AI information card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFEAF3FF)
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
                            .background(Color.White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "✦",
                            fontSize = 24.sp,
                            color = KaamBlue
                        )
                    }

                    Spacer(Modifier.width(12.dp))

                    Column {
                        Text(
                            text = "AI has extracted the key details",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = KaamText
                        )

                        Spacer(Modifier.height(4.dp))

                        Text(
                            text = "Review everything before saving.",
                            fontSize = 12.sp,
                            color = KaamMuted
                        )
                    }
                }
            }

            // Report details
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(1.dp)
            ) {
                Column {
                    DetailRow(
                        icon = {
                            Icon(
                                Icons.Default.Storefront,
                                contentDescription = null,
                                tint = KaamBlue
                            )
                        },
                        label = "Customer",
                        value = customer,
                        editable = isEditing,
                        onValueChange = { customer = it }
                    )

                    HorizontalDivider(color = Color(0xFFEDF0F5))

                    DetailRow(
                        icon = {
                            Icon(
                                Icons.Default.CalendarMonth,
                                contentDescription = null,
                                tint = KaamBlue
                            )
                        },
                        label = "Visit date",
                        value = "Today",
                        editable = false,
                        onValueChange = {}
                    )

                    HorizontalDivider(color = Color(0xFFEDF0F5))

                    DetailRow(
                        icon = {
                            Icon(
                                Icons.Default.Inventory2,
                                contentDescription = null,
                                tint = KaamBlue
                            )
                        },
                        label = "Visit summary",
                        value = notes,
                        editable = isEditing,
                        onValueChange = { notes = it }
                    )

                    HorizontalDivider(color = Color(0xFFEDF0F5))

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
                color = KaamMuted,
                lineHeight = 18.sp
            )

            Spacer(Modifier.height(8.dp))
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
                    Color(0xFFEAF3FF),
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
                color = KaamMuted
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
                        color = KaamText
                    )
                )
            } else {
                Text(
                    text = value.ifBlank { "Not provided" },
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = KaamText,
                    lineHeight = 20.sp
                )
            }
        }
    }
}
