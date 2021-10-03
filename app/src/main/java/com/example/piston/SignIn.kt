package com.example.piston

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDirections
import androidx.navigation.Navigation.findNavController
import com.example.data.Screen
import com.example.piston.ui.theme.PistonTheme


@Composable
fun SignIn(navController: NavController) {
    val viewModelRetrofit: ViewModelRetrofit = viewModel()
    var phone by rememberSaveable { mutableStateOf("") }
    var fullName:String by rememberSaveable { mutableStateOf("") }
    var resultText: String = "ارسال کد تایید"

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Row() {
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
                            navController.navigate("SignInBack")
                        },

                        ) {
                        Icon(
                            modifier = Modifier
                                .width(22.dp)
                                .padding(start = 2.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(color = colorResource(id = R.color.purple_500))
                                .height(20.dp),
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                            contentDescription = "",
                            tint = Color.Unspecified
                        )

                    }

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "اطلاعات حساب",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.textColor_deep_blue)

                    )
                }
            }
        }


        Spacer(modifier = Modifier.padding(10.dp))
        Row(

            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center


        ) {
            Text(
                "افزودن حساب کاربری", fontSize = 20.sp,
                color = colorResource(id = R.color.gray)

            )
            Icon(

                painterResource(R.drawable.ic_person),
                contentDescription = "",
                modifier = Modifier
                    .size(25.dp),
                tint = colorResource(id = R.color.textColor_deep_blue)

            )
        }
        Spacer(modifier = Modifier.padding(10.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(51.dp)
            , horizontalArrangement = Arrangement.Center


        ) {


            OutlinedTextField(
                value = fullName,
                onValueChange = { newValue ->
                    fullName = newValue

                },


                placeholder = {
                    Text(
                        "نام و نام خانوادگی",
                        modifier = Modifier.padding(70.dp, 0.dp),
                    )
                    Icon(

                        painterResource(R.drawable.ic_person),
                        contentDescription = "",
                        modifier = Modifier
                            .size(25.dp),
                        tint = colorResource(id = R.color.gray)

                    )
                }

            )
        }
        Spacer(modifier = Modifier.padding(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(51.dp)
            , horizontalArrangement = Arrangement.Center
        ) {


            OutlinedTextField(
                value = phone,
                onValueChange = { newValue ->
                    phone = newValue

                },
                placeholder = {
                    Text(
                        "شماره تلفن",
                        modifier = Modifier.padding(90.dp, 0.dp)
                    )
                    Icon(

                        painterResource(R.drawable.ic_phone),
                        contentDescription = "",
                        modifier = Modifier
                            .size(25.dp),
                        tint = colorResource(id = R.color.gray)

                    )
                }
            )

        }
        Spacer(modifier = Modifier.padding(100.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    //    resultText  =viewModelRetrofit.getCode(phone)
                    resultText = viewModelRetrofit.getCode(phone)
                   navController.navigate(Screen.SignInVerifyCode.route+"/$fullName/$phone"  )



                },
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.purple_500)),
                modifier = Modifier
                    .width(330.dp)
                    .height(45.dp)


            )
            {
                //Text(text = "خرید",style = MaterialTheme.typography.h6)

                Text(
                    text = resultText,
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }


    }
}












//public class TextFieldState() {
//    var text: String by mutableStateOf("")
//}

//@Composable
//fun textInput(emailState :TextFieldState= remember { TextFieldState() },hint:String){
//   // var txt by rememberSaveable { mutableStateOf(txt1) }
//
//    TextField(
//        value =emailState.text,
//        onValueChange = {
//            emailState.text=it
//
//        },
//        placeholder = { hint }
//    )
//
//}

//fun getCode(code:String, viewModelRetrofit: ViewModelRetrofit  ) {
//
//
//        viewModelRetrofit.getCode(code).observe(this, viewModelRetrofit.liveData<String> ->{
//
//
//
//    }
//
//}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PistonTheme {
        val viewModelRetrofit: ViewModelRetrofit = viewModel()
        var phone by rememberSaveable { mutableStateOf("") }
        var fullName by rememberSaveable { mutableStateOf("") }
        var resultText: String = "ارسال کد تایید"


        Column() {
            Row() {


                OutlinedTextField(
                    value = fullName,
                    onValueChange = { newValue ->
                        fullName = newValue

                    },
                    placeholder = { Text("نام و نام خانوادگی") }
                )
            }
            Row() {


                OutlinedTextField(
                    value = phone,
                    onValueChange = { newValue ->
                        phone = newValue

                    },
                    placeholder = { Text("شماره موبایل") }
                )

                Spacer(modifier = Modifier.padding(200.dp))
            }
            Button(
                onClick = {
                    //    resultText  =viewModelRetrofit.getCode(phone)
                    resultText = viewModelRetrofit.getCode(phone)


                },
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.light_gray)),
                modifier = Modifier
                    .size(130.dp, 40.dp),
            )
            {
                //Text(text = "خرید",style = MaterialTheme.typography.h6)

                Text(
                    text = resultText,
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

        }

    }
}
