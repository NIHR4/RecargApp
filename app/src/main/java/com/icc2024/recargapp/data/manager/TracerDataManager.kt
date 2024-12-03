package com.icc2024.recargapp.data.manager

import android.content.Context
import android.os.Parcelable
import com.icc2024.recargapp.data.model.LoginResponse
import com.icc2024.recargapp.data.remote.api.TracerServiceApi
import com.icc2024.recargapp.data.remote.client.TracerServiceGenerator
import kotlinx.parcelize.Parcelize
import rx.Observable


class TracerDataManager (val context: Context) {
    fun performLogin(username : String, password: String) : Observable<LoginResponse> {
            return TracerServiceGenerator.createService(TracerServiceApi::class.java, context)
                .performLogin(username, password)
    }
}