package com.imthiyas.quoteapp

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.google.gson.Gson
import com.imthiyas.quoteapp.models.Quote
import com.imthiyas.quoteapp.screens.Pages

object DataManager {

    var data = emptyArray<Quote>()

    var isDataLoaded = mutableStateOf(false)
   // val currentQuote = mutableStateOf(data.get(0))
    var currentPage = mutableStateOf(Pages.LISTINGS)

    fun loadAssetsFromFile(context: Context) {
        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        data = gson.fromJson(json, Array<Quote>::class.java)

        isDataLoaded.value = true

    }

    fun switchPages() {
        if (currentPage.value == Pages.LISTINGS) {
            currentPage.value = Pages.DETAILS
        } else {
            currentPage.value = Pages.LISTINGS
        }
    }
}