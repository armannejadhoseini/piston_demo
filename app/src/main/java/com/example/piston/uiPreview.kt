package com.example.piston

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


object myData {
    lateinit var myList: List<ScreenContent>

    @Composable
    fun getList(): List<ScreenContent> {
        myList = listOf(
            ScreenContent(
                1,
                R.drawable.ic_launcher_background,
                "Creativity",
                "Our goal in this artivle is to boost your creavity to make you a better designer in long term. "
            ),
            ScreenContent(
                2,
                R.drawable.ic_launcher_background,
                "Skill",
                "You can learn and Improve your skills by intending in our courses designed for you. "
            ),
            ScreenContent(
                3,
                R.drawable.ic_launcher_background,
                "Job",
                "We provide the best we can to help you achieve your dream job. "
            )


        )
        return myList
    }
}

@Composable
fun Ui(item: ScreenContent, padding: Dp) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                Log.d("TAG", "Ui2: clicked at item #${item.id}")
            }
            .absolutePadding(4.dp, 10.dp, 20.dp, padding),
        shape = RoundedCornerShape(30.dp),
        elevation = 20.dp
    ) {


        Row(modifier = Modifier.fillMaxWidth()) {

            Image(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .height(180.dp)
                    .fillMaxWidth(0.4F),
                imageVector = ImageVector.vectorResource(id = item.itemImage),
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )

            Spacer(
                modifier = Modifier
                    .width(10.dp)
                    .height(180.dp)
            )
            Column {
                Text(
                    modifier = Modifier
                        .layoutId("text_title")
                        .fillMaxWidth(1.0F)
                        .height(30.dp),
                    text = item.itemTitle,
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp,
                    color = Color(66, 132, 237)

                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    modifier = Modifier
                        .layoutId("text_title")
                        .fillMaxWidth(1.0F)
                        .height(140.dp), text = item.itemBody,
                    textAlign = TextAlign.Start,
                    fontSize = 10.sp,
                    color = Color.Gray

                )
            }
        }
    }
}

@Composable
fun Ui2(item: ScreenContent, padding: Dp) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                Log.d("TAG", "Ui2: clicked at item #${item.id}")
            }
            .absolutePadding(20.dp, 10.dp, 4.dp, padding),
        shape = RoundedCornerShape(30.dp),
        elevation = 20.dp,
    ) {


        Row(modifier = Modifier.fillMaxWidth()) {

            Column {
                Text(
                    modifier = Modifier
                        .layoutId("text_title")
                        .fillMaxWidth(0.6F)
                        .height(30.dp),
                    text = item.itemTitle,
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp,
                    color = Color(66, 132, 237)


                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    modifier = Modifier
                        .layoutId("text_title")
                        .fillMaxWidth(0.6F)
                        .absolutePadding(2.dp)
                        .height(140.dp), text = item.itemBody,
                    textAlign = TextAlign.Start,
                    fontSize = 10.sp,
                    color = Color.Gray
                )
            }
            Spacer(
                modifier = Modifier
                    .width(10.dp)
                    .height(180.dp)
            )
            Image(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .height(180.dp)
                    .fillMaxWidth(1.0F),
                imageVector = ImageVector.vectorResource(id = item.itemImage),
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )
        }
    }
}







