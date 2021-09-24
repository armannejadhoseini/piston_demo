package com.example.piston

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi

object Pages {
    const val questionPage = "questionPage"
    const val questionResultPage = "questionResultPage"
}
@ExperimentalPagerApi
@Composable
fun Manager() {
    var navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "questionPage",
        modifier = Modifier.fillMaxSize()
    ) {
        composable(Pages.questionPage) {
            ExamPage(navController)
        }
        composable(Pages.questionResultPage) {
            ExamResultPage(navController)
        }
    }
}