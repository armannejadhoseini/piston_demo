package com.example.piston

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi

object Pages {
    const val questionPage = "questionPage"
    const val questionResultPage = "questionResultPage"
}
//@ExperimentalPagerApi
//@Composable
//fun Manager(index: Int?) {
//    var navController = rememberNavController()
//    NavHost(
//        navController = navController,
//        startDestination = "questionPage",
//        modifier = Modifier.fillMaxSize()
//    ) {
//        composable(Pages.questionPage) {
//            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr ) {
//                ExamTestPage(navController,)
//            }
//        }
//        composable(Pages.questionResultPage) {
//            ExamResultPage(navController)
//        }
//    }
//}
