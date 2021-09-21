package com.example.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("Home", "Home", Icons.Filled.Home)
    object Lessons : Screen("Lessons", "Lessons", Icons.Filled.DateRange)
    object Quizes : Screen("Quizes", "Quizes", Icons.Filled.Info)
    object More : Screen("More", "More", Icons.Filled.Menu)
}
