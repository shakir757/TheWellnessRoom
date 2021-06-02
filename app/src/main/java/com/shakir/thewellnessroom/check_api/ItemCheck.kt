package com.shakir.thewellnessroom.check_api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemCheck(
    @SerializedName("nds")
    val nds: Int,
    @SerializedName("sum")
    val sum: Int,
    @SerializedName("name")
    val productName: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("paymentType")
    val paymentType: Int,
    @SerializedName("productType")
    val productType: Int,
) : Parcelable