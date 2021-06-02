package com.shakir.thewellnessroom.check_api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataCheck(
    @SerializedName("json")
    val json: JsonCheck,
    @SerializedName("html")
    val html: String
) : Parcelable