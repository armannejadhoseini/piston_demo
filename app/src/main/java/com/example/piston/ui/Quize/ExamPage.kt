package com.example.piston

import android.annotation.SuppressLint
import android.os.CountDownTimer
import android.view.Gravity
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.piston.Pages.questionResultPage

import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import java.util.*
import kotlin.math.absoluteValue
import kotlin.random.Random

@ExperimentalPagerApi
@Composable
fun ExamPage(navController: NavHostController) {
    var list = listOf("amir", "ali", "jamal", "karim", "hossein")
    var colorList = listOf(Color.Blue, Color.Green, Color.Red, Color.Cyan)
    var count = 20
    var state = rememberPagerState(pageCount = count)
    var choose by remember {
        mutableStateOf(0)
    }
    var scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(250, 250, 255)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopLayout(
            Modifier
                .fillMaxWidth()
                .weight(1.2f)
        ) {
            navController.navigate(questionResultPage)
        }
        PagerLayout(
            modifier = Modifier
                .fillMaxWidth()
                .weight(12f), state = state
        ) {
            choose = it
        }
        QuestionList(modifier = Modifier.weight(1f), onChoose = {
            scope.launch {
                state.animateScrollToPage(it)
            }
            choose = it
        }, choose, count)
    }

}

@Composable
fun TopLayout(modifier: Modifier, onFinish: () -> Unit) {
    var context = LocalContext.current
    Row(
        modifier = modifier
    ) {
        Card(
            modifier = Modifier
                .fillMaxHeight(1f)
                .weight(1f)
                .padding(6.dp),
            shape = RoundedCornerShape(16.dp),
            backgroundColor = Color(150, 150, 250)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { }
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(4f), contentAlignment = Center
        ) {
            TimerLayout(3700) {
                onFinish()
            }
        }
        Card(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 4.dp, bottom = 4.dp, top = 4.dp, end = 4.dp)
                .weight(1f), backgroundColor = Color(150, 150, 250),
            shape = RoundedCornerShape(16.dp)
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .clickable { onFinish() }) {
                AutoSizeText(
                    text = "پایان", modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp), color = Color.White
                )
            }
        }

    }
}

@ExperimentalPagerApi
@Composable
fun PagerLayout(modifier: Modifier, state: PagerState, onPageScroll: (Int) -> Unit) {
    HorizontalPager(
        state,
        modifier = modifier,
    ) { page ->
        onPageScroll(this.currentPage)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                    alpha = lerp(
                        start = 0f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also {
                        this.scaleX = it
                        this.scaleY = it
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize(1f)
                    .padding(start = 4.dp, end = 4.dp, top = 8.dp, bottom = 8.dp),
                shape = RoundedCornerShape(
                    topStart = 8.dp,
                    topEnd = 8.dp,
                    bottomEnd = 8.dp,
                    bottomStart = 8.dp
                ),
                elevation = 8.dp
            ) {
                QuestionLayout(page)
            }

        }
    }
}

@Composable
fun TimerLayout(timeInMils: Long = 190000, onFinish: () -> Unit) {
    var minute by remember {
        mutableStateOf(0)
    }
    var second by remember {
        mutableStateOf(0)
    }
    var coroutineScope = rememberCoroutineScope()
    LaunchedEffect(key1 = "one") {
        coroutineScope.launch {
            var x = object : CountDownTimer(timeInMils, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    var seconds = millisUntilFinished / 1000
                    minute = (seconds / 60).toInt()
                    second = (seconds % 60).toInt()
                }

                override fun onFinish() {
                    onFinish()
                }
            }
            x.start()
        }

    }

    Card(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.5f)
            .padding(start = 4.dp, bottom = 4.dp, top = 4.dp, end = 4.dp),

        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AutoSizeText(
                text = minute.toString(), modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight(), color = Color.Gray
            )
            AutoSizeText(
                text = ":", modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(), color = Color.Gray
            )
            AutoSizeText(
                text = second.toString(), modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight(), color = Color.Gray
            )
        }
    }
}


