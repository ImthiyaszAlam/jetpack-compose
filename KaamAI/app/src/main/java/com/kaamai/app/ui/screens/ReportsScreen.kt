package com.kaamai.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaamai.app.data.ReportItem

private val KaamBlue = Color(0xFF0866F5)
private val KaamText = Color(0xFF17243A)
private val KaamMuted = Color(0xFF78869B)
private val KaamBackground = Color(0xFFF7F9FC)

@Composable
fun ReportsScreen(
    onHomeClick: () -> Unit = {},
    onReportClick: (String, String) -> Unit = { _, _ -> }
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("All") }

    val reports = remember {
        listOf(
            ReportItem(
                "Sharma Traders",
                "Visited and delivered 20 units...",
                "Today, 10:24 AM",
                "Today"
            ),
            ReportItem(
                "R.K. Distributors",
                "Discussed new product launch...",
                "Yesterday, 4:18 PM",
                "This Week"
            ),
            ReportItem(
                "Agarwal Retail",
                "Customer happy with the stock...",
                "Yesterday, 11:42 AM",
                "This Week"
            ),
            ReportItem(
                "Gupta Enterprises",
                "Delivered 15 units. No issues.",
                "Apr 26, 3:15 PM",
                "This Week"
            ),
            ReportItem(
                "Singh Traders",
                "Customer requested 10 more units...",
                "Apr 25, 11:02 AM",
                "This Week"
            )
        )
    }

    val filteredReports = reports.filter { report ->
        val matchesSearch =
            report.customer.contains(searchQuery, ignoreCase = true) ||
                    report.notes.contains(searchQuery, ignoreCase = true)

        val matchesFilter = when (selectedFilter) {
            "Today" -> report.category == "Today"
            "This Week" -> report.category == "Today" ||
                    report.category == "This Week"
            else -> true
        }

        matchesSearch && matchesFilter
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(KaamBackground)
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(20.dp))

        // Screen title
        Text(
            text = "Reports",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = KaamText
        )

        Spacer(Modifier.height(16.dp))

        // Search field
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    "Search customers or notes",
                    fontSize = 14.sp
                )
            },
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search",
                    tint = KaamMuted
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                unfocusedBorderColor = Color(0xFFE4EAF2),
                focusedBorderColor = KaamBlue
            )
        )

        Spacer(Modifier.height(16.dp))

        // Filters
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color(0xFFEAF0F8),
                    RoundedCornerShape(50.dp)
                )
                .padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            listOf("All", "Today", "This Week").forEach { filter ->
                val selected = selectedFilter == filter

                Surface(
                    onClick = { selectedFilter = filter },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(50.dp),
                    color = if (selected) KaamBlue
                    else Color.Transparent
                ) {
                    Box(
                        modifier = Modifier.padding(
                            horizontal = 4.dp,
                            vertical = 10.dp
                        ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = filter,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (selected) Color.White
                            else KaamText
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(18.dp))

        Text(
            text = "${filteredReports.size} reports",
            fontSize = 13.sp,
            color = KaamMuted
        )

        Spacer(Modifier.height(10.dp))

        // Reports list
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(filteredReports) { report ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(1.dp),
                    onClick = {
                        onReportClick(report.customer, report.notes)
                    }
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(46.dp)
                                .background(
                                    Color(0xFFEAF3FF),
                                    RoundedCornerShape(14.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Storefront,
                                contentDescription = null,
                                tint = KaamBlue
                            )
                        }

                        Spacer(Modifier.width(12.dp))

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = report.customer,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = KaamText,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Spacer(Modifier.height(5.dp))

                            Text(
                                text = report.notes,
                                fontSize = 12.sp,
                                color = KaamMuted,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Spacer(Modifier.height(5.dp))

                            Text(
                                text = report.date,
                                fontSize = 11.sp,
                                color = KaamMuted
                            )
                        }

                        Icon(
                            Icons.Default.ChevronRight,
                            contentDescription = "View report",
                            tint = KaamMuted
                        )
                    }
                }
            }

            if (filteredReports.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 36.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            Icons.Default.Description,
                            contentDescription = null,
                            tint = KaamMuted,
                            modifier = Modifier.size(40.dp)
                        )

                        Spacer(Modifier.height(10.dp))

                        Text(
                            text = "No reports found",
                            fontWeight = FontWeight.SemiBold,
                            color = KaamText
                        )

                        Text(
                            text = "Try another search or filter.",
                            color = KaamMuted,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
    }
}
