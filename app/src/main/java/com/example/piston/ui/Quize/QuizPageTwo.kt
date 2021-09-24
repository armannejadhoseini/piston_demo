package com.example.piston.ui.Quize

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.piston.AutoSizeText
import com.example.piston.Manager
import com.example.piston.ui.theme.textColor
import com.google.accompanist.pager.ExperimentalPagerApi

object PagesTow {
    var listPage = "listPage"
    var examPage = "examPage"
}


@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun QuizPageTwo(navController: NavHostController) {
    var navController2 = rememberNavController()
    NavHost(navController = navController2, startDestination = PagesTow.listPage){
        composable(PagesTow.listPage){
            QuizList(navController2)
        }
        composable(PagesTow.examPage){
            Manager()
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun QuizList(navController2: NavHostController) {
    LazyVerticalGrid(cells = GridCells.Fixed(2), contentPadding = PaddingValues(8.dp),modifier = Modifier
        .fillMaxSize()
        .background(
            color = colorResource(
                id = com.example.piston.R.color.layout_background
            )
        )) {
        items(20) { index ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .padding(4.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .clickable { navController2.navigate(PagesTow.examPage)}) {
                    AutoSizeText(
                        text =   "آزمون مقدماتی :" + index.toString(),
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .weight(2f)
                            .align(CenterHorizontally),
                        color = textColor
                    ){
                        it.setLines(1)
                    }
                    AutoSizeText(
                        text =  "آخرین تلاش شما" ,
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .weight(1f)
                            .align(CenterHorizontally),
                        color = textColor
                    ){
                        it.setLines(1)
                    }
                    AutoSizeText(
                        text =  "0%" ,
                        modifier = Modifier
                            .fillMaxWidth(0.2f)
                            .weight(1f)
                            .align(CenterHorizontally),
                        color = textColor
                    ){
                        it.setLines(1)
                    }
                }
            }
        }
    }
}