@SuppressLint("RestrictedApi")
@Composable
fun QuestionLayout(page: Int) {
    var imageList = listOf(
        R.drawable.image1,
        R.drawable.image10,
        R.drawable.image12,
        R.drawable.image14,
        R.drawable.image15,
        R.drawable.image17,
        R.drawable.image18,
        R.drawable.image19,
        R.drawable.image20,
        R.drawable.image22,
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp), contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(
                    imageList[
                            Random(Calendar.getInstance().timeInMillis).nextInt(
                                from = 0,
                                until = 9
                            )
                    ]

                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .weight(2f)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(4.dp), contentAlignment = Alignment.CenterStart
                ) {
                    AutoSizeText(
                        text = LoremIpsum(12).values.iterator().next(),
                        modifier = Modifier.fillMaxSize(),
                        color = Color.DarkGray,
                        gravity = Gravity.START
                    )
                }
                var selectedAnswer by remember {
                    mutableStateOf(-1)
                }
                (0..3).forEach { index ->
                    var borderStroke = if (index == selectedAnswer) {
                        2.dp
                    } else 0.dp
                    var color = if (index == selectedAnswer) {
                        Color.DarkGray
                    } else Color.Transparent
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(4.dp),
                        elevation = 4.dp,
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(width = borderStroke, color = color)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    selectedAnswer = index
                                }, contentAlignment = Center
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(4.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .aspectRatio(0.7f)
                                ) {
                                    AutoSizeText(
                                        text = (index + 1).toString(),
                                        modifier = Modifier.fillMaxSize(),
                                        color = Color.Gray
                                    )
                                }
                                AutoSizeText(
                                    text = LoremIpsum(12).values.iterator().next(),
                                    modifier = Modifier.weight(1f),
                                    color = Color.Gray,
                                    gravity = Gravity.START
                                )
                            }
                        }

                    }
                }

            }
        }
    }
}

@Composable
fun QuestionList(modifier: Modifier, onChoose: (index: Int) -> Unit, _choose: Int, count: Int) {
    var coroutineScope = rememberCoroutineScope()
    var state = rememberLazyListState()
    LazyRow(
        state = state, modifier = modifier
            .fillMaxWidth()
    ) {
        coroutineScope.launch {
            var firstVisibleItemIndex = state.firstVisibleItemIndex
            var visibleItemCount = state.layoutInfo.visibleItemsInfo.size
            if (_choose !in firstVisibleItemIndex + 1 until firstVisibleItemIndex + visibleItemCount - 1)
                state.animateScrollToItem(_choose)
        }
        items(count) { index ->
            var color =
                if (index == _choose) {
                    Color(220, 200, 100)
                } else Color(120, 120, 255)
            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .padding(4.dp),
                backgroundColor = color
            ) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        onChoose(index)
                    }) {
                    AutoSizeText(
                        text = (index + 1).toString(), modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxSize(), color = Color.White
                    )
                }
            }
        }
    }
}

private fun lerp(start: Float, stop: Float, fraction: Float): Float =
    (1 - fraction) * start + fraction * stop

@SuppressLint("RestrictedApi")
@Composable
fun AutoSizeText(text: String, modifier: Modifier, color: Color, gravity: Int = Gravity.CENTER,font:Int = R.font.shabnam,androidText:((AppCompatTextView)->Unit)? = null) {
    AndroidView(factory = {
        AppCompatTextView(it)
    }, modifier = modifier, update = {
        it.setAutoSizeTextTypeUniformWithConfiguration(1, 100, 1, 1)
        it.text = text
        it.layoutParams = ViewGroup.LayoutParams(-1, -1)
        it.gravity = gravity
        it.setTextColor(color.toArgb())
        it.setTypeface(ResourcesCompat.getFont(it.context,font))
        androidText?.let { andriodText->
            androidText(it)
        }
    })
}
