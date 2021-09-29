package com.example.piston.ui.Quize

import android.view.Gravity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.piston.AutoSizeText
import com.example.piston.R
import com.example.piston.ViewModel
import com.example.piston.ui.theme.textColor
import com.google.accompanist.pager.ExperimentalPagerApi

sealed class QuizPage(val name:String){
    object PageOne:QuizPage("pageOne")
    object PageTwo:QuizPage("pageTwo")
}
@Composable
fun QuizPageFirst(navController: NavHostController, viewModel: ViewModel) {
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
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.8f)
                .padding(4.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .clickable { navController.navigate(QuizPage.PageTwo.name) }){
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
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.8f)
                .padding(4.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .clickable { }){
                Row(modifier = Modifier.fillMaxSize()) {
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
}

@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun QuizPage(navController: NavHostController, viewModel: ViewModel) {
    var navController = rememberNavController()
    NavHost(navController = navController,startDestination = QuizPage.PageOne.name){
        composable(route = QuizPage.PageOne.name){
            QuizPageFirst(navController,viewModel)
        }
        composable(route = QuizPage.PageTwo.name){
            QuizPageTwo(navController,viewModel)
        }
    }
}