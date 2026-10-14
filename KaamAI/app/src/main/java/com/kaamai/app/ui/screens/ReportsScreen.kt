package com.kaamai.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaamai.app.data.ReportItem

@Composable
fun ReportsScreen(
    onHomeClick: () -> Unit = {},
    onReportClick: (String, String) -> Unit = { _, _ -> }
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("All") }

    val colors = MaterialTheme.colorScheme

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
            .background(colors.background)
            .padding(horizontal = 16.dp)
    ) {
        Spacer(Modifier.height(8.dp))

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
                    tint = colors.onSurfaceVariant
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(50.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = colors.surface,
                focusedContainerColor = colors.surface,
                unfocusedBorderColor = colors.outline,
                focusedBorderColor = colors.primary
            )
        )

        Spacer(Modifier.height(16.dp))

        // Filters
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    colors.surfaceVariant,
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
                    color = if (selected) colors.primary
                    else colors.surfaceVariant
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
                            color = if (selected) colors.onPrimary
                            else colors.onSurface
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(18.dp))

        Text(
            text = "${filteredReports.size} reports",
            fontSize = 13.sp,
            color = colors.onSurfaceVariant
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
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = colors.surface
                    ),
                    elevation = CardDefaults.cardElevation(1.dp),
                    onClick = {
                        onReportClick(report.customer, report.notes)
                    }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(46.dp)
                                .background(
                                    colors.surfaceVariant,
                                    RoundedCornerShape(50.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Storefront,
                                contentDescription = null,
                                tint = colors.primary
                            )
                        }

                        Spacer(Modifier.width(12.dp))
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            Text(
                                text = report.customer,
                                fontSize = 14.sp,
                                lineHeight = 16.sp,
                                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                                color = colors.onSurface,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Text(
                                text = report.notes,
                                fontSize = 12.sp,
                                lineHeight = 14.sp,
                                color = colors.onSurfaceVariant,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Text(
                                text = report.date,
                                fontSize = 11.sp,
                                lineHeight = 13.sp,
                                color = colors.onSurfaceVariant
                            )
                        }

                        Icon(
                            Icons.Default.ChevronRight,
                            contentDescription = "View report",
                            tint = colors.onSurfaceVariant
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
                            tint = colors.onSurfaceVariant,
                            modifier = Modifier.size(40.dp)
                        )

                        Spacer(Modifier.height(10.dp))

                        Text(
                            text = "No reports found",
                            fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold,
                            color = colors.onSurface
                        )

                        Text(
                            text = "Try another search or filter.",
                            color = colors.onSurfaceVariant,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
    }
}
