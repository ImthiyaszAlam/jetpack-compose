package com.imthiyas.usermanagementapp.model

data class Product(
    val id: Int,
    val name: String,
    val price: String,
    var isFavourite: Boolean
)
