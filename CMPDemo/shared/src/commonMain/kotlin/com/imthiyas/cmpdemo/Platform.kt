package com.imthiyas.cmpdemo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform