package com.imthiyas.photoviewingapp

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage


@Composable
fun PhotoScreen(modifier: Modifier = Modifier,viewModel: PhotoViewModel = viewModel()) {

    val photos by viewModel.photos.collectAsState()

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(photos) { uri ->

            AsyncImage(
                model = uri,
                contentDescription = "Photo",
                modifier = modifier
                    .fillMaxWidth()
                    .height(screenHeight / 2),
                contentScale = ContentScale.Crop
            )
        }
    }
}