package com.icc2024.recargapp.ui.dialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import com.icc2024.recargapp.R

class LoadingDialog (
    val activity : Activity
) {

    var dialog : AlertDialog? = null
    fun startLoadingDialog() {
        val builder = AlertDialog.Builder(activity)

        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.loading_dialog, null))
        builder.setCancelable(false);

        dialog = builder.create()
        dialog?.show()
    }

    fun dismissDialog() {
        dialog?.dismiss()
    }

}