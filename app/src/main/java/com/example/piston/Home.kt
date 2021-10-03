package com.example.piston.presenter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.data.Screen
import com.example.piston.R
import com.example.piston.ui.theme.PistonTheme

@Composable
fun HomeLayout(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {

        Row() {
            AverageTrueAnswers()

        }


        Row() {
            Divider(color = colorResource(id = R.color.light_gray), thickness = 1.5.dp)

        }
        Row(

        ) {
            buttons()
        }
        Row() {
            lastLessonCard()
        }


    }


}

@Composable
fun lastLessonCard() {
    Card(


            modifier = Modifier
                .fillMaxWidth()
                .border(3.dp, Color.White, RoundedCornerShape(40))
                .shadow(20.dp)


            ) {
            Column(
                Modifier.padding(10.dp),
                horizontalAlignment = Alignment.End
            )
            {

                Row(
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    Text(
                        text = "آخرین درس",
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.textColor_deep_blue),
                        textAlign = TextAlign.End
                    )
                    Icon(

                        painterResource(R.drawable.about_us_image),
                        contentDescription = "",
                        modifier = Modifier
                            .size(15.dp)
                    )

                }
                Spacer(modifier = Modifier.padding(8.dp))
                Row {
                    Column(
                        modifier = Modifier.weight(weight = 1f),


                        ) {
                        Image(
                            painterResource(R.drawable.about_us_image),
                            contentDescription = "",

                            modifier = Modifier
                                .size(70.dp)
                        )
                    }


                    Column(
                        modifier = Modifier
                            .weight(weight = 3f)
                            .padding(0.dp, 15.dp),
                        horizontalAlignment = Alignment.End,
                    ) {

                        Text(
                            text = " کمک های اولیه",
                            fontSize = 23.sp,
                            color = colorResource(id = R.color.textColor_deep_blue),
                            textAlign = TextAlign.End
                        )
                    }


                }
            }
        }


        }

        @Composable
        fun AverageTrueAnswers() {
            Card(

                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.White, RoundedCornerShape(10.dp, 10.dp, 0.dp, 0.dp))
                    .shadow(10.dp)


            ) {
                Column(
                    Modifier.padding(10.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Row(
                        //  horizontalArrangement = Arrangement.Start
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier


                    ) {

                        Text(
                            text = "میانگین پاسخ های صحیح",
                            fontSize = 25.sp,
                            color = colorResource(id = R.color.textColor_deep_blue),
                            textAlign = TextAlign.End
                        )

                    }
                    Spacer(modifier = Modifier.padding(16.dp))
                    Row(
                        //horizontalArrangement = Arrangement.End,
                        // horizontalArrangement = Arrangement.Center


                    ) {
                        Text(
                            text = stringResource(id = R.string.AverageAnswerInfo_txt),
                            fontSize = 10.sp,
                            color = colorResource(id = R.color.textColor_deep_blue),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .size(240.dp, 35.dp)


                        )
                    }
                    Row(
                        //horizontalArrangement = Arrangement.End,
                        // horizontalArrangement = Arrangement.Center


                    ) {
                        Text(
                            text = stringResource(id = R.string.normal_avrage_text1),
                            fontSize = 15.sp,
                            color = colorResource(id = R.color.textColor_deep_blue),
                            textAlign = TextAlign.Center
                        )

                    }


                }
                Column(Modifier.padding(10.dp)) {


                    Row() {
                        Box(

                            Modifier
                                .size(100.dp)
                                .padding(10.dp)
                                .background(color = colorResource(id = R.color.light_gray))


                        ) {
                        }
                    }
                }

                Spacer(modifier = Modifier.padding(10.dp))


            }


        }

        @Composable
        fun buttons() {

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(

                ) {


                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.light_gray)),
                        modifier = Modifier
                            .size(130.dp, 40.dp)


                    ) {
                        //Text(text = "خرید",style = MaterialTheme.typography.h6)
                        Text(
                            text = "نشان شده",
                            fontSize = 16.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )

                    }
                }

            }


        }

















        @Preview(showBackground = true)
        @Composable
        fun DefaultPreview() {
            PistonTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()

                ) {

                    Row() {
                        AverageTrueAnswers()

                    }


                    Row() {
                        Divider(color = colorResource(id = R.color.light_gray), thickness = 1.5.dp)

                    }
                    Row(

                    ) {
                        buttons()
                    }
                    Row() {
                        lastLessonCard()
                    }

                }


            }
        }