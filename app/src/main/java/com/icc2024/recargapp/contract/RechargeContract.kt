package com.icc2024.recargapp.contract

import com.icc2024.recargapp.data.dto.RechargeRequestDto

interface RechargeContract {
    interface  View {
        fun populateCompaniesSpinner(values: List<String>)
        fun populatePackageSpinner(values : List<String>)
        fun setPriceLabel(price: String)
        fun changeFragment(req : RechargeRequestDto)
    }

    interface Presenter {
        fun loadInitialData()
        fun selectCompany(selected : String)
        fun selectRechargeSku(selected: String)
        fun promptConfirmation(number : String)

    }
}