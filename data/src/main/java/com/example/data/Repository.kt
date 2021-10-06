package com.example.data

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.data.entities.apiModel.VerifyAccountModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    var resultText: String? = null
    public val liveDataInvalidCode = MutableLiveData<Int>()


    fun requestToSendValidationCode(phone: String): String? {
        //  val liveData:MutableLiveData<String> = MutableLiveData()

        val apiService: ApiInterface =
            ApiClient.getApiClient(ApiClient.BASE_URL)!!.create(ApiInterface::class.java)
        val call: Call<Void> = apiService.sendValidationCode(phone)
        call.enqueue(object : Callback<Void> {
            @SuppressLint("UseCompatLoadingForDrawables")
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
//                newUser_validCode_layout.visibility = View.VISIBLE
//                send_validationCode.background =
//                    requireContext().getDrawable(R.drawable.indicator_done_shape)
//                sumbit_code_txt.text =resources.getString(R.string.sumbit_btn)
                    //  liveData.value="تایید"

                    Log.i("q1", "onResponse:تایید ")

                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
//            Toast.makeText(requireActivity(), "لطفا دوباره تلاش کنید", Toast.LENGTH_SHORT)
//                .show()

            }

        })

        return resultText
    }

    suspend fun requestVerifyValidationCode(
        userPhone: String,
        Code: String,
        userName: String
    ): MutableLiveData<Int> {
        // var  checkCode:Int=7

        val liveData = MutableLiveData<Int>()
        val task = GlobalScope.launch(Dispatchers.IO) {

            val apiService: ApiInterface =
                ApiClient.getApiClient(ApiClient.BASE_URL)!!.create(ApiInterface::class.java)
            val call: Call<Int> = apiService.verifyValidationCode(userPhone, Code, userName)

            call.enqueue(object : Callback<Int> {
                @SuppressLint("UseCompatLoadingForDrawables")

                override fun onResponse(call: Call<Int>, response: Response<Int>) {

                    if (response.isSuccessful) {
                        if (response.body() == 1) {

                             runBlocking { verifyAccountType(userPhone,userName)}
//                        newUser_validCode_layout.isErrorEnabled = true
//                        newUser_validCode_layout.error = "کد نامعتبر است"
                            liveData.value = response.body()!!
                            liveDataInvalidCode.value = response.body()!!
                            Log.d("hexxxx", "onResponse: ${liveData.value}")
                            Log.d("rexxxx", "onResponse: ${liveDataInvalidCode.value}")


                        }
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.d("hexxxx", "onResponse: " + t)
                }

            })


        }
        task.join()

        return liveData
    }

    public fun getter(): LiveData<Int> {
        return liveDataInvalidCode
    }


 suspend    fun verifyAccountType(userPhone: String, userName: String):MutableLiveData<Int> {

     //   val bundle = Bundle()

      val  liveDataAccType = MutableLiveData<Int>()

        val task1 = GlobalScope.launch(Dispatchers.IO) {
            val apiService: ApiInterface = ApiClient.getApiClient(ApiClient.BASE_URL)!!.create(ApiInterface::class.java)
            val call: Call<List<VerifyAccountModel>> = apiService.verifyAccount(userPhone)
            call.enqueue(object : Callback<List<VerifyAccountModel>> {
                @SuppressLint("UseCompatLoadingForDrawables")
                override fun onResponse(
                    call: Call<List<VerifyAccountModel>>,
                    response: Response<List<VerifyAccountModel>>
                ) {
                    if (response.isSuccessful) {
                        liveDataAccType.value=response.body()!![0].acc_type
                        Log.d("liveDataAccType", "onResponse: " +liveDataAccType.value)
//                    if (response.body()!![0].acc_type == 1) {
//                        //   Hawk.put("fullAccount",20)
//                        bundle.putInt("fullAccount", 20)
//                    } else if (response.body()!![0].acc_type == 2) {
//                        //   Hawk.put("goldenAccount",10)
//                        bundle.putInt("goldenAccount", 10)
//
//                    }
//                    bundle.putInt("logIn", 1)
//                    bundle.putString("userName", userName)
//                    bundle.putString("userPhone", userPhone)
                        //  HideSignInLayoutAnimation()
                        //initPage()
                    }
                }

                override fun onFailure(call: Call<List<VerifyAccountModel>>, t: Throwable) {
//                Toast.makeText(requireActivity(), "لطفا دوباره تلاش کنید", Toast.LENGTH_SHORT)
//                    .show()
                }

            })

        }

      task1.join()
     return liveDataAccType
    }


}