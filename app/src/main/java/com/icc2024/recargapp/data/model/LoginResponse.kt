package com.icc2024.recargapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
class LoginResponse(
    val establecimiento : String,
    val cadena : String
) : Parcelable