package com.example.data

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.data.entities.apiModel.VerifyAccountModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    var resultText: String? = null
  public  val liveDataInvalidCode = MutableLiveData<Int>()


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
   suspend fun requestVerifyValidationCode(userPhone: String, Code: String, userName: String):LiveData<Int>{
    // var  checkCode:Int=7

         val liveData = MutableLiveData<Int>()



        val apiService: ApiInterface = ApiClient.getApiClient(ApiClient.BASE_URL)!!.create(ApiInterface::class.java)
        val call: Call<Int> = apiService.verifyValidationCode(userPhone, Code, userName)

             call.enqueue(object : Callback<Int> {
                 @SuppressLint("UseCompatLoadingForDrawables")

                 override fun onResponse(call: Call<Int>, response: Response<Int>) {

                     if (response.isSuccessful) {
                         if (response.body() == 1) {
                             //  verifyAccountType(userPhone,userName)
                             liveData.value = response.body()!!



                         } else {
//                        newUser_validCode_layout.isErrorEnabled = true
//                        newUser_validCode_layout.error = "کد نامعتبر است"
                             liveData.value = response.body()!!
                             liveDataInvalidCode.value=response.body()!!
                             Log.d("hexxxx", "onResponse: ${liveData.value}")
                             Log.d("rexxxx", "onResponse: ${liveDataInvalidCode.value}")


                         }
                     }
                 }

                 override fun onFailure(call: Call<Int>, t: Throwable) {
                     Log.d("hexxxx", "onResponse: " + t)
                 }

             })

             return liveData

    }

  public  fun getter():LiveData<Int>{
        return liveDataInvalidCode
    }


//
//    private fun verifyAccountType(userPhone: String,userName: String){
//
//
//
//        val apiService: ApiInterface = ApiClient.getApiClient(ApiClient.BASE_URL)!!.create(ApiInterface::class.java)
//        val call: Call<List<VerifyAccountModel>> = apiService.verifyAccount(userPhone)
//        call.enqueue(object : Callback<List<VerifyAccountModel>> {
//            @SuppressLint("UseCompatLoadingForDrawables")
//            override fun onResponse(call: Call<List<VerifyAccountModel>>, response: Response<List<VerifyAccountModel>>) {
//                if (response.isSuccessful) {
//                    if (response.body()!![0].acc_type == 1){
//                        Hawk.put("fullAccount",20)
//                    }else if (response.body()!![0].acc_type == 2 ){
//                        Hawk.put("goldenAccount",10)
//                    }
//                    Hawk.put("logIn", 1)
//                    Hawk.put("userName", userName)
//                    Hawk.put("userPhone", userPhone)
//                    HideSignInLayoutAnimation()
//                    initPage()
//                }
//            }
//
//            override fun onFailure(call: Call<List<VerifyAccountModel>>, t: Throwable) {
//                Toast.makeText(requireActivity(), "لطفا دوباره تلاش کنید", Toast.LENGTH_SHORT)
//                    .show()
//            }
//
//        })
//
//
//    }
//
//









}