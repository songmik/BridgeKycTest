package com.example.bridgekyctest

import android.content.Context
import android.content.Intent
import android.util.Log
import android.webkit.JavascriptInterface
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
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


/*

 안드로이드 webView에서 정보를 가져오기 위해, AndroidBridge로 Web(JavaScript)와 연결한다. (In MainActivity)
 1, Javascript와 연결된 함수에서 정보를 String으로 가져오기
 2. 가져온 String을 JsonObject로 만들고 파싱한다
 3. 검증 실패 했을 때, 오류코드
 4. 검증 성공 했을 때, MoveActivity로 이동
 5. 검증이 끝나면 서버로 데이터를 전송





*/


class BridgeActivity(val mContext: Context) {


    // 1. Javascript와 연결된 함수에서 정보를 String으로 가져오기
    @JavascriptInterface
    fun kycResultToAndroid(json : String) {


        // 2. 가져온 String을 JsonObject로 만들고 파싱한다
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



            // 3. 검증 실패 했을 때, 오류코드

            if (result.toString() == "fail") {
                val intent = Intent(mContext, MoveActivity::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(this, "인증을 다시 시도해주세요.",Toast.LENGTH_SHORT).show()
            }



            // 4. 검증 성공 했을 때, MoveActivity로 이동


            // 5. 검증이 끝나면 서버로 데이터를 전송



        } catch (e: Exception) {
            e.printStackTrace()
        }


    }




}