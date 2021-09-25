package com.example.piston

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun More(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .scrollable(
                rememberScrollState(), orientation = Orientation.Vertical
            )
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            subscriptionCard(
                price = stringResource(id = R.string.GoldenACCPrice),
                title = stringResource(id = R.string.golden_test_txt),
                body = stringResource(id = R.string.GoldenAccBody),
                color = colorResource(id = R.color.golden),
                size = 0.5F
            )
            subscriptionCard(
                price = stringResource(id = R.string.FullAccountPrice),
                title = stringResource(id = R.string.FullAccountTitle_txt),
                body = stringResource(id = R.string.FullAccBody_txt) + "\n" + stringResource(id = R.string.FullAccbody_txt2) + "\n" + stringResource(
                    id = R.string.FullAccBody_txt2
                ),
                color = colorResource(id = R.color.courcesBlue),
                size = 1.0F
            )
        }
        Row() {
            IconButton(modifier = Modifier.background(colorResource(id = R.color.courcesBlue)),
                onClick = { /*TODO*/ }) {

                Text(
                    text = stringResource(id = R.string.MoneySec_txt),
                    color = colorResource(id = R.color.white_deep)
                )
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_money),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
            }
            IconButton(modifier = Modifier.background(colorResource(id = R.color.courcesBlue)),
                onClick = { /*TODO*/ }) {

                Text(
                    text = stringResource(id = R.string.SendTicket_txt),
                    color = colorResource(id = R.color.white_deep)
                )
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_support),
                    contentDescription = "",
                    tint = Color.Unspecified
                )

            }
        }
        moreCard(
            icon = R.drawable.ic_person,
            text = R.string.USerProfile_txt,
            color = R.color.courcesBlue
        )
        moreCard(
            icon = R.drawable.ic_manabe,
            text = R.string.ResourceTitle_txt,
            color = R.color.courcesBlue
        )
        moreCard(
            icon = R.drawable.ic_add_member,
            text = R.string.InviteFriendTitle_txt,
            color = R.color.courcesBlue
        )
        moreCard(
            icon = R.drawable.ic_call,
            text = R.string.CallUsTitle_txt,
            color = R.color.courcesBlue
        )
        moreCard(icon = R.drawable.ic_info, text = R.string.about_us, color = R.color.golden)

    }
}

@Composable
fun subscriptionCard(price: String, title: String, body: String, color: Color, size: Float) {
    Card(
        modifier = Modifier
            .fillMaxWidth(size)
            .height(200.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = price,
                color = color,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                color = color,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = body,
                color = colorResource(id = R.color.gray),
                textAlign = TextAlign.Center
            )
            Button(
                modifier = Modifier
                    .background(
                        color,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .fillMaxWidth(0.8F),
                onClick = { /*TODO*/ },

                ) {
                Text(text = stringResource(id = R.string.txt_buy))
            }
        }
    }
}

@Composable
fun moreCard(icon: Int, text: Int, color: Int) {
    IconButton(modifier = Modifier.background(colorResource(id = R.color.white_deep)),
        onClick = { /*TODO*/ }) {

        Text(
            text = stringResource(id = text),
            color = colorResource(id = R.color.courcesBlue)
        )
        Icon(
            modifier = Modifier.background(colorResource(id = color)),
            imageVector = ImageVector.vectorResource(id = icon),
            contentDescription = "",
            tint = Color.Unspecified
        )

    }


}