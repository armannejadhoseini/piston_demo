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

import com.example.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelRetrofit(application: Application) : AndroidViewModel(application) {


    lateinit private var code: String

    // val liveData: MutableLiveData<String> = MutableLiveData<String>()
    public var invalidCode = MutableLiveData<Int>()
  public  var  liveDataAccType = MutableLiveData<Int>()


    fun getCode(phone1: String): String {
        code = Repository().requestToSendValidationCode(phone1).toString()

        return code
    }


    fun verifyCode(phone: String, code: String, fullName: String) {
        viewModelScope.launch {
            invalidCode = (Repository().requestVerifyValidationCode(phone, code, fullName))
            Log.d("invalidViewModel", " ${invalidCode.value}")

            liveDataAccType=(Repository().verifyAccountType(phone,fullName))
            Log.d("verifyCodeViewModel", " ${liveDataAccType.value}")



        }

    }


}