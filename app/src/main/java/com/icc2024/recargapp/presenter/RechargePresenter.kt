package com.icc2024.recargapp.presenter

import android.content.Context
import android.content.res.Resources
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.icc2024.recargapp.R
import com.icc2024.recargapp.contract.RechargeContract
import com.icc2024.recargapp.data.dto.RechargeRequest
import com.icc2024.recargapp.data.dto.RechargeSku
import java.io.InputStreamReader


class RechargePresenter (
    private val view: RechargeContract.View,
    private val context: Context) : RechargeContract.Presenter {

    private var skus : List<RechargeSku>? = null
    private val companies = listOf("TELCEL", "MOVISTAR", "AT&T", "UNEFON", "VIRGIN", "NEXTER", "ALO")

    private var selectedCompanyIdx = 0;
    private var selectedSku : RechargeSku? = null;

    fun readJsonArray(resources: Resources, resourceId: Int): List<RechargeSku> {
        val inputStream = resources.openRawResource(resourceId)
        val reader = InputStreamReader(inputStream)
        val type = object : TypeToken<List<RechargeSku>>() {}.type
        return Gson().fromJson(reader, type)
    }


    override fun loadInitialData() {
        skus = readJsonArray(context.resources, R.raw.sku_data)
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
        var request = RechargeRequest(
            phoneNumber = number,
            sku = selectedSku!!,
            user = user!!,
            password = pass!!,
            store_id = store!!,
            chain_id = chain!!
        )

        view.changeFragment(request)
    }


}