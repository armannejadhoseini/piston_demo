package com.example.piston.ui.Quize

import android.view.Gravity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.domain.model.QuizModel
import com.example.myapplication.domain.model.TestPercentEntity
import com.example.piston.*
import com.example.piston.R
import com.example.piston.ui.Quize.ExamQuizPages.AdvanceTestListName
import com.example.piston.ui.Quize.ExamQuizPages.AdvanceTestsName
import com.example.piston.ui.Quize.ExamQuizPages.ElementaryResultName
import com.example.piston.ui.Quize.ExamQuizPages.ElementaryTestListName
import com.example.piston.ui.Quize.ExamQuizPages.ElementaryTestsName
import com.example.piston.ui.Quize.ExamQuizPages.FirstTestPageName
import com.example.piston.ui.theme.textColor
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object ExamQuizPages {
    var FirstTestPageName = "first_test_page"
    var ElementaryTestListName = "elementary_list_page"
    var ElementaryTestsName = "elementary_test_page"
    var ElementaryResultName = "elementary_result_page"
    var AdvanceTestListName = "advanced_page_list"
    var AdvanceTestsName = "advanced_test_page"
    var AdvanceResultName = "advanced_result_page"
}

data class QuizResult(var answers: List<Int>, var quizList: List<QuizModel>)

@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun QuizPageManger(showBottom: (Boolean) -> Unit) {
    var navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    if (navBackStackEntry?.destination?.route != FirstTestPageName) showBottom(false)
    else showBottom(true)
    NavHost(
        navController = navController,
        startDestination = FirstTestPageName
    ) {
        composable(route = FirstTestPageName) {
            FirstTestPage(navController)
        }
        composable(route = ElementaryTestListName) {
            ElementaryTestList(navController) {
                navController.navigate("$ElementaryTestsName/$it")
            }
        }
        composable(route = AdvanceTestListName) {
            AdvancedTestList(navController) {
                navController.navigate("$AdvanceTestsName/$it")
            }
        }
        composable(
            route = "$ElementaryTestsName/{number}",
            arguments = listOf(navArgument("number") {
                type = NavType.IntType
            })
        ) {
            ElementaryTestsPage(navController, it.arguments?.getInt("number")!!)
        }
        composable(AdvanceTestsName) {

        }
        composable(
            route = "$ElementaryResultName/{result}",
            arguments = listOf(navArgument("result") {
                type = NavType.StringType
            })
        ) {
            var resultString = it.arguments?.getString("result", "") ?: ""
            var gson = Gson()
            var type = object : TypeToken<QuizResult>() {}.type
            var quizResult = gson.fromJson<QuizResult>(resultString, type)
            ElementaryTestResult(navController, quizResult)
        }
    }
}

@Composable
fun FirstTestPage(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .background(color = colorResource(id = R.color.layout_background))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .aspectRatio(4f)
        ) {
            AutoSizeText(
                text = stringResource(id = R.string.BaseTestTitle),
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f)
                    .align(
                        Alignment.Center
                    ),
                color = textColor,
                androidText = {
                    it.setLines(1)
                }
            )
        }
        ElementaryTestBanner {
            navController.navigate(route = ExamQuizPages.ElementaryTestListName)
        }
        AdvancedTestBanner {
            navController.navigate(route = ExamQuizPages.AdvanceTestListName)
        }
    }
}

@Composable
fun ElementaryTestBanner(onPageChange: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.8f)
            .padding(4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .clickable {
                onPageChange()
            }) {
            Row(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.base_test_img),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(2.5f),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(3f)
                        .padding(8.dp)
                ) {
                    AutoSizeText(
                        text = "آزمون مقدماتی",
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1.5f),
                        color = textColor,
                        gravity = Gravity.START or Gravity.CENTER_VERTICAL
                    )
                    AutoSizeText(
                        text = stringResource(id = R.string.course_quiz_body_text),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(5f),
                        color = textColor,
                        gravity = Gravity.START or Gravity.CENTER_VERTICAL
                    )
                }
            }
        }

    }
}

@Composable
fun AdvancedTestBanner(onPageChange: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.8f)
            .padding(4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .clickable { }) {
            Row(modifier = Modifier
                .fillMaxSize()
                .clickable {
                    onPageChange()
                }) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(3f)
                        .padding(8.dp)
                ) {
                    AutoSizeText(
                        text = stringResource(id = R.string.ExamTestTitle),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1.5f),
                        color = textColor,
                        gravity = Gravity.START or Gravity.CENTER_VERTICAL
                    )
                    AutoSizeText(
                        text = stringResource(id = R.string.course_exam_body_string),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(5f),
                        color = textColor,
                        gravity = Gravity.START or Gravity.CENTER_VERTICAL
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.test_img),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(2.5f),
                    contentScale = ContentScale.Crop
                )

            }
        }

    }
}

