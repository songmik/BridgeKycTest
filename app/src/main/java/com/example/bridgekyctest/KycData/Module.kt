package com.example.bridgekyctest.KycData


import com.google.gson.annotations.SerializedName

data class Module(
    @SerializedName("account_verification")
    val accountVerification: Boolean?,
    @SerializedName("face_authentication")
    val faceAuthentication: Boolean?,
    @SerializedName("id_card_ocr")
    val idCardOcr: Boolean?,
    @SerializedName("id_card_verification")
    val idCardVerification: Boolean?,
    @SerializedName("liveness")
    val liveness: Boolean?
)