package com.icc2024.recargapp.data.manager

import android.content.res.Resources
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.icc2024.recargapp.R
import com.icc2024.recargapp.data.dto.RechargeSkuDto
import java.io.InputStreamReader

class SkuDataManager private constructor(){

    private var skuDtos : List<RechargeSkuDto>? = null

    companion object {
        val instance by lazy {
            SkuDataManager()
        }
    }

    fun getAllSkus(resources: Resources): List<RechargeSkuDto> {
        if(skuDtos == null) {
            val inputStream = resources.openRawResource(R.raw.sku_data)
            val reader = InputStreamReader(inputStream)
            val type = object : TypeToken<List<RechargeSkuDto>>() {}.type
            skuDtos =  Gson().fromJson(reader, type)
        }
        return skuDtos!!;

    }


}