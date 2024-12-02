package com.icc2024.recargapp.data.remote.api
import com.icc2024.recargapp.data.dto.RechargeRequestDto
import com.icc2024.recargapp.data.model.RechargeResponse
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface TaeServiceApi {
    @POST("/recarga")
    fun performTaeRecharge (@Body request : RechargeRequestDto) : Observable<RechargeResponse>
}