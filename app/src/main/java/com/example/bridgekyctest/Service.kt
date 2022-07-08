package com.example.bridgekyctest

import com.example.bridgekyctest.KycData.ApiResponse
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Service {

    @FormUrlEncoded
    @POST("/kyc/insert/")
    fun getData() : Call<ApiResponse>
}