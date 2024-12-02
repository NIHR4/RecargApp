package com.icc2024.recargapp.ui.recharge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.icc2024.recargapp.R
import com.icc2024.recargapp.data.dto.RechargeRequest

class RechargeActivity : AppCompatActivity(R.layout.activity_recharge), RechargeFragment.Callbacks {

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

    override fun showConfirmationScreen(request: RechargeRequest) {
        val fragment = RechargeConfirmationFragment.newInstance(request)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, fragment)
            .addToBackStack("actions_fragment")
            .commit()
    }

}