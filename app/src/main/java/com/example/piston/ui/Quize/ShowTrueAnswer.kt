package com.example.piston.ui.Quize

import android.annotation.SuppressLint
import android.os.CountDownTimer
import android.util.Log
import android.view.Gravity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavController
import com.example.myapplication.domain.model.QuizModel
import com.example.piston.*
import com.google.accompanist.pager.*
import com.google.gson.Gson
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.absoluteValue
import kotlin.random.Random

@ExperimentalPagerApi
@Composable
fun ShowTrueAnswer(navController: NavController,answers:List<Int>,quizList:List<QuizModel>,showTrueAnswer:Boolean = true){
    var state = rememberPagerState(pageCount = quizList.size)
    var (selectedAnswerList, selectedAnswerOnChange) = remember {
        mutableStateOf(answers)
    }
    var correctAnswer by remember {
        mutableStateOf(showTrueAnswer)
    }
    var choose by remember {
        mutableStateOf(0)
    }
    Log.i("TAG000", "ExamTestPage: ${quizList.size}")
    var scope = rememberCoroutineScope()
    var countDownTimer by remember{
        mutableStateOf<CountDownTimer?>(null)
    }
    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(250, 250, 255)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PagerLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(12f),
                state = state,
                quizList,
                correctAnswer,
                selectedAnswerList as ArrayList<Int>,
                selectedAnswerOnChange,
            ) {
                choose = it
            }
            QuestionList(modifier = Modifier.weight(1f), onChoose = {
                scope.launch {
                    state.animateScrollToPage(it)
                }
                choose = it
            }, choose, 30)
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
        com.example.piston.R.drawable.image1,
        com.example.piston.R.drawable.image10,
        com.example.piston.R.drawable.image12,
        com.example.piston.R.drawable.image14,
        com.example.piston.R.drawable.image15,
        com.example.piston.R.drawable.image17,
        com.example.piston.R.drawable.image18,
        com.example.piston.R.drawable.image19,
        com.example.piston.R.drawable.image20,
        com.example.piston.R.drawable.image22,
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
                            .weight(1f)
                            .padding(4.dp),
                        elevation = 4.dp,
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(width = 2.dp, color = color)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
//                                    onSelectAnswer(answerIndex, index)
                                }, contentAlignment = Alignment.Center
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
                                        text = (answerIndex + 1).toString(),
                                        modifier = Modifier.fillMaxSize(),
                                        color = Color.Gray
                                    )
                                }
                                AutoSizeText(
                                    text = answers[answerIndex],
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