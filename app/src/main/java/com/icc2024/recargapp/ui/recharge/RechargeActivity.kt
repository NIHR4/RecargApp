package com.icc2024.recargapp.ui.recharge

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.icc2024.recargapp.R
import com.icc2024.recargapp.data.dto.RechargeRequestDto
import com.icc2024.recargapp.data.model.RechargeResponse

class RechargeActivity : AppCompatActivity(R.layout.activity_recharge), RechargeFragment.Callbacks, RechargeConfirmationFragment.Callbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view)
        if(currentFragment == null) {
            val fragment = RechargeFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container_view, fragment)
                .commit()
        }
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount > 0 ){
            supportFragmentManager.popBackStack()
        }else{
            super.onBackPressed()
        }

    }

    override fun showConfirmationScreen(request: RechargeRequestDto) {
        val fragment = RechargeConfirmationFragment.newInstance(request)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun changeToSuccessScreen(response: RechargeResponse) {


        while (supportFragmentManager.getBackStackEntryCount() > 0) {
            supportFragmentManager.popBackStackImmediate();
        }

        val fragment = TransactionSuccessFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, fragment)
            .commit()
    }

    override fun changeToErrorScreen() {
        Toast.makeText(this, "Conexion fallida", Toast.LENGTH_SHORT);
        finish()
    }

}