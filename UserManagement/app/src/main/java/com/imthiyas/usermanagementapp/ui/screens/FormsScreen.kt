package com.imthiyas.usermanagementapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.imthiyas.usermanagementapp.ui.components.AppTopBar


@Composable
fun FormsScreen(onBackClick: () -> Unit) {


    var name by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }


    var phone by remember {
        mutableStateOf("")
    }


    Scaffold(topBar = { AppTopBar("Add User", onBackClick) }) { innerPadding ->


        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {

            Spacer(modifier = Modifier.height(16.dp))


            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                },
                label = {
                    Text("Name")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )


            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = {
                    Text(text = "Email")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            OutlinedTextField(
                value = phone,
                onValueChange = {
                    phone = it
                },
                label = {
                    Text(text = "Phone")
                },
                modifier = Modifier.fillMaxWidth()
            )


            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Button(
                onClick = {
                    // validation later
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit")
            }



            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Button(
                onClick = onBackClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Back")
            }


        }

    }

}