package com.example.bridgekyctest

import android.util.Log
import android.webkit.JavascriptInterface
import com.example.bridgekyctest.KycData.ApiResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class BridgeActivity {
    @JavascriptInterface
    fun kycResultToAndroid() {

        val json = """
            {
              "review_result": {
                "id": 108,
                "request_time": "2022-06-22T02:31:03.441Z",
                "name": "권송미",
                "phone_number": "01058064018",
                "birthday": "1994-07-27",
                "result_type": 5,
                "result_email": 0,
                "result_sms": 0,
                "module": {
                  "id_card_ocr": true,
                  "id_card_verification": true,
                  "face_authentication": false,
                  "account_verification": false,
                  "liveness": false
                },
                "id_card": {
                  "modified": true,
                  "verified": true,
                  "id_card_image": "/9j/4AAQSkZJRgABAQEA...생략...",
                  "id_crop_image": null,
                  "original_ocr_data": "{\"idType\":\"1\",\"userName\":\"권송미\",\"juminNo1\":\"940727\",\"juminNo2\":\"2066010\",\"_juminNo2\":\"2******\",\"issueDate\":\"20191201\",\"transaction_id\":\"158515636462b27eff3730e1655865087\"}",
                  "modified_ocr_data": "{\"idType\":\"1\",\"userName\":\"권송미\",\"juminNo1\":\"940727\",\"juminNo2\":\"2066010\",\"_juminNo2\":\"2******\",\"issueDate\":\"20191211\",\"transaction_id\":\"158515636462b27eff3730e1655865087\"}",
                  "id_card_origin": "/9j/4AAQSkZJRgABAQAA...생략...",
                  "is_manual_input": false,
                  "uploaded_type": "camera",
                  "is_uploaded": false
                },
                "face_check": null,
                "account": null
              },
              "api_response": {
                "result_code": "N100",
                "result_message": "OK."
              },
              "result": "success"
            }
        """.trimIndent()


        try {
            val jsonObject = JSONObject(json)

            val reviewResult = jsonObject.getJSONObject("review_result")
            val id = reviewResult.getString("id")
            val requestTime = reviewResult.getString("request_time")
            val name = reviewResult.getString("name")
            val idCard = reviewResult.getJSONObject("id_card")
            val originalOcrData = idCard.getJSONObject("original_ocr_data")

            val apiResponse = jsonObject.getJSONObject("api_response")
            val resultMessage = jsonObject.getString("result_message")

            val result = jsonObject.getString("result")

            Log.d("Review Result 결과 : " , reviewResult.toString())


            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(20000L, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://aml-api-dev.rootone.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(Service::class.java)

            service.getData().enqueue(object : Callback<ApiResponse>{
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful) {
                        val total = response.body()
                        Log.d("Success Body : ", total.toString())


                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })


        } catch (e: Exception) {
            e.printStackTrace()
        }


    }




}