package com.icc2024.recargapp.presenter

import android.content.Context
import com.icc2024.recargapp.contract.RechargeConfirmationContract
import com.icc2024.recargapp.data.dto.RechargeRequest

class RechargeConfirmationPresenter (
    private val view: RechargeConfirmationContract.View,
    private val context: Context,
    private val data : RechargeRequest
) : RechargeConfirmationContract.Presenter {

    override fun initializeUi() {
        view.updateRechargeAmountLabel(data.sku.monto)
        view.updateNumberLabel(data.phoneNumber)
    }

    override fun performRecharge() {
        TODO("Not yet implemented")
    }
}