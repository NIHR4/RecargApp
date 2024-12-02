package com.icc2024.recargapp.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class RechargeRequestDto(
    var numtel : String,
    var skucode : String,
    var usuario : String,
    var password : String,
    var tienda : String,
    var cadena : String,
) : Parcelable
