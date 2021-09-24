package com.example.piston

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room
import com.example.data.*
import com.example.piston.ui.theme.ReadingPage
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext


class MainActivity : ComponentActivity() {
    val viewModel: ViewModel by viewModels()



    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getListFromDb()

        setContent {
            Ui()
        }

    }


    @ExperimentalPagerApi
    @Composable
    fun Ui() {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                BottomNavigation {
                    val navBackStackEntry by navController
                        .currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    Constants.BottomNavigationItems.forEach { screen ->
                        BottomNavigationItem(
                            icon = { Icon(screen.icon, contentDescription = null) },
                            label = { Text(screen.title) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {

                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }

                                    launchSingleTop = true

                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        ) {
            NavHost(navController = navController, startDestination = Screen.Home.route) {
                composable(Screen.Home.route) { Home(navController) }
                composable(Screen.Lessons.route) { Lessons(navController) }
                composable(Screen.Quizes.route) { Quizes() }
                composable(Screen.More.route) { More(navController) }
                composable(
                    "Lessons_menu/{courses_name}",
                    arguments = listOf(navArgument("courses_name") {
                        type = NavType.StringType
                    })
                ) { navBackStackEntry ->
                    PracticalLesson(
                        navController,
                        navBackStackEntry.arguments?.getString("courses_name"),
                        viewModel.list
                    )
                }
                composable(
                    "reading_page/{index}",
                    arguments = listOf(navArgument("index") {
                        type = NavType.IntType
                    })
                ) {

                    ReadingPage(navController, it.arguments!!.getInt("index"), viewModel.list)
                }
            }
        }
    }

    @Composable
    fun Home(navController: NavController) {
        Text(text = "home")
    }

    @Composable
    fun Quizes() {
        Text(text = "quizes")
    }


}










