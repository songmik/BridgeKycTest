package com.example.bridgekyctest.KycData


import com.google.gson.annotations.SerializedName

data class IdCard(
    @SerializedName("id_card_image")
    val idCardImage: String?,
    @SerializedName("id_card_origin")
    val idCardOrigin: String?,
    @SerializedName("id_crop_image")
    val idCropImage: Any?,
    @SerializedName("is_manual_input")
    val isManualInput: Boolean?,
    @SerializedName("is_uploaded")
    val isUploaded: Boolean?,
    @SerializedName("modified")
    val modified: Boolean?,
    @SerializedName("modified_ocr_data")
    val modifiedOcrData: String?,
    @SerializedName("original_ocr_data")
    val originalOcrData: String?,
    @SerializedName("uploaded_type")
    val uploadedType: String?,
    @SerializedName("verified")
    val verified: Boolean?
)