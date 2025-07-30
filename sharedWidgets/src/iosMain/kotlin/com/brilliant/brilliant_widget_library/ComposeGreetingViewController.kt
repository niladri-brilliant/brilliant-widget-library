package com.brilliant.brilliant_widget_library

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController

fun ComposeGreetingViewController(name: String): UIViewController =
    ComposeUIViewController { GreetingView(name) }