package com.shakir.thewellnessroom

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val name: String,
    val cost: Double,
    val count: Int
) : Parcelable