package com.icc2024.recargapp.data.model;

import android.os.Parcel
import android.os.Parcelable;

class RechargeResponse(
    val numtel: String?,
    val monto: Float?,
    val fechaHora: String?,
    val fechaHoraRespuesta: String?,
    val fechaHoraSolicitud: String?,
    val codigoRespuesta : String?,
    val descripcionRespuesta : String?,
    val respuesta: String?,
    val noAutorizado: String?) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readFloat(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()

        ){

        }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(numtel)
        parcel.writeValue(monto)
        parcel.writeString(fechaHora)
        parcel.writeString(fechaHoraRespuesta)
        parcel.writeString(fechaHoraSolicitud)
        parcel.writeString(codigoRespuesta)
        parcel.writeString(descripcionRespuesta)
        parcel.writeString(respuesta)
        parcel.writeString(noAutorizado)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RechargeResponse> {
        override fun createFromParcel(parcel: Parcel): RechargeResponse {
            return RechargeResponse(parcel)
        }

        override fun newArray(size: Int): Array<RechargeResponse?> {
            return arrayOfNulls(size)
        }
    }
}
