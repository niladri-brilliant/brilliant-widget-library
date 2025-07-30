package com.brilliant.brilliant_widget_library

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform