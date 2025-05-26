package com.imthiyas.newsapp.presentation.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.imthiyas.newsapp.R
import com.imthiyas.newsapp.presentation.Dimens.mediumPadding1
import com.imthiyas.newsapp.presentation.onboarding.Page


@Composable
fun OnboardingPage(
    modifier: Modifier = Modifier,
    page: Page
) {
    Column(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            painter = painterResource(id = page.image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(1.dp))

        page.title?.let {
            Text(
                text = it,
                modifier = Modifier.padding(horizontal = mediumPadding1),
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id = R.color.black)
            )
        }

        page.description?.let {
            Text(
                text = it,
                modifier = Modifier.padding(horizontal = mediumPadding1),
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = R.color.white)
            )

        }
    }
}