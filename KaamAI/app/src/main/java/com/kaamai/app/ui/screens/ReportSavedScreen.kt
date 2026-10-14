
package com.kaamai.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
private val KaamGreen = Color(0xFF16A765)

@Composable
fun ReportSavedScreen(
    customerName: String,
    visitNotes: String,
    onCreateAnotherClick: () -> Unit,
    onHomeClick: () -> Unit
) {
    Scaffold(
        containerColor = KaamBackground
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(64.dp))

            // Success icon
            Box(
                modifier = Modifier
                    .size(88.dp)
                    .background(KaamGreen, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Success",
                    tint = Color.White,
                    modifier = Modifier.size(48.dp)
                )
            }

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Report Saved!",
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold,
                color = KaamText
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Your visit report has been saved successfully.",
                fontSize = 14.sp,
                color = KaamMuted,
                lineHeight = 21.sp
            )

            Spacer(Modifier.height(32.dp))

            // Saved report summary
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(1.dp)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .background(
                                    Color(0xFFEAF3FF),
                                    RoundedCornerShape(12.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Storefront,
                                contentDescription = null,
                                tint = KaamBlue
                            )
                        }

                        Spacer(Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = customerName.ifBlank {
                                    "Customer"
                                },
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = KaamText
                            )

                            Text(
                                text = "Visit report",
                                fontSize = 12.sp,
                                color = KaamMuted
                            )
                        }

                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Saved",
                            tint = KaamGreen
                        )
                    }

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 16.dp),
                        color = Color(0xFFEDF0F5)
                    )

                    Row(verticalAlignment = Alignment.Top) {
                        Icon(
                            imageVector = Icons.Default.Inventory2,
                            contentDescription = null,
                            tint = KaamBlue,
                            modifier = Modifier.size(20.dp)
                        )

                        Spacer(Modifier.width(12.dp))

                        Column {
                            Text(
                                text = "Visit notes",
                                fontSize = 12.sp,
                                color = KaamMuted
                            )

                            Spacer(Modifier.height(4.dp))

                            Text(
                                text = visitNotes.ifBlank {
                                    "No notes added"
                                },
                                fontSize = 14.sp,
                                color = KaamText,
                                lineHeight = 20.sp
                            )
                        }
                    }

                    Spacer(Modifier.height(16.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.CalendarMonth,
                            contentDescription = null,
                            tint = KaamBlue,
                            modifier = Modifier.size(20.dp)
                        )

                        Spacer(Modifier.width(12.dp))

                        Column {
                            Text(
                                text = "Follow-up",
                                fontSize = 12.sp,
                                color = KaamMuted
                            )

                            Text(
                                text = "Schedule when needed",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = KaamText
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(32.dp))

            // Create another report
            Button(
                onClick = onCreateAnotherClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = KaamBlue
                )
            ) {
                Text(
                    text = "Create Another Report",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(Modifier.height(8.dp))

            // Back to home
            TextButton(
                onClick = onHomeClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Back to Home",
                    color = KaamBlue,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
