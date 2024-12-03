package com.icc2024.recargapp.presenter

import android.content.Context
import android.util.Log
import com.icc2024.recargapp.R
import com.icc2024.recargapp.contract.LoginContract
import com.icc2024.recargapp.data.manager.TracerDataManager
import com.icc2024.recargapp.data.model.LoginResponse
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class LoginPresenter(
    private val view: LoginContract.View,
    private val context: Context
) : LoginContract.Presenter {
    private val dataManager = TracerDataManager(context)
    override fun tryLogin(username: String, password: String) {
        Log.v("LoginPresenter.performRecharge", "attempting login recharge")
        val observable = dataManager.performLogin(username, password)
        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<LoginResponse>() {
                override fun onCompleted() {}
                override fun onError(e: Throwable?) {
                    Log.e("RechargeConfirmationPresenter.performRecharge", "error performing recharge")
                    e?.printStackTrace()

                    //Close dialog, transition to error screen
                    view.displayErrorMessage()

                }

                override fun onNext(t: LoginResponse?) {
                    //Login success
                    Log.i("LoginPresenter", t.toString())
                    handleSuccess(t!!)
                }

            })

    }

    fun handleSuccess(response : LoginResponse) {
        val prefs = context?.getSharedPreferences(context.getString(R.string.app_preference_file), Context.MODE_PRIVATE)
        prefs?.edit()?.apply {
            putBoolean(context.getString(R.string.pref_logged_in_key), true) // Set the desired value
            putString(context.getString(R.string.pref_cadena_key), response.cadena)
            putString(context.getString(R.string.pref_tienda_key), response.establecimiento)
            apply() // Apply the changes
        }
        view.changeToMainMenu()
    }
}