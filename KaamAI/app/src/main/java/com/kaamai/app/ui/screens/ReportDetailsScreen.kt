package com.kaamai.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaamai.app.components.DetailRow

@Composable
fun ReportDetailsScreen(
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
            .imePadding()
            .background(colors.background)
            .verticalScroll(rememberScrollState())
            .padding(all = 16.dp)
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = colors.surface
            ),
            elevation = CardDefaults.cardElevation(1.dp)
        )

        {
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

        Spacer(Modifier.height(8.dp))

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
        )

        {
            Button(
                onClick = onSaveClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(50.dp),
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
                shape = RoundedCornerShape(50.dp)
            ) {
                Text(
                    if (isEditing) "Finish Editing" else "Edit Details",
                    color = colors.primary
                )
            }
        }


    }
}