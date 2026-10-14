package com.kaamai.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaamai.app.viewmodel.KaamAIViewModel

@Composable
fun HomeScreen(
    viewModel: KaamAIViewModel,
    onStartNewVisit: () -> Unit = {}
) {
    val reports = viewModel.reports
    val showForm = viewModel.showForm
    val customer = viewModel.customer
    val notes = viewModel.notes

    val colors = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .padding(horizontal = 16.dp)
    ) {


        Spacer(Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = colors.surfaceVariant
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Text(
                            text = "✦  YOUR FIELDWORK ASSISTANT",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = colors.primary,
                            letterSpacing = 1.sp
                        )

                        Spacer(Modifier.height(14.dp))

                        Text(
                            text = "Work smarter.\nReport faster.",
                            fontSize = 27.sp,
                            lineHeight = 33.sp,
                            fontWeight = FontWeight.Bold,
                            color = colors.onSurface
                        )

                        Spacer(Modifier.height(8.dp))

                        Text(
                            text = "Record customer visits and keep your " +
                                    "daily work organised.",
                            fontSize = 14.sp,
                            lineHeight = 21.sp,
                            color = colors.onSurfaceVariant
                        )

                        Spacer(Modifier.height(20.dp))

                        Button(
                            onClick = {
                                if (showForm) {
                                    viewModel.toggleForm()
                                } else {
                                    onStartNewVisit()
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colors.primary
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = if (showForm) "Close Visit Form"
                                else "Start New Visit",
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }

            if (showForm) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = colors.surface
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "New Visit Report",
                                fontSize = 19.sp,
                                fontWeight = FontWeight.Bold,
                                color = colors.onSurface
                            )

                            Spacer(Modifier.height(12.dp))

                            OutlinedTextField(
                                value = customer,
                                onValueChange = {
                                    viewModel.updateCustomer(it)
                                },
                                label = { Text("Customer name") },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                singleLine = true
                            )

                            Spacer(Modifier.height(10.dp))

                            OutlinedTextField(
                                value = notes,
                                onValueChange = {
                                    viewModel.updateNotes(it)
                                },
                                label = { Text("Visit notes") },
                                placeholder = {
                                    Text("What happened during the visit?")
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                minLines = 3
                            )

                            Spacer(Modifier.height(12.dp))

                            Button(
                                onClick = { viewModel.addReport() },
                                enabled = customer.isNotBlank() &&
                                        notes.isNotBlank(),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colors.primary
                                )
                            ) {
                                Text("Save Report")
                            }
                        }
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Recent reports",
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        color = colors.onSurface
                    )

                    Text(
                        text = "${reports.size} reports",
                        fontSize = 12.sp,
                        color = colors.primary
                    )
                }
            }

            if (reports.isEmpty()) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = colors.surface
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(52.dp)
                                    .background(
                                        colors.surfaceVariant,
                                        CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Storefront,
                                    contentDescription = null,
                                    tint = colors.primary
                                )
                            }

                            Spacer(Modifier.height(12.dp))

                            Text(
                                text = "No reports yet",
                                fontWeight = FontWeight.SemiBold,
                                color = colors.onSurface
                            )

                            Spacer(Modifier.height(4.dp))

                            Text(
                                text = "Your customer visits will appear here.",
                                fontSize = 13.sp,
                                color = colors.onSurfaceVariant
                            )
                        }
                    }
                }
            } else {
                items(reports) { report ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = colors.surface
                        ),
                        elevation = CardDefaults.cardElevation(1.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .background(
                                        colors.surfaceVariant,
                                        RoundedCornerShape(14.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Storefront,
                                    contentDescription = null,
                                    tint = colors.primary
                                )
                            }

                            Spacer(Modifier.width(12.dp))

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = report.customer,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = colors.onSurface,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Spacer(Modifier.height(5.dp))

                                Text(
                                    text = report.notes,
                                    fontSize = 12.sp,
                                    color = colors.onSurfaceVariant,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }

                            Spacer(Modifier.width(8.dp))

                            Text(
                                text = "›",
                                fontSize = 28.sp,
                                color = colors.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            item {
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}
