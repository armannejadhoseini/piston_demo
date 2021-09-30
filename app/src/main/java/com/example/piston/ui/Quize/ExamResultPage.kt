package com.example.piston

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.piston.ui.theme.textColor

@Composable
fun ExamResultPage(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopBar(
                modifier = Modifier.height(60.dp).padding(start = 8.dp,end = 8.dp,top=4.dp,bottom = 4.dp),
                stringResource(id = R.string.TestResulteTitle)
            )
        }
    }
}

@Composable
fun TextIcon(
    modifier: Modifier,
    backColor: Color,
    text: String,
    textColor: Color,
    clickable: () -> Unit,
    shape: Shape = RoundedCornerShape(16.dp)
) {
    Card(modifier = modifier, backgroundColor = backColor, shape = shape) {
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
fun ImageIcon(
    modifier: Modifier,
    backColor: Color,
    image: Int,
    clickable: () -> Unit,
    shape: Shape = RoundedCornerShape(16.dp)
) {
    Card(modifier = modifier, backgroundColor = backColor, shape = shape) {
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
fun TopBar(modifier: Modifier, text: String) {
    Row(modifier = modifier.fillMaxWidth()) {
        ImageIcon(
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.CenterVertically),
            backColor = Color.Blue,
            image = R.drawable.ic_back,
            clickable = {}
        )
        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        )
        AutoSizeText(
            text = text,
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .weight(2f),
            color = textColor
        )

    }
}



