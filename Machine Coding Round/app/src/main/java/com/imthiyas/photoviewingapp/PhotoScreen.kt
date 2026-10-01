package com.imthiyas.photoviewingapp

import android.Manifest
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage


@Composable
fun PhotoScreen(modifier: Modifier = Modifier,viewModel: PhotoViewModel = viewModel()) {

    val context = LocalContext.current
    val photos by viewModel.photos.collectAsState()

    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()){
        granted->
        if (granted){
            viewModel.loadPhotos(context)
        }
    }
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    LaunchedEffect(Unit) {
        permissionLauncher.launch(
            Manifest.permission.READ_MEDIA_IMAGES
        )
    }

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