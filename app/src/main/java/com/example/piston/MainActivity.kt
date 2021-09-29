package com.example.piston

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.data.*
import com.example.piston.ui.Quize.ExamQuizPageManger
import com.example.piston.ui.Quize.FirstTestPage
import com.example.piston.ui.theme.ReadingPage
import com.google.accompanist.pager.ExperimentalPagerApi


class MainActivity : ComponentActivity() {
    private val viewModel: ViewModel by viewModels()

    @ExperimentalFoundationApi
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.initDb()

        setContent {
            Ui()
        }

    }


    @ExperimentalFoundationApi
    @ExperimentalPagerApi
    @Composable
    fun Ui() {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        Scaffold(

            bottomBar = {
                if (
                    navBackStackEntry?.destination?.route == "Home" ||
                    navBackStackEntry?.destination?.route == "Lessons" ||
                    navBackStackEntry?.destination?.route == "Quizes" ||
                    navBackStackEntry?.destination?.route == "More"
                ) {
                    BottomNavigation {
                        val currentDestination = navBackStackEntry?.destination

                        Constants.BottomNavigationItems.forEach { screen ->
                            BottomNavigationItem(
                                modifier = Modifier.background(colorResource(id = R.color.white_deep)),
                                icon = {
                                    Icon(
                                        ImageVector.vectorResource(id = screen.icon),
                                        contentDescription = null,
                                        modifier = Modifier.size(60.dp)
                                    )
                                },
                                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                onClick = {
                                    navController.navigate(screen.route) {

                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }

                                        launchSingleTop = true

                                        restoreState = true
                                    }
                                },
                                selectedContentColor = colorResource(id = R.color.light_blue),
                                unselectedContentColor = colorResource(id = R.color.light_gray)

                            )
                        }
                    }
                }
            }

        ) {
            NavHost(navController = navController, startDestination = Screen.Home.route) {
                composable(Screen.Home.route) { Home() }
                composable(Screen.Lessons.route) { Lessons(navController) }
                composable(Screen.Quizes.route) { ExamQuiz() }
                composable(Screen.More.route) { More(navController) }
                composable(
                    "Lessons_menu/{courses_name}",
                    arguments = listOf(navArgument("courses_name") {
                        type = NavType.StringType
                    })
                ) { navBackStackEntry ->
                    if (navBackStackEntry.arguments?.getString("courses_name") == "لیست دوره های تئوری") {
                        LessonsList(
                            navController,
                            navBackStackEntry.arguments?.getString("courses_name"),
                            viewModel.theory_list
                        )
                    }
                    if (navBackStackEntry.arguments?.getString("courses_name") == "لیست دوره های عملی") {
                        LessonsList(
                            navController,
                            navBackStackEntry.arguments?.getString("courses_name"),
                            viewModel.practical_list
                        )
                    }

                }
                composable(
                    "reading_page/{index}/{type}",
                    arguments = listOf(navArgument("index") {
                        type = NavType.IntType
                    },
                        navArgument("type") {
                            type = NavType.IntType
                        })
                ) {
                    if (it.arguments?.getInt("type") == 3) {
                        ReadingPage(
                            navController,
                            it.arguments!!.getInt("index"),
                            viewModel.theory_list
                        )
                    }
                    if (it.arguments?.getInt("type") == 7) {
                        ReadingPage(
                            navController,
                            it.arguments!!.getInt("index"),
                            viewModel.practical_list,
                        )
                    }

                }
            }
        }
    }

    @Composable
    fun Home() {
        Text(text = "home")
    }

    @ExperimentalFoundationApi
    @ExperimentalPagerApi
    @Composable
    fun ExamQuiz() {
        ExamQuizPageManger()
    }

}










