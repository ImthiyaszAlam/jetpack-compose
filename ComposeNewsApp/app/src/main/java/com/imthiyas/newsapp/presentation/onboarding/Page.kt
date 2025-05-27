package com.imthiyas.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.imthiyas.newsapp.R

data class Page(
    val title: String? = null,
    val description: String? = null,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Breaking: New Policy Announced",
        description = "The government unveils a new initiative aimed at boosting the economy.",
        image = R.drawable.image1
    ),

    Page(
        title = "Tech Giants Launch AI Partnership",
        description = "Major companies collaborate to create next-gen artificial intelligence tools.",
        image = R.drawable.image2
    ),
    Page(
        title = "City Park to Undergo Renovation",
        description = "Officials approve a plan to revamp the central park with new features.",
        image = R.drawable.image3
    ),
    Page(
        title = "Welcome",
        description = "Officials approve a plan to revamp the central park with new features.",
        image = R.drawable.image4

    )


)