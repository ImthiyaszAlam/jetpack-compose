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
        image = R.drawable.baseline_add_24
    ),

    Page(
        title = "Tech Giants Launch AI Partnership",
        description = "Major companies collaborate to create next-gen artificial intelligence tools.",
        image = R.drawable.baseline_add_24
    ),
    Page(
        title = "City Park to Undergo Renovation",
        description = "Officials approve a plan to revamp the central park with new features.",
        image = R.drawable.baseline_add_24
    )


)