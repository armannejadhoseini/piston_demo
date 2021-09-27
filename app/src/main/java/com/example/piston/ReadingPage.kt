package com.example.piston.ui.theme

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.data.Constants
import com.example.myapplication.domain.LectureList
import com.example.piston.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.landscapist.glide.GlideImage


@ExperimentalPagerApi
@Composable
fun ReadingPage(navController: NavController, index: Int, list: List<LectureList>) {

    HorizontalPager(state = rememberPagerState(pageCount = 8)) {
        val page = this.currentPage
        Log.d("TAG", "ReadingPage: $page")
        Column() {
            PageHeader(navController, page)
            if (page == 3 || page == 6) {

                QuestionTab(index, list)

            } else {
                LectureTab(index, list, page)
            }
        }

    }
}


@Composable
fun PageHeader(navController: NavController, page: Int) {
    Row(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(
            modifier = Modifier
                .width(30.dp)
                .height(30.dp),
            onClick = {
                navController.navigateUp()
            },

            ) {
            Icon(
                modifier = Modifier
                    .width(20.dp)
                    .padding(start = 2.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(color = colorResource(id = R.color.courcesBlue))
                    .height(20.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                contentDescription = "",
                tint = Color.Unspecified
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(0.9F)
                .padding(8.dp)
                .height(20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            var color: Color = colorResource(id = R.color.isDoneGreen)
            var size: Dp
            Constants.reading_list.forEach { index ->
                if (page + 1 == index) {
                    color = colorResource(id = R.color.layout_background)
                    size = 20.dp
                } else {
                    size = 15.dp
                }

                Button(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .padding(start = 7.dp)
                        .width(size)
                        .height(size),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(color),
                )
                {
                    Text(
                        modifier = Modifier
                            .width(15.dp)
                            .height(15.dp),
                        text = "$index",
                        color = colorResource(id = R.color.white)
                    )
                }
            }
        }
        if (page == 7) {
            IconButton(
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp),
                onClick = {
                    navController.navigateUp()
                },

                ) {
                Icon(
                    modifier = Modifier
                        .width(20.dp)
                        .padding(start = 2.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(color = colorResource(id = R.color.courcesBlue))
                        .height(20.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_check),
                    contentDescription = "",
                    tint = Color.Unspecified
                )

            }

        } else {
            Spacer(
                modifier = Modifier
                    .height(30.dp)
                    .width(30.dp)
            )
        }
    }
}

@Composable
fun LectureTab(index: Int, list: List<LectureList>, page: Int) {
    Log.d("TAG", "LectureTab: $page")
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        GlideImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            imageModel = list[index].image,
            contentScale = ContentScale.Inside
        )

        Row(modifier = Modifier.padding(vertical = 20.dp)) {
            IconButton(
                modifier = Modifier
                    .height(30.dp)
                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                    .width(60.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(colorResource(id = R.color.recyclerEdgeBlueColor)),
                onClick = { /*TODO*/ },
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .width(40.dp),
                        text = "افزودن",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_unsave),
                        contentDescription = "",
                        tint = Color.Unspecified
                    )

                }

            }
            Text(
                modifier = Modifier
                    .fillMaxWidth(1.0F)
                    .padding(end = 10.dp)
                    .height(30.dp),
                text = list[index].title,
                textAlign = TextAlign.End,
                fontSize = 19.sp,
                color = colorResource(id = R.color.textColors),
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState(0))
                .padding(end = 10.dp)
                .fillMaxHeight(1.0F),
            text = when (page) {
                0 -> list[index].page1
                1 -> list[index].page2
                2 -> list[index].page3
                4 -> list[index].page4
                5 -> list[index].page5
                7 -> list[index].page6

                else -> list[index].page1
            },
            textAlign = TextAlign.End,
            fontSize = 15.sp,
            color = colorResource(id = R.color.textColors)
        )
    }
}


@Composable
fun QuestionTab(index: Int, list: List<LectureList>) {


    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.End) {
        GlideImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            imageModel = list[index].image,
            contentScale = ContentScale.Inside
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 30.dp),
            text = stringResource(id = R.string.ResourceTitle_txt),
            color = colorResource(id = R.color.textColors),
            fontSize = 15.sp
        )

        listOf(1, 2, 3, 4).forEach { _ ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 4.dp, end = 20.dp, bottom = 4.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                elevation = 4.dp
            ) {
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.padding(end = 4.dp),
                        text = "this dummy data",
                        color = colorResource(
                            id = R.color.textColors
                        )
                    )
                    RadioButton(
                        modifier = Modifier.padding(end = 4.dp),
                        selected = false,
                        onClick = { /*TODO*/ })
                }
            }
        }
    }

}






