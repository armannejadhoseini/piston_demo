package com.example.piston

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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.data.Constants
import com.example.data.Retrofit
import com.example.data.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    val viewModel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
                viewModel.setNavController(rememberNavController())
            
            Ui()

        }

    }

    @Composable
    fun Ui() {


            Scaffold(
                bottomBar = {
                    BottomNavigation {
                        val navBackStackEntry by viewModel.getNavController().currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination

                        Constants.BottomNavigationItems.forEach { screen ->
                            BottomNavigationItem(
                                icon = { Icon(screen.icon, contentDescription = null) },
                                label = { Text(screen.title) },
                                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                onClick = {
                                    viewModel.getNavController().navigate(screen.route) {

                                        popUpTo(viewModel.getNavController().graph.findStartDestination().id) {
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
                NavHost(viewModel.getNavController() , startDestination = Screen.Home.route) {
                    composable(Screen.Home.route) { Home(viewModel.getNavController()) }
                    composable(Screen.Lessons.route) { Lessons(viewModel.getNavController()) }
                    composable(Screen.Quizes.route) { Quizes() }
                    composable(Screen.More.route) { More(viewModel.getNavController()) }
                    composable("Lessons_menu") { Lessons_menu("", viewModel.getNavController()) }
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










