package com.icc2024.recargapp.contract

interface RechargeConfirmationContract {
    interface View {
        fun updateNumberLabel(value: String)
        fun updateRechargeAmountLabel(value: String)
    }

    interface Presenter {
        fun initializeUi()
        fun performRecharge()
    }
}