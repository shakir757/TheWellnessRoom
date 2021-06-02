package com.shakir.thewellnessroom.check

import android.util.Log
import com.shakir.thewellnessroom.BuildConfig
import com.shakir.thewellnessroom.check_api.CheckApi
import com.shakir.thewellnessroom.check_api.DataBodyCheck
import com.shakir.thewellnessroom.check_api.JsonCheck
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://proverkacheka.com/"

class CheckInteractor {
    private var checkApi: CheckApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(
            OkHttpClient().newBuilder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    if (BuildConfig.DEBUG) {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                }).build())
        .build()
        .create(CheckApi::class.java)

    fun getNewCheck(dataBodyCheck: DataBodyCheck): Single<JsonCheck> {
        return Single.fromObservable(
            checkApi.getCheckInfo(
                dataBodyCheck.fn,
                dataBodyCheck.fd,
                dataBodyCheck.fp,
                dataBodyCheck.n,
                dataBodyCheck.s,
                dataBodyCheck.t,
                dataBodyCheck.qr,
                dataBodyCheck.token
            ).map {
                Log.d("Check", "check: " + it.data.json.toString())
                it.data.json
            }
        )
    }
}