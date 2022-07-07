package com.example.bridgekyctest.KycData


import com.google.gson.annotations.SerializedName

data class ResultData(
    @SerializedName("api_response")
    val apiResponse: ApiResponse?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("review_result")
    val reviewResult: ReviewResult?
)