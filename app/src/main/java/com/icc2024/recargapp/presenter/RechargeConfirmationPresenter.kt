package com.icc2024.recargapp.presenter

import android.content.Context
import android.util.Log
import com.icc2024.recargapp.contract.RechargeConfirmationContract
import com.icc2024.recargapp.data.dto.RechargeRequestDto
import com.icc2024.recargapp.data.manager.DataManager
import com.icc2024.recargapp.data.manager.SkuDataManager
import com.icc2024.recargapp.data.model.RechargeResponse
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class RechargeConfirmationPresenter (
    private val view: RechargeConfirmationContract.View,
    private val context: Context,
    private val rechargeData : RechargeRequestDto,
) : RechargeConfirmationContract.Presenter {

    private val dataManager = DataManager(context)

    override fun initializeUi() {
        val skuObj = SkuDataManager.instance.getAllSkus(context.resources).find { obj -> obj.sku == rechargeData.skucode }
        view.updateRechargeAmountLabel(skuObj?.monto!!)
        view.updateNumberLabel(rechargeData.numtel)
    }

    override fun performRecharge() {
        Log.v("RechargeConfirmationPresenter.performRecharge", "attempting TAE recharge")
        val observable = dataManager.performRechargeRequest(rechargeData)
        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<RechargeResponse>() {
                override fun onCompleted() {}
                override fun onError(e: Throwable?) {
                    Log.e("RechargeConfirmationPresenter.performRecharge", "error performing recharge")
                    e?.printStackTrace()

                    //Close dialog, transition to error screen
                    view.changeToErrorScreen()

                }

                override fun onNext(t: RechargeResponse?) {
                    //Change scene
                    Log.i("RechargeConfirmationPresenter.performRecharge.observer.onNext", t.toString())
                    view.changeToSuccessScreen(t!!)
                }

            })

    }
}