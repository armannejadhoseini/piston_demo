package com.example.piston

import android.annotation.SuppressLint
import android.os.CountDownTimer
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavHostController
import com.example.myapplication.domain.model.QuizModel
import com.example.piston.ui.BackPressHandler
import com.example.piston.ui.Quize.ExamQuizPages.ElementaryResultName
import com.example.piston.ui.Quize.ExamQuizPages.ElementaryTestListName
import com.example.piston.ui.Quize.ExitDialog
import com.example.piston.ui.Quize.ExitDialogProperties
import com.example.piston.ui.Quize.QuizResult


import com.google.accompanist.pager.*
import com.google.gson.Gson
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.absoluteValue
import kotlin.random.Random


@ExperimentalPagerApi
@Composable
fun ExamTestPage(navController: NavHostController, quizList: List<QuizModel>) {
    var countDownTimer by remember {
        mutableStateOf<CountDownTimer?>(null)
    }
    var showDialog by remember {
        mutableStateOf(false)
    }
    BackPressHandler {
        showDialog = true
    }
    if (showDialog)
        ExitDialog(
            onDismissRequest = { showDialog = false },
            properties = DialogProperties(dismissOnClickOutside = false),
            dataProperties = ExitDialogProperties(
                stringResource(id = R.string.Exit_btn),
                stringResource(id = R.string.NeverMind_txt),
                stringResource(id = R.string.ExitExamTitle),
                R.drawable.ic_exit_img
            ),
            onCancel = { showDialog = false }, onConfirm = {
                navController.popBackStack(
                    route = ElementaryTestListName,
                    inclusive = false,
                    saveState = false
                )
            })

    var state = rememberPagerState(pageCount = quizList.size)
    var (selectedAnswerList, selectedAnswerOnChange) = remember {
        mutableStateOf(initSelectedList(30))
    }
    var correctAnswer by remember {
        mutableStateOf(false)
    }
    var choose by remember {
        mutableStateOf(0)
    }
    Log.i("TAG000", "ExamTestPage: ${quizList.size}")
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
                .height(60.dp),
            onBackPress = {
                showDialog = true
            },
            onFinish = {
                countDownTimer?.cancel()
                val quizResult = QuizResult(selectedAnswerList.toList(), quizList.toList())
                val quizResultJson = Gson().toJson(quizResult, QuizResult::class.java)
                navController.navigate("${ElementaryResultName}/$quizResultJson")
            },
            onCancel = {
                countDownTimer = it
            }
        )

        PagerLayout(
            modifier = Modifier
                .fillMaxWidth()
                .weight(12f),
            state = state,
            quizList,
            correctAnswer,
            selectedAnswerList,
            selectedAnswerOnChange,
        ) {
            choose = it
        }
        QuestionList(modifier = Modifier.height(40.dp), onChoose = {
            scope.launch {
                state.animateScrollToPage(it)
            }
            choose = it
        }, choose, 30)
    }
}

@Composable
fun TopLayout(
    modifier: Modifier,
    onBackPress: () -> Unit,
    onFinish: () -> Unit,
    onCancel: (CountDownTimer) -> Unit
) {
    Row(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        Card(
            modifier = Modifier
                .size(30.dp)
                .align(CenterVertically),
            shape = RoundedCornerShape(8.dp),
            backgroundColor = Color(150, 150, 250)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onBackPress() }
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(4f)
                .align(CenterVertically)
                .padding(horizontal = 8.dp, vertical = 2.dp), contentAlignment = Center
        ) {
            TimerLayout(
                20 * 60 * 1000,
                onCancel = onCancel,
                onFinish = onFinish
            )
        }
        Card(
            modifier = Modifier
                .width(80.dp)
                .height(40.dp)
                .align(CenterVertically), backgroundColor = Color(150, 150, 250),
            shape = RoundedCornerShape(8.dp)
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

fun <T> List<T>.copy(): ArrayList<T> {
    var list = ArrayList<T>()
    this.forEach {
        list.add(it)
    }
    return list
}

fun initSelectedList(size: Int): ArrayList<Int> {
    var list = ArrayList<Int>()
    (0 until size).forEach {
        list.add(-1)
    }
    return list
}

@ExperimentalPagerApi
@Composable
fun PagerLayout(
    modifier: Modifier,
    state: PagerState,
    list: List<QuizModel>,
    showCorrectAnswer: Boolean,
    selectedAnswerList: ArrayList<Int>,
    selectedAnswerOnChange: (ArrayList<Int>) -> Unit,
    onPageScroll: (Int) -> Unit
) {

    HorizontalPager(
        state,
        modifier = modifier,
    ) { index ->
        onPageScroll(this.currentPage)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(index).absoluteValue
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
                QuestionLayout(
                    index,
                    list[index],
                    selectedAnswerList[index],
                    showCorrectAnswer
                ) { answerIndex, pageIndex ->
                    val tempList = selectedAnswerList.copy()
                    tempList[pageIndex] = answerIndex
                    selectedAnswerOnChange(tempList)
                }
            }

        }
    }
}

