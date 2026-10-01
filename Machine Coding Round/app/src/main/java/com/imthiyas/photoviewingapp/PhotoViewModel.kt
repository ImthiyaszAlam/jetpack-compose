package com.imthiyas.photoviewingapp

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PhotoViewModel: ViewModel() {

    val _photos = MutableStateFlow<List<Uri>>(emptyList())
    val photos : StateFlow<List<Uri>> = _photos

    fun loadPhotos(context: Context) {

        val imageList = mutableListOf<Uri>()

        val collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf(
            MediaStore.Images.Media._ID
        )

        context.contentResolver.query(
            collection,
            projection,
            null,
            null,
            "${MediaStore.Images.Media.DATE_ADDED} DESC"
        )?.use { cursor ->

            val idColumn =
                cursor.getColumnIndexOrThrow(
                    MediaStore.Images.Media._ID
                )

            while (cursor.moveToNext()) {

                val id = cursor.getLong(idColumn)

                val uri = ContentUris.withAppendedId(
                    collection,
                    id
                )

                imageList.add(uri)
            }
        }

        _photos.value = imageList
    }

}