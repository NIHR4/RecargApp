package com.icc2024.recargapp.data.remote.api

import com.icc2024.recargapp.data.model.LoginResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import rx.Observable

interface TracerServiceApi {
    @GET("/authenticate")
    fun performLogin(@Query("username") username : String, @Query("password")password : String) : Observable<LoginResponse>

}