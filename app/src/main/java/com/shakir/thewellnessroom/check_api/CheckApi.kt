package com.shakir.thewellnessroom.check_api

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface CheckApi {

    @FormUrlEncoded
    @POST("api/v1/check/get")
    fun getCheckInfo(
        @Field("fn") fn: String,
        @Field("fd") fd: String,
        @Field("fp") fp: String,
        @Field("n") n: String,
        @Field("s") s: String,
        @Field("t") t: String,
        @Field("qr") qr: String,
        @Field("token") token: String,
    ) : Observable<BaseCheck>
}