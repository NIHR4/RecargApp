package com.icc2024.recargapp.contract

import com.icc2024.recargapp.data.model.RechargeResponse

interface RechargeConfirmationContract {
    interface View {
        fun updateNumberLabel(value: String)
        fun updateRechargeAmountLabel(value: String)
        fun changeToSuccessScreen(rechargeResponse: RechargeResponse)
        fun changeToErrorScreen()
    }

    interface Presenter {
        fun initializeUi()
        fun performRecharge()
    }
}