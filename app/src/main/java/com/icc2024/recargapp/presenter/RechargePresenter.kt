package com.icc2024.recargapp.presenter

import android.content.Context
import android.util.Log
import com.icc2024.recargapp.R
import com.icc2024.recargapp.contract.RechargeContract
import com.icc2024.recargapp.data.dto.RechargeRequestDto
import com.icc2024.recargapp.data.dto.RechargeSkuDto
import com.icc2024.recargapp.data.manager.SkuDataManager


class RechargePresenter (
    private val view: RechargeContract.View,
    private val context: Context) : RechargeContract.Presenter {

    private var skus : List<RechargeSkuDto>? = null
    private val companies = listOf("TELCEL", "MOVISTAR", "AT&T", "UNEFON", "VIRGIN", "NEXTER", "ALO")

    private var selectedCompanyIdx = 0;
    private var selectedSku : RechargeSkuDto? = null;



    override fun loadInitialData() {
        skus = SkuDataManager.instance.getAllSkus(context.resources)
        view.populateCompaniesSpinner(companies)
    }

    override fun selectCompany(selected : String) {
        selectedCompanyIdx = companies.indexOf(selected);
        Log.i("RechargePresenter", "selected company index $selectedCompanyIdx")
        val selectedCompany = companies[selectedCompanyIdx]
        val filteredSkus = skus?.filter { sku ->
            sku.nombre.contains(selectedCompany, ignoreCase = true)
        }
        val list : List<String>? = filteredSkus?.map { sku -> sku.nombre }
        view.populatePackageSpinner(list!!)
    }

    override fun selectRechargeSku(selected: String) {
        val selectedObj = skus?.find { sku -> sku.nombre == selected }
        selectedSku = selectedObj;
        view.setPriceLabel(selectedObj?.monto!!)
    }

    override fun promptConfirmation(number: String) {
        val prefs = context.getSharedPreferences(context.getString(R.string.app_preference_file), Context.MODE_PRIVATE )
        val user = prefs.getString(context.getString(R.string.pref_user_key), "")
        val pass = prefs.getString(context.getString(R.string.pref_password_key), "")
        val store = prefs.getString(context.getString(R.string.pref_tienda_key), "")
        val chain = prefs.getString(context.getString(R.string.pref_cadena_key), "")
        var request = RechargeRequestDto(
            numtel = number,
            skucode = selectedSku?.sku!!,
            usuario = user!!,
            password = pass!!,
            tienda = store!!,
            cadena = chain!!
        )

        view.changeFragment(request)
    }


}