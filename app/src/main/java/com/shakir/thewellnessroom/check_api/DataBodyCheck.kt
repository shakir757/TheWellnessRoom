package com.shakir.thewellnessroom.check_api

data class DataBodyCheck(
    val fn: String,
    val fd: String,
    val fp: String,
    val n: String, //int
    val s: String, //double
    val t: String,
    val qr: String, //int
    val token: String
)