package com.imthiyas.usermanagementapp.ui.screens

import android.transition.Slide
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imthiyas.usermanagementapp.ui.components.AppTopBar
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposePlaygroundScreen(onBackClick: () -> Unit) {

    Scaffold(topBar = { AppTopBar("PlayGround", onBackClick = { onBackClick }) }) { innerPadding ->


        var notificationEnabled by rememberSaveable() {
            mutableStateOf(false)
        }

        var termsAccepted by rememberSaveable() {
            mutableStateOf(false)
        }

        var selectGender by rememberSaveable() {
            mutableStateOf("Male")
        }

        var selectedCountry by rememberSaveable() {
            mutableStateOf("India")
        }

        var expanded by rememberSaveable() {
            mutableStateOf(false)
        }

        val countries = listOf(
            "India", "USA", "Canada",
            "Australia",
            "UK"
        )

        var sliderValue by rememberSaveable() {
            mutableStateOf(50f)
        }

        var showSnackbar by rememberSaveable() {
            mutableStateOf(false)
        }

        val snackbarHostState = remember {
            SnackbarHostState()
        }

        val scope = rememberCoroutineScope()



        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        )

        {


            Row(

                horizontalArrangement = Arrangement.SpaceBetween
            )

            {

                Text(text = "Notifications", modifier = Modifier.weight(1f))

                Switch(
                    modifier = Modifier.weight(1f),
                    checked = notificationEnabled,
                    onCheckedChange = {
                        notificationEnabled = it
                    })
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = termsAccepted,
                    onCheckedChange = { termsAccepted = it })
                Text(text = "I accept the term")
            }

            Text(text = "Gender")
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = selectGender == "Male", onClick = { selectGender = "Male" })
                Text(text = "Male")

                RadioButton(
                    selected = selectGender == "Female",
                    onClick = { selectGender = "Female" })
                Text(text = "Female")

            }

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded })

            {

                OutlinedTextField(
                    value = selectedCountry,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text(text = "Country") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    }
                ) {
                    countries.forEach { country ->
                        DropdownMenuItem(
                            text = { Text(text = country) },
                            onClick = {
                                selectedCountry = country
                                expanded = false
                            }
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            )

            {

                Text(text = " ${sliderValue.toInt()}")


                Spacer(
                    modifier = Modifier.width(16.dp)
                )
                Slider(
                    value = sliderValue,
                    onValueChange = {
                        sliderValue = it
                    },
                    valueRange = 0f..100f
                )


            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            )

            {

                LinearProgressIndicator(
                    modifier = Modifier.padding(top = 16.dp),
                    progress = sliderValue / 100f
                )

                Spacer(
                    modifier = Modifier.width(16.dp)
                )

                CircularProgressIndicator(progress = sliderValue / 100f)

            }


            Button(
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Saved successfully"
                        )
                    }
                }
            )

            {
                Text("Show Snackbar")
            }


            

        }


    }
}