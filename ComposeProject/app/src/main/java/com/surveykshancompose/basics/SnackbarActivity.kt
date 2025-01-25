package com.surveykshancompose.basics

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class SnackbarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val snackbarHostState = remember { SnackbarHostState() }

            val scope = rememberCoroutineScope()

            var textFieldState by remember {
                mutableStateOf("")
            }

            Scaffold(modifier = Modifier.fillMaxSize(),
                snackbarHost = { SnackbarHost(hostState = snackbarHostState) } // Attach the SnackbarHost
            ) { paddingValues ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(horizontal = 10.dp)
                ) {


                    TextField(
                        value = textFieldState,
                        label = {
                            Text(text = "Enter Your Name")
                        },
                        onValueChange = {
                            textFieldState = it
                        },
                        singleLine = true,
                        modifier = Modifier
                            .height(50.dp)

                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Hello $textFieldState")
                        }

                    }) {
                        Text(text = "Please Greet Me")
                    }

                }
            }
        }
    }
}
