package com.example.piston

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ExamResultPage(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopBar(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun TextIcon(modifier: Modifier, backColor: Color, text: String, textColor: Color,clickable: () -> Unit,shape:Shape = RoundedCornerShape(16.dp)) {
    Card(modifier = modifier, backgroundColor = backColor,shape = shape) {
        Box(modifier = Modifier.fillMaxSize()) {
            AutoSizeText(
                text = text, modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
                    .clickable { clickable() },
                color = textColor
            )
        }
    }
}

@Composable
fun ImageIcon(modifier: Modifier, backColor: Color, image: Int,clickable:()->Unit,shape: Shape = RoundedCornerShape(16.dp)) {
    Card(modifier = modifier, backgroundColor = backColor,shape = shape) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
                    .clickable {
                        clickable()
                    }
            )
        }
    }
}


@Composable
fun TopBar(modifier: Modifier) {
    Row(modifier = modifier.fillMaxWidth()) {
        TextIcon(
            modifier = Modifier
                .fillMaxHeight(0.3f)
                .aspectRatio(1f)
                .padding(8.dp),
            backColor = Color.Blue,
            text = "one",
            textColor = Color.White,
            clickable = {}
        )
        ImageIcon(
            modifier = Modifier
                .fillMaxHeight(0.3f)
                .aspectRatio(1f)
                .padding(8.dp),
            backColor = Color.Blue,
            image = R.drawable.ic_back,
            clickable = {}
        )
    }
}



