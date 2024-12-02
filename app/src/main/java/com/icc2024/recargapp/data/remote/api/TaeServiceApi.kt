package com.icc2024.recargapp.data.remote.api
import retrofit2.http.Body
import retrofit2.http.POST

interface TaeServiceApi {
    @POST
    fun performTaeRecharge(
        @Body cadena : String,
        @Body numtel : String,
        @Body password: String,
        @Body skucode: String,
        @Body tienda: String,
        @Body usuario: String
    )
}