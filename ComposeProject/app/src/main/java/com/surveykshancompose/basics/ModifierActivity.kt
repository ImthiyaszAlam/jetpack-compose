package com.surveykshancompose.basics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class ModifierActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Column(
                modifier = Modifier
                    .background(Color.Yellow)
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth()
                    .padding(top = 50.dp, start = 20.dp)
                    .border(2.dp, Color.Magenta)
                    .padding(10.dp)
                    .border(2.dp, Color.Blue)
                    .padding(10.dp)
                    .border(2.dp, Color.Black)
                    .padding(10.dp)
            ) {
                Text(
                    text = "Text-1", modifier = Modifier
                        .clickable {

                        }
                        .border(2.dp, Color.Black)
                        .padding(10.dp)
                )
                Text(
                    text = "Text-2", modifier = Modifier
                        .clickable { }
                        .offset(20.dp, 20.dp)
                        .border(2.dp, Color.Black)
                        .padding(10.dp)
                )
                Text(
                    text = "Text-3", modifier = Modifier
                        .clickable { }
                        .border(2.dp, Color.Gray)
                        .padding(10.dp)
                )
            }

        }
    }
}