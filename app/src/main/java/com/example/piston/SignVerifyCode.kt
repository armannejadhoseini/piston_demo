package com.example.piston

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.data.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SignVerifyCode(
    navController: NavController,
    fullNameInputed: String?,
    phoneInputed: String?,
    codeInputed: String?

) {
    val coroutineScope = rememberCoroutineScope()
    val viewModelRetrofit: ViewModelRetrofit = viewModel()
    var LifecycleOwner = LocalLifecycleOwner.current
    var Lf by remember {
        mutableStateOf(LifecycleOwner)
    }
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



            Button(
                onClick = {

                    var b=true
                    Log.d("bb1", "onCreate: $code")
                    //  var LifecycleOwner = LocalLifecycleOwner.current

                    coroutineScope.launch(Dispatchers.Unconfined)
                    {if(b){
                        Log.d("ggggg", "onCreate: $code/$phone/$fullName")
                        viewModelRetrofit.verifyCode(phone!!, code, fullName!! )

                        viewModelRetrofit.invalidCode.observe(Lf) {
                            Log.d("TAG000", "onCreate: $it")
                            if(it==0)
                            {
                                codePlaceHolderText = "کد نامعتبر"
                                code = ""
                            }
                            if(it==1)
                            {

                                navController.navigate(Screen.Profile.route)
                            }
                        }

                    }

                    }


                    //    resultText  =viewModelRetrofit.getCode(phone)
                    //  navController.navigate(Screen.SignInVerifyCode.route+"/$fullName/$phone"+codeInputed  )
                    Log.d("newww", "onCreate: $code")

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
