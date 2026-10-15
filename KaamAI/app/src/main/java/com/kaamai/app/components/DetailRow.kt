package com.kaamai.app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetailRow(
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
                    RoundedCornerShape(50.dp)
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
                    shape = RoundedCornerShape(50.dp),
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