@Composable
fun TimerLayout(
    timeInMils: Long = 190000,
    onFinish: () -> Unit,
    onCancel: (CountDownTimer) -> Unit
) {

    var minute by remember {
        mutableStateOf(0)
    }
    var second by remember {
        mutableStateOf(0)
    }
    var timer = object : CountDownTimer(timeInMils, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            var seconds = millisUntilFinished / 1000
            minute = (seconds / 60).toInt()
            second = (seconds % 60).toInt()
        }

        override fun onFinish() {
            onFinish()
        }
    }
    var coroutineScope = rememberCoroutineScope()
    LaunchedEffect(key1 = "one") {
        coroutineScope.launch {
            timer.start()
            onCancel(timer)
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
fun QuestionLayout(
    index: Int,
    page: QuizModel,
    selectedAnswer: Int,
    showCorrectAnswer: Boolean,
    onSelectAnswer: (index: Int, pageIndex: Int) -> Unit
) {
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

    fun randomImages(): List<Int> {
        var list = List(30) {
            imageList[Random(Calendar.getInstance().timeInMillis).nextInt(from = 0, until = 9)]
        }
        return list
    }

    var context = LocalContext.current
    var answers = listOf(page.answer1, page.answer2, page.answer3, page.answer4)
    var images by remember {
        mutableStateOf(randomImages())
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp), contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ComposeImageView(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .weight(2f), updateImage = { image ->
                page.image?.let {
                    image.setImageBitmap(it)
                } ?: let {
                    val drawableId = images[index]
                    val drawable = ResourcesCompat.getDrawable(context.resources, drawableId, null)
                    image.setImageDrawable(drawable)
                }

            })
            fun List<String>.findMaxSize(): Int {
                var size = 0
                forEach {
                    if (it.length > size)
                        size = it.length
                }
                return size
            }

            fun List<String>.findMinSize(): Int {
                var size = first().length
                forEach {
                    if (it.length < size)
                        size = it.length
                }
                return size
            }

            fun Float.coerceToWeight(weight: Float = 0.7f, max: Int): Float {
                return this.coerceIn(max * weight, max.toFloat()) / max.toFloat()
            }

            var maxLength = answers.findMaxSize()
            var minLength = answers.findMinSize()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(
                            maxLength
                                .toFloat()
                                .coerceToWeight(max = maxLength)
                        )
                        .padding(4.dp), contentAlignment = Alignment.CenterStart
                ) {
                    AutoSizeText(
                        text = page.title,
                        modifier = Modifier.fillMaxSize(),
                        color = Color.DarkGray,
                        gravity = Gravity.START
                    )
                }

                (0..3).forEach { answerIndex ->
                    var color = if (showCorrectAnswer) {
                        when {
                            answerIndex == page.true_answer -> Color.Green
                            selectedAnswer == answerIndex -> Color.Red
                            else -> Color.Transparent
                        }
                    } else {
                        if (answerIndex == selectedAnswer) {
                            Color.DarkGray
                        } else Color.Transparent
                    }
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(
                                (answers[answerIndex].length
                                    .toFloat()
                                    .coerceToWeight(max = maxLength))
                            )
                            .padding(4.dp),
                        elevation = 4.dp,
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(width = 2.dp, color = color)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    onSelectAnswer(answerIndex, index)
                                }, contentAlignment = Center
                        ) {
                            var density = LocalDensity.current
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(4.dp)
                            ) {
                                AutoSizeText(
                                    text = answers[answerIndex],
                                    modifier = Modifier
                                        .weight(1f)
                                        .align(CenterVertically),
                                    color = colorResource(id = R.color.textColors),
                                    gravity = Gravity.START or Gravity.CENTER_VERTICAL
                                ) {
                                    it.setAutoSizeTextTypeUniformWithConfiguration(
                                        1,
                                        with(density) {
                                            8.sp.toPx().toInt()
                                        },
                                        1,
                                        1
                                    )
                                }

                                Spacer(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .width(16.dp)
                                )

                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight(0.8f)
                                        .width(30.dp)
                                        .align(CenterVertically),contentAlignment = Center
                                ) {
                                    Text(
                                        text = (answerIndex + 1).toString(),
                                        fontSize = 15.sp,
                                        color = Color.Gray
                                    )
                                }
                            }
                        }

                    }
                }

            }
        }
    }
}


@Composable
fun ComposeImageView(modifier: Modifier, updateImage: (ImageView) -> Unit) {
    AndroidView(factory = {
        ImageView(it)
    }, update = {
        it.layoutParams = ViewGroup.LayoutParams(-1, -1)
        updateImage(it)
    },
        modifier = modifier
    )
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

fun lerp(start: Float, stop: Float, fraction: Float): Float =
    (1 - fraction) * start + fraction * stop

@SuppressLint("RestrictedApi")
@Composable
fun AutoSizeText(
    text: String,
    modifier: Modifier,
    color: Color,
    gravity: Int = Gravity.CENTER,
    font: Int = R.font.shabnam,
    androidText: ((AppCompatTextView) -> Unit)? = null
) {
    AndroidView(factory = {
        AppCompatTextView(it)
    }, modifier = modifier, update = {
        it.setAutoSizeTextTypeUniformWithConfiguration(1, 100, 1, 1)
        it.text = text
        it.layoutParams = ViewGroup.LayoutParams(-1, -1)
        it.gravity = gravity
        it.setTextColor(color.toArgb())
        it.typeface = ResourcesCompat.getFont(it.context, font)
        androidText?.let { andriodText ->
            androidText(it)
        }
    })
}
