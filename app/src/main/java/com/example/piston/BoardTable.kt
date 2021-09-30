package com.example.piston

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.data.Constants
import com.example.myapplication.domain.BoardList
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@ExperimentalFoundationApi
@Composable
fun BoardTable(list: List<BoardList>, navController: NavController) {
    var boardList by remember {
        mutableStateOf(list)
    }
    var topNav by remember {
        mutableStateOf(2)
    }
    Column() {
        Row(
            modifier = Modifier
                .height(40.dp)
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
                    .fillMaxWidth()
                    .height(40.dp),
                horizontalArrangement = Arrangement.End
            ) {

                if (topNav == 1) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)) {

                        DropdownMenu(
                            modifier = Modifier
                                .fillMaxWidth(0.9F)
                                .height(30.dp),
                            expanded = false,
                            onDismissRequest = { /*TODO*/ }) {
                            Constants.dropDownItems.forEach { item ->
                                DropdownMenuItem(onClick = { /*TODO*/ }) {
                                    Text(text = item)
                                }
                            }
                        }
                        IconButton(
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp),
                            onClick = {
                                topNav = 2
                            },
                        ) {
                            Icon(
                                modifier = Modifier
                                    .width(20.dp)
                                    .padding(start = 2.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(color = colorResource(id = R.color.courcesBlue))
                                    .height(20.dp),
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_close),
                                contentDescription = "",
                                tint = Color.Unspecified
                            )
                        }
                    }
                }
                if (topNav == 2) {
                    var text_input by remember {
                        mutableStateOf("  ")
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp)
                    ) {

                        BasicTextField(
                            modifier = Modifier
                                .fillMaxWidth(0.9F)
                                .height(30.dp)
                                .padding(end = 2.dp, top = 2.dp),
                            value = text_input,
                            onValueChange = {
                                text_input = it
                                boardList = dbTask(text_input, list)
                            },
                            cursorBrush = SolidColor(colorResource(id = R.color.textColor_deep_blue)),
                            decorationBox = { innerTextField ->
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(15.dp))
                                        .border(
                                            1.dp, colorResource(id = R.color.light_gray),
                                            RoundedCornerShape(15.dp)
                                        ),
                                    contentAlignment = Alignment.CenterStart,
                                ) {

                                    innerTextField()
                                }
                            }

                        )
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
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_close),
                                contentDescription = "",
                                tint = Color.Unspecified
                            )
                        }
                    }

                }
            }
        }


        LazyVerticalGrid(cells = GridCells.Adaptive(128.dp)) {
            items(boardList.size) { item ->
                BoardItem(list[item])
            }
        }
    }
}


@Composable
fun BoardItem(item: BoardList) {


    Column() {
        GlideImage(
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .padding(10.dp),
            imageModel = item.image
        )
        Text(
            modifier = Modifier
                .width(100.dp)
                .height(20.dp)
                .padding(horizontal = 10.dp),
            text = item.title,
            color = colorResource(id = R.color.textColor_deep_blue),
            fontSize = 10.sp
        )
    }
}

fun dbTask(text: String, list: List<BoardList>): List<BoardList> {
    var matchedItems = mutableListOf<BoardList>()
    list.forEach { item ->
        if (item.title.contains(text)) {
            matchedItems.add(item)
        }
    }
    return matchedItems
}