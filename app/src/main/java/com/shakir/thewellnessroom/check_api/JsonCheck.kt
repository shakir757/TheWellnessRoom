package com.shakir.thewellnessroom.check_api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class JsonCheck(
    @SerializedName("code")
    val code: Int,
    @SerializedName("user")
    val user: String,
    @SerializedName("items")
    val items: List<ItemCheck>,
    @SerializedName("nds18")
    val nds18: Int,
    @SerializedName("fnsUrl")
    val fnsUrl: String,
    @SerializedName("userInn")
    val userInn: String,
    @SerializedName("dateTime")
    val dateTime: String,
    @SerializedName("kktRegId")
    val kktRegId: String,
    @SerializedName("operator")
    val operator: String,
    @SerializedName("totalSum")
    val totalSum: Int,
    @SerializedName("creditSum")
    val creditSum: Int,
    @SerializedName("fiscalSign")
    val fiscalSign: Int,
    @SerializedName("prepaidSum")
    val prepaidSum: Int,
    @SerializedName("retailPlace")
    val retailPlace: String,
    @SerializedName("shiftNumber")
    val shiftNumber: Int,
    @SerializedName("cashTotalSum")
    val cashTotalSum: Int,
    @SerializedName("provisionSum")
    val provisionSum: Int,
    @SerializedName("eCashTotalSum")
    val eCashTotalSum: Int,
    @SerializedName("operationType")
    val operationType: Int,
    @SerializedName("requestNumber")
    val requestNumber: Int,
    @SerializedName("sellerAddress")
    val sellerAddress: String,
    @SerializedName("fiscalDriveNumber")
    val fiscalDriveNumber: String,
    @SerializedName("retailPlaceAddress")
    val retailPlaceAddress: String,
    @SerializedName("fiscalDocumentNumber")
    val fiscalDocumentNumber: Int,
    @SerializedName("fiscalDocumentFormatVer")
    val fiscalDocumentFormatVer: Int
) : Parcelable