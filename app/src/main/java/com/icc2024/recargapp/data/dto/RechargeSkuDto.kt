package com.icc2024.recargapp.data.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class RechargeSkuDto (
    @SerializedName("Sku:Nombre") val nombre: String,
    @SerializedName("Sku:Sku") val sku: String,
    @SerializedName("Sku:Monto") val monto: String,
    @SerializedName("Sku:Info") val info: String,
    @SerializedName("Sku:Costo") val costo: String
) : Parcelable