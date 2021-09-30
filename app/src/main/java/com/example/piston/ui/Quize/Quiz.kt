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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.domain.model.TestModel
import com.example.myapplication.domain.model.TestPercentEntity
import com.example.piston.AutoSizeText
import com.example.piston.ExamTestPage
import com.example.piston.R
import com.example.piston.ViewModel
import com.example.piston.ui.Quize.ExamQuizPages.AdvanceTestListName
import com.example.piston.ui.Quize.ExamQuizPages.AdvanceTestsName
import com.example.piston.ui.Quize.ExamQuizPages.ElementaryTestListName
import com.example.piston.ui.Quize.ExamQuizPages.ElementaryTestsName
import com.example.piston.ui.Quize.ExamQuizPages.FirstTestPageName
import com.example.piston.ui.theme.textColor
import com.google.accompanist.pager.ExperimentalPagerApi
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

@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun ExamQuizPageManger() {
    var navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = FirstTestPageName) {
        composable(route = FirstTestPageName){
            FirstTestPage(navController)
        }
        composable(route = ElementaryTestListName){
            ElementaryTestList {
                navController.navigate("$ElementaryTestsName/$it")
            }
        }
        composable(route = AdvanceTestListName){
            AdvancedTestList {
                navController.navigate("$AdvanceTestsName/$it")
            }
        }
        composable(route = "$ElementaryTestsName/{number}",
        arguments = listOf(navArgument("number"){
            type = NavType.IntType
        })){
            ElementaryTestsPage(navController,it.arguments?.getInt("number")!!)
        }
        composable(AdvanceTestsName){

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


@ExperimentalFoundationApi
@Composable
fun TestListLayout(list: ArrayList<TestPercentEntity>, onPageChange: (Int) -> Unit) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2), contentPadding = PaddingValues(8.dp), modifier = Modifier
            .fillMaxSize()
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
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun AdvancedTestList(onPageChange: (Int) -> Unit) {
    var list by remember {
        mutableStateOf(ArrayList<TestPercentEntity>())
    }
    var viewModel = viewModel(ViewModel::class.java)
    LaunchedEffect(key1 = "start") {
        launch(Dispatchers.IO){
            list = viewModel.getQuizPercentListFromDb() as ArrayList<TestPercentEntity>
        }
    }
    TestListLayout(list, onPageChange)
}

@ExperimentalFoundationApi
@Composable
fun ElementaryTestList(onPageChange: (Int) -> Unit) {
    var list by remember {
        mutableStateOf(ArrayList<TestPercentEntity>())
    }
    var viewModel = viewModel(ViewModel::class.java)
    LaunchedEffect(key1 = "start") {
        this.launch(Dispatchers.IO){
            list = viewModel.getExamPercentListFromDb() as ArrayList<TestPercentEntity>
        }
    }
    TestListLayout(list, onPageChange)
}

@ExperimentalPagerApi
@Composable
fun ElementaryTestsPage(navController: NavHostController, number: Int) {
    var list by remember{
        mutableStateOf(ArrayList<TestModel>())
    }
    var viewModel = viewModel(ViewModel::class.java)
    LaunchedEffect(key1 = "start"){
        this.launch(Dispatchers.IO){
            list = viewModel.getExamListFromDb(number) as ArrayList<TestModel>
        }
    }
    ExamTestPage(navController,list)
}

@Composable
fun AdvancedTestsPage() {

}

@Composable
fun ElementaryTestResult() {
}

@Composable
fun AdvancedTestResult() {
}
