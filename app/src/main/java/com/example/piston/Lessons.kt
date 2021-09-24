package com.example.piston

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.res.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.data.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants
import kotlin.coroutines.coroutineContext

val lessons_list = listOf(
    lessons_item(
        0,
        "اموزش تئوری",
        "لیست دوره های تئوری",
        R.string.course_theory_body_text,
        R.drawable.theory_course_img
    ),
    lessons_item(
        1,
        "آموزش عملی",
        "لیست دوره های عملی",
        R.string.course_practical_body_text,
        R.drawable.practical_course_img
    ),
    lessons_item(
        2,
        "تابلو ها و خطوط راهنما",
        "لیست دروس",
        R.string.course_board_body_text,
        R.drawable.board_course_img
    )
)



@Composable
fun Lessons(navController: NavController) {

    Scaffold(Modifier.fillMaxHeight(),
        topBar = {
            TopAppBar(backgroundColor = Color.White) {

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "دوره های آموزشی",
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.textColor_deep_blue),
                    fontSize = 20.sp

                )
            }
        }
    ) {

        LazyColumn() {
            items(lessons_list) { item ->
                if (item.id == 2) {
                    itemsColumn(item = item, 30.dp, navController)
                } else {
                    itemsColumn(item = item, 0.dp, navController)
                }
            }
        }


    }
}

@Composable
fun itemsColumn(item: lessons_item, padding: Dp, navController: NavController) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(7.dp, 7.dp, 7.dp, padding)
            .height(180.dp)
            .clickable {
                navController.navigate("Lessons_menu/${item.cat}")
            },
        elevation = 10.dp,
        shape = RoundedCornerShape(30.dp),

        ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Image(
                modifier = Modifier
                    .width(180.dp)
                    .height(180.dp),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                painter = painterResource(id = item.vector)
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp), text = item.title,
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.textColor_deep_blue),
                    fontSize = 20.sp
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp)
                        .height(140.dp), text = stringResource(id = item.body),
                    textAlign = TextAlign.End,
                    color = colorResource(id = R.color.textColor_deep_blue),
                    fontSize = 10.sp
                )
            }
        }
    }
}