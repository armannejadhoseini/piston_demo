package com.example.piston

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.lifecycle.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LifecycleOwner
import com.example.data.ApiClient
import com.example.data.ApiInterface
import com.orhanobut.hawk.Hawk
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelRetrofit : ViewModel() {



    lateinit private var code:String
   // val liveData: MutableLiveData<String> = MutableLiveData<String>()


    fun getCode(phone1:String):String{
        code= Repository().requestToSendValidationCode(phone1).toString()

        return code
    }
    fun verifyCode(phone:String,code:String,fullName:String):LiveData<Int>{
        viewModelScope.launch {
            val x= (Repository().requestVerifyValidationCode(phone,code,fullName)).value
         val   checkCode = getInvalidCode()

            Log.d("TAG", "verifyCode: ${checkCode.value}")


        }
       return getInvalidCode()

    }

//    fun getter():LiveData<Int>{
//        return che
//    }
    fun getInvalidCode():LiveData<Int>{
        return Repository().getter()
    }


}