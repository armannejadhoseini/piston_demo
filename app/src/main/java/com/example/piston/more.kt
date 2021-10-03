package com.example.piston


import android.content.Context
import android.content.Intent
import android.graphics.Path
import android.view.Gravity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController




@Composable

fun more(navController: NavController) {
    val columnScroll = rememberScrollState()
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(columnScroll)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
            subscriptionCard(
                price = stringResource(id = R.string.GoldenACCPrice),
                title = stringResource(id = R.string.golden_test_txt),
                body = stringResource(id = R.string.GoldenAccBody),
                color = colorResource(id = R.color.golden),
                size = 0.5F,
                paddingAllPrice = 5.dp,
                paddingBottomPrice = 0.dp,
                paddingBottomPriceButton = 55.dp,
                navController

            )
            subscriptionCard(
                price = stringResource(id = R.string.FullAccountPrice),
                title = stringResource(id = R.string.FullAccountTitle_txt),
                body = stringResource(id = R.string.FullAccBody_txt) + "\n" + stringResource(id = R.string.FullAccbody_txt2) + "\n" + stringResource(
                    id = R.string.FullAccBody_txt2
                ),
                color = colorResource(id = R.color.courcesBlue),
                size = 1.0F,
                paddingAllPrice = 5.dp,
                paddingBottomPrice = 0.dp,
                paddingBottomPriceButton = 32.dp,
                navController

            )
        }
        // پول سازی و ارسال تیکت
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(60.dp)
                    .padding(top = 5.dp, end = 5.dp, start = 5.dp)
            ) {
                IconButton(
                    modifier = Modifier.background(colorResource(id = R.color.courcesBlue)),
                    onClick = {
                        Toast.makeText(context, "به زودی فعال می شه :)", Toast.LENGTH_LONG).show()
                        navController.navigate("sign")
                    }) {
                    Box() {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End
                        ) {
                            Text(
                                text = stringResource(id = R.string.MoneySec_txt),
                                color = colorResource(id = R.color.white_deep),
                                modifier = Modifier.padding(end = 5.dp),
                                fontSize = 20.sp
                            )
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_money),
                                contentDescription = "",
                                tint = Color.Unspecified,
                                modifier = Modifier.size(35.dp)
                            )
                        }
                    }

                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth(1.0f)
                    .height(60.dp)
                    .padding(top = 5.dp, end = 5.dp, start = 5.dp)
            ) {
                IconButton(
                    modifier = Modifier.background(colorResource(id = R.color.courcesBlue)),
                    onClick = { /*TODO*/ }) {
                    Box() {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End
                        ) {
                            Text(
                                text = stringResource(id = R.string.SendTicket_txt),
                                color = colorResource(id = R.color.white_deep),
                                modifier = Modifier.padding(end = 5.dp),
                                fontSize = 20.sp
                            )
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_support),
                                contentDescription = "",
                                tint = Color.Unspecified,
                                modifier = Modifier.size(35.dp)
                            )
                        }
                    }

                }


            }
        }

        Column() {
            moreCard(
                icon = R.drawable.ic_person,
                text = R.string.USerProfile_txt,
                color = R.color.courcesBlue,
                navController = navController
            )
            moreCard(
                icon = R.drawable.ic_manabe,
                text = R.string.ResourceTitle_txt,
                color = R.color.courcesBlue,
                navController = navController

            )
            moreCard(
                icon = R.drawable.ic_add_member,
                text = R.string.InviteFriendTitle_txt,
                color = R.color.courcesBlue,
                navController = navController
            )
            moreCard(
                icon = R.drawable.ic_call,
                text = R.string.CallUsTitle_txt,
                color = R.color.courcesBlue,
                navController = navController
            )
            moreCard(
                icon = R.drawable.ic_info,
                text = R.string.about_us,
                color = R.color.golden,
                navController = navController
            )
        }


    }

}

@Composable
fun subscriptionCard(
    price: String,
    title: String,
    body: String,
    color: Color,
    size: Float,
    paddingAllPrice: Dp,
    paddingBottomPrice: Dp,
    paddingBottomPriceButton: Dp,
    navController: NavController
    ) {
    Card(
        modifier = Modifier
            .fillMaxWidth(size)
            .padding(end = 2.75.dp, start = 2.75.dp)
            .height(200.dp),
        shape = RoundedCornerShape(10.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingAllPrice),
                text = price,
                color = color,
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = paddingBottomPrice, top = 10.dp),
                text = title,
                color = color,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = paddingBottomPriceButton, top = 15.dp),
                text = body,
                color = colorResource(id = R.color.gray),
                textAlign = TextAlign.Center,
                fontSize = 10.sp
            )
            Button(
                modifier = Modifier
                    .background(
                        shape = RoundedCornerShape(10.dp),
                        color = color
                    )
                    .fillMaxWidth(0.8F),
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = color)

            ) {
                Text(text = stringResource(id = R.string.txt_buy))
            }
        }
    }

}

@Composable
fun moreCard(icon: Int, text: Int, color: Int, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()

    ) {

        IconButton(modifier = Modifier.background(colorResource(id = R.color.white_deep)),
            onClick = {
                navController.navigate("SignIn")

            }) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp), contentAlignment = Alignment.CenterEnd
            ) {
                Row(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = stringResource(id = text),
                        color = colorResource(id = R.color.courcesBlue),
                    )
                    Icon(
                        modifier = Modifier
                            .padding(start = 15.dp)
                            .background(
                                colorResource(id = color),
                                shape = RoundedCornerShape(5.dp)
                            ),
                        imageVector = ImageVector.vectorResource(id = icon),
                        contentDescription = "",
                        tint = Color.Unspecified
                    )
                }

            }


        }
    }

}



