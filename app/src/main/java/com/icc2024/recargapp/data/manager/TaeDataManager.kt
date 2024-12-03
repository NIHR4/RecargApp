package com.icc2024.recargapp.data.manager

import android.content.Context
import com.icc2024.recargapp.data.dto.RechargeRequestDto
import com.icc2024.recargapp.data.model.RechargeResponse
import com.icc2024.recargapp.data.remote.api.TaeServiceApi
import com.icc2024.recargapp.data.remote.client.TaeServiceGenerator
import rx.Observable

class TaeDataManager(val context: Context) {

    fun performRechargeRequest(request : RechargeRequestDto) : Observable<RechargeResponse> {
        return TaeServiceGenerator.createService(TaeServiceApi::class.java, context)
            .performTaeRecharge(request)

    }

}