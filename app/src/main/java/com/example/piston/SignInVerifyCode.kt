package com.example.piston

import android.util.Log
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.data.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Composable
fun SignInVerifyCode(
    navController: NavController,
    fullNameInputed: String?,
    phoneInputed: String?,
    codeInputed: String?

) {
    val viewModelRetrofit: ViewModelRetrofit = viewModel()
    var LifecycleOwner = LocalLifecycleOwner.current
    var phone:String?=     phoneInputed
    var fullName:String?=fullNameInputed
    var code by rememberSaveable { mutableStateOf("") }
    val b=true

    var checkCode: Int? = null
    var codePlaceHolderText: String by remember {
        mutableStateOf("کد دریافتی")


    }

    var codePlaceHolderColor: Color = Color.Gray
    var resultText: String = "ارسال کد تایید"
    var isVisible: Boolean = true
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
                .height(51.dp), horizontalArrangement = Arrangement.Center


        ) {


            OutlinedTextField(
                value = fullNameInputed!!,
                onValueChange = { newValue ->
                    fullName = fullNameInputed

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
                .height(51.dp), horizontalArrangement = Arrangement.Center

        ) {


            OutlinedTextField(
                value = code,
                onValueChange = { newValue ->
                    code = newValue},
                placeholder = {
                    Text(
                        codePlaceHolderText,
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
        Spacer(modifier = Modifier.padding(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(51.dp), horizontalArrangement = Arrangement.Center

        ) {


            OutlinedTextField(
                value = phoneInputed!!,
                onValueChange = { newValue ->
                    phone = phoneInputed

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

//        if (checkCode == 0) {
//            code = ""
//            com.example.piston.codeChecker(
//                true,
//                codeValue = code,
//                codePlaceHolderText = "کد نامعتبر",
//                codePlaceHolderColor = Color.Red
//            )
//        } else {
//            com.example.piston.codeChecker(
//                true,
//                code,
//                codePlaceHolderText = "کد دریافتی",
//                codePlaceHolderColor = Color.Gray
//            )
//        }

        Spacer(modifier = Modifier.height(100.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            var LifecycleOwner = LocalLifecycleOwner.current
            val coroutineScope = rememberCoroutineScope()

            Button(


                        onClick = {
                            Log.d("bb1", "onCreate: $code")

                  coroutineScope.launch(Dispatchers.Unconfined)
                  {if(b){
                        Log.d("bb", "onCreate: $code")
                        viewModelRetrofit.verifyCode(phone!!, code, fullName!! )

                        viewModelRetrofit.invalidCode.observe(LifecycleOwner) {
                            Log.d("TAG000", "onCreate: $it")
                            if(it==0)
                            {
                                codePlaceHolderText = "کد نامعتبر"
                                code = ""
                            }
                            if(it==1)
                            {

                                code = "ok"
                            }
                        }

                        }

                    }


                    //    resultText  =viewModelRetrofit.getCode(phone)
                    //  navController.navigate(Screen.SignInVerifyCode.route+"/$fullName/$phone"+codeInputed  )


                },
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.light_green)),
                modifier = Modifier
                    .width(330.dp)
                    .height(45.dp)
            )
            {
                //Text(text = "خرید",style = MaterialTheme.typography.h6)

                Text(
                    text = "تایید",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }


    }

}
