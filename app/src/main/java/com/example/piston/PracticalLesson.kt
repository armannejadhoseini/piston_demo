package com.example.piston

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.data.Lessons_subitem
import com.example.data.lecture_items

@Composable
fun PracticalLesson(navController: NavController, item_name: String?, list: List<lecture_items>) {


    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.White) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {


                    IconButton(
                        modifier = Modifier
                            .width(30.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .height(30.dp),
                        onClick = {
                            navController.navigateUp()
                        },

                        ) {
                        Icon(
                            modifier = Modifier
                                .width(20.dp)
                                .background(color = colorResource(id = R.color.courcesBlue))
                                .height(20.dp),
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                            contentDescription = "",
                            tint = Color.Unspecified
                        )

                    }

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "$item_name",
                        textAlign = TextAlign.End,
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.textColor_deep_blue)

                    )
                }
            }
        }) {

        LazyColumn {


            items(list) { item ->
                subMenu(item.id,navController,
                    item = Lessons_subitem(
                        R.drawable.ic_silver_medal,
                        "",
                        R.drawable.ic_silver_medal
                    ), item.title
                )
            }

        }

    }

}
@Composable
fun subMenu(index: Int, navController: NavController, item: Lessons_subitem,title: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("reading_page/${index-1}")
            }
            .padding(10.dp,10.dp,10.dp)
            .height(150.dp),
        elevation = 7.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.fillMaxWidth(0.2F)) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .scale(0.6F)
                        .height(120.dp),
                    imageVector = ImageVector.vectorResource(id = item.icon),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds
                )
                Text(
                    text = "کوییز | دروس", modifier = Modifier
                        .fillMaxWidth()
                        .padding(7.dp)
                        .width(20.dp)
                )
            }





            Text(
                modifier = Modifier
                    .fillMaxWidth(0.7F)
                    .align(Alignment.CenterVertically)
                    .height(30.dp),
                text = title,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = colorResource(id = R.color.textColor_deep_blue),
            )

            Image(
                modifier = Modifier
                    .fillMaxWidth(0.9F)
                    .height(150.dp),
                painter = painterResource(id = R.drawable.guidline),
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )


            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(1.0F)

                    .background(
                        colorResource(id = R.color.recyclerEdgeBlueColor)
                    ),

                )


        }

    }
}

