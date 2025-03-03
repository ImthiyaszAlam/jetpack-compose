package com.surveykshancompose.models

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.surveykshancompose.R


@Preview
@Composable
fun PreviewItem() {
    // Card1()
    BlogCategory(R.drawable.pretty_soul, "title1", "subtitle2")
}


@Composable
fun BlogCategory(image: Int, title: String, subTitle: String) {
    Card(elevation = CardDefaults.cardElevation(8.dp), modifier = Modifier.padding(16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(5.dp)
        ) {

            Image(
                painter = painterResource(id = image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .padding(2.dp)
                    .border(1.dp, Color.Blue)
                    .clip(CircleShape)
                    .weight(.2f),
                contentDescription = ""
            )
            ItemDescription(title, subTitle, modifier = Modifier.weight(.8f))

        }
    }
}

@Composable
private fun ItemDescription(title: String, subTitle: String, modifier: Modifier) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = subTitle,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Thin
        )
    }
}


@Composable
fun Card1() {

    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .border(1.dp, Color.Blue)
            .background(Color.Red)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.background(Color.Red)
        ) {
            Image(
                painter = painterResource(id = R.drawable.pretty_soul),
                contentDescription = "", modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Text(
                text = "Pretty soul",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(5.dp)

            )
        }
    }

}
