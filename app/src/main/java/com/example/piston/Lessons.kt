package com.example.piston

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.data.Constants
import com.example.data.lessons_item

@Composable
fun Lessons(navController: NavController) {
    Scaffold(Modifier.fillMaxHeight(),
        topBar = {
            TopAppBar(backgroundColor = Color.White) {

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "دوره های آموزشی",
                    textAlign = TextAlign.Center,
                )
            }
        }
    ) {

        LazyColumn() {
            items(Constants.lessons_list) { item ->
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
            .height(150.dp)
            .clickable {
                navController.navigate("Lessons_menu")
            },
        elevation = 10.dp,
        shape = RoundedCornerShape(30.dp),

        ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {
            Image(
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_launcher_background),
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp), text = item.title,
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp), text = item.body,
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun Lessons_menu(item_name: String, navController: NavController) {
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
                                .height(30.dp),

                            onClick = {
                                navController.previousBackStackEntry
                            },
                            ) {
                            Icon(
                                modifier = Modifier
                                    .width(30.dp)
                                    .background(color = Color(33, 107, 200))
                                    .height(30.dp),
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_arrow_back_24),
                                contentDescription = "",
                                tint = Color.Unspecified
                            )

                        }

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "لیست دوره های $item_name",
                        textAlign = TextAlign.End

                    )
                }
            }
        }) {


        LazyColumn {

        }

    }
}

@Composable
fun ok() {
Card(modifier = Modifier
    .fillMaxWidth()
    .height(150.dp)) {
    

}
}

@Preview
@Composable
fun Preview() {
    Lessons(navController = rememberNavController())
}