package com.example.piston.ui.theme

import android.graphics.BitmapFactory
import android.graphics.Paint
import android.util.Log
import android.widget.HorizontalScrollView
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PixelMap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.data.lecture_items
import com.example.piston.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@Composable
fun ReadingPage(navController: NavController, index: Int, list: List<lecture_items>) {

    HorizontalPager(state = rememberPagerState(pageCount = 6)) {
        val page = this.currentPage
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.End, modifier = Modifier.padding(bottom = 20.dp)
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
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                        .padding(end = 3.dp, top = 3.dp),
                    textAlign = TextAlign.End,
                    text = list[index].title,
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.textColor_deep_blue)
                )
            }
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painter = painterResource(id = R.drawable.guidline),
                contentDescription = ""
            )
            Row() {
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
                    color = colorResource(id = R.color.textColor_deep_blue),
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState(0))
                    .padding(end = 10.dp)
                    .fillMaxHeight(1.0F), text = when (page) {
                        1 -> list[index].page1
                        2 -> list[index].page2
                        3 -> list[index].page3
                        4 -> list[index].page4
                        5 -> list[index].page5
                        6 -> list[index].page6

                    else -> list[index].page1
                },
                textAlign = TextAlign.End,
                fontSize = 15.sp,
                color = colorResource(id = R.color.textColor_deep_blue)
            )
        }
    }
}

