package com.example.bridgekyctest.KycData


import com.google.gson.annotations.SerializedName

data class ReviewResult(
    @SerializedName("account")
    val account: Any?,
    @SerializedName("birthday")
    val birthday: String?,
    @SerializedName("face_check")
    val faceCheck: Any?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("id_card")
    val idCard: IdCard?,
    @SerializedName("module")
    val module: Module?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phone_number")
    val phoneNumber: String?,
    @SerializedName("request_time")
    val requestTime: String?,
    @SerializedName("result_email")
    val resultEmail: Int?,
    @SerializedName("result_sms")
    val resultSms: Int?,
    @SerializedName("result_type")
    val resultType: Int?
)