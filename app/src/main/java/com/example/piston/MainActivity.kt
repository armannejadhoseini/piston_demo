package com.example.piston

import android.os.Bundle
import android.util.Log
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
import com.example.piston.presenter.Home
import com.example.piston.ui.Quize.QuizPage
import com.example.piston.ui.theme.ReadingPage
import com.google.accompanist.pager.ExperimentalPagerApi


class MainActivity : ComponentActivity() {
    private val viewModel: ViewModel by viewModels()


    @ExperimentalFoundationApi
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getTheoryListFromDb()
        viewModel.getPracticalListFromDb()

        val viewModelRetrofit: ViewModelRetrofit by viewModels()
        viewModelRetrofit.verifyCode("09106967480", "87793", "shr")
        viewModelRetrofit.invalidCode.observe(this) {
            Log.i("TAG000", "onCreate: $it")
        }

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
                composable(Screen.Home.route) { Home(navController) }
                composable(Screen.Lessons.route) { Lessons(navController) }
                composable(Screen.Quizes.route) { Quizes() }
                composable(Screen.More.route) { more(navController) }
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
                composable(Screen.SignIn.route) {
                    SignIn(navController)
                }
                composable(Screen.Profile.route) {
                    Profile(navController)
                }

                composable("SignInBack") {
                    more(navController)

                }

                composable(
                    route = Screen.SignVerifyCode.route + "/{fullName}/{phone}",
                    arguments = listOf(navArgument("fullName") {
                        type = NavType.StringType
                    },
                        navArgument("phone") {
                            type = NavType.StringType
                        })
//                        //, (navArgument("LifecycleOwner") {
//                            type = NavType.StringType
//                        })

                ) { navBackStackEntry ->
                    SignVerifyCode(
                        navController,
                        fullNameInputed = navBackStackEntry.arguments?.getString("fullName"),
                        phoneInputed = navBackStackEntry.arguments?.getString("phone"), ""
                    )
                }
                composable(
                    route = Screen.SignInvalidCode.route + "/{fullName}/{phone}/{code}",
                    arguments = listOf(navArgument("fullName") {
                        type = NavType.StringType
                    },
                        navArgument("phone") {
                            type = NavType.StringType
                        }, (navArgument("code") {
                            type = NavType.StringType
                        })
                    )

                ) { navBackStackEntry ->
                    SignInvalidCode(
                        navController,
                        fullNameInputed = navBackStackEntry.arguments?.getString("fullName"),
                        phoneInputed = navBackStackEntry.arguments?.getString("phone"),
                        codeInputed = navBackStackEntry.arguments?.getString("code")
                    )
                }


            }
        }
    }
}


@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun Quizes() {
    QuizPage()
}















