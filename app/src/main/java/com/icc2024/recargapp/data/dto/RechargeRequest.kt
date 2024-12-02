package com.icc2024.recargapp.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class RechargeRequest(
    var phoneNumber : String,
    var sku : RechargeSku,
    var user : String,
    var password : String,
    var store_id : String,
    var chain_id : String,
) : Parcelable
