package com.icc2024.recargapp.data.model;

import android.os.Parcel
import android.os.Parcelable;
import kotlinx.parcelize.Parcelize

@Parcelize
class TransactionResponse(
    val codigo_respuesta : String,
    val descripcion_respuesta: String,
    val fecha_hora: String,
    val fecha_hora_respuesta: String,
    val fecha_hora_solicitud: String,
    val monto: String,
    val no_autorizacion: String,
    val numtel: String
) : Parcelable