@Composable
fun TopBar(modifier: Modifier, text: String, onBackPress: () -> Unit) {
    Row(modifier = modifier.fillMaxWidth()) {
        ImageIcon(
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.CenterVertically),
            backColor = Color.Blue,
            image = R.drawable.ic_back,
            clickable = {
                onBackPress()
            }
        )
        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        )
        AutoSizeText(
            text = text,
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .weight(2f),
            color = textColor
        )

    }
}

@ExperimentalFoundationApi
@Composable
fun TestListLayout(
    list: List<TestPercentEntity>,
    onPageChange: (Int) -> Unit,
    onBackPress: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(4.dp), text = stringResource(id = R.string.ExamListTitle_txt)
        ) {
            onBackPress()
        }
        LazyVerticalGrid(
            cells = GridCells.Fixed(2), contentPadding = PaddingValues(8.dp), modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(
                    color = colorResource(
                        id = R.color.layout_background
                    )
                )
        ) {
            list.forEachIndexed { index, item ->
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .padding(4.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                onPageChange(item.id)
                            }) {
                            AutoSizeText(
                                text = "آزمون مقدماتی :" + (index + 1).toString(),
                                modifier = Modifier
                                    .fillMaxWidth(0.7f)
                                    .weight(2f)
                                    .align(Alignment.CenterHorizontally),
                                color = textColor
                            ) {
                                it.setLines(1)
                            }
                            AutoSizeText(
                                text = "آخرین تلاش شما",
                                modifier = Modifier
                                    .fillMaxWidth(0.7f)
                                    .weight(1f)
                                    .align(Alignment.CenterHorizontally),
                                color = textColor
                            ) {
                                it.setLines(1)
                            }
                            AutoSizeText(
                                text = "${item.percent}%",
                                modifier = Modifier
                                    .fillMaxWidth(0.2f)
                                    .weight(1f)
                                    .align(Alignment.CenterHorizontally),
                                color = textColor
                            ) {
                                it.setLines(1)
                            }
                            LinearProgressIndicator(
                                progress = (item.percent.toFloat() / 100f).coerceIn(0f, 1f),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(0.4f),
                                color = colorResource(
                                    id = R.color.courcesBlue
                                )
                            )
                        }
                    }
                }
            }
        }
    }

}

@ExperimentalFoundationApi
@Composable
fun AdvancedTestList(
    navController: NavHostController,
    viewModel: ViewModel = viewModel(),
    onPageChange: (Int) -> Unit
) {
    val list by viewModel.examPercentList.collectAsState(initial = null, Dispatchers.IO)
    list?.let {
        TestListLayout(it, onPageChange, onBackPress = {

        })
    }
}

@ExperimentalFoundationApi
@Composable
fun ElementaryTestList(
    navController: NavHostController,
    viewModel: ViewModel = viewModel(),
    onPageChange: (Int) -> Unit
) {
    val list by viewModel.examPercentList.collectAsState(initial = null, Dispatchers.IO)
    list?.let {
        TestListLayout(it, onPageChange, onBackPress = {
            navController.popBackStack()
        })
    }
}

@ExperimentalPagerApi
@Composable
fun ElementaryTestsPage(
    navController: NavHostController,
    number: Int,
    viewModel: ViewModel = viewModel()
) {
    var list = viewModel.examList(number)
        .collectAsState(initial = null, context = Dispatchers.IO)
    list.value?.let {
        ExamTestPage(navController, it)
    }
}

@Composable
fun AdvancedTestsPage() {

}

@Composable
fun ElementaryTestResult(navController: NavHostController, quizResult: QuizResult) {
    var correctAnswerCount = 0
    var answers = quizResult.answers
    var testList = quizResult.quizList
    answers.forEachIndexed { index, item ->
        if (testList[index].true_answer == item) {
            correctAnswerCount++
        }
    }
    var percent = correctAnswerCount / answers.size.toFloat()
    percent *= 100
    var viewModel = viewModel(ViewModel::class.java)

    LaunchedEffect(key1 = "start") {
        launch(Dispatchers.IO) {
            viewModel.setExamPercentOnDb(quizResult.quizList[0].test_number, percent.toInt())
        }
    }


    Box(modifier = Modifier.fillMaxSize()) {
        ExamResultPage(navController = navController, correctAnswerCount, percent)
    }
}

@Composable
fun AdvancedTestResult() {
}
