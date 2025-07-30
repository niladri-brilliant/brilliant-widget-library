package com.brilliant.brilliant_widget_library

import androidx.compose.runtime.Composable
import androidx.compose.material.Text

@Composable
public fun GreetingView(name: String) {
    Text("Howdy, $name!")
}