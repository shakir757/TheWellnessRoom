package com.shakir.thewellnessroom.check

import androidx.lifecycle.ViewModel
import com.shakir.thewellnessroom.check_api.DataBodyCheck
import com.shakir.thewellnessroom.check_api.JsonCheck
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CheckViewModel : ViewModel() {

    private val checkInteractor = CheckInteractor()

    fun fetchNewCheck(
        dataBodyCheck: DataBodyCheck,
        successCallback: (JsonCheck) -> Unit,
        errorCallback: (Throwable) -> Unit
    ) {
        checkInteractor.getNewCheck(dataBodyCheck)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    successCallback(it)
                },
                {
                    errorCallback(it)
                }
            )
    }
}