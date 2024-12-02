package com.icc2024.recargapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.icc2024.recargapp.R
import com.icc2024.recargapp.ui.recharge.RechargeActivity

class ActionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_actions)

        val rechargeBtn = findViewById<Button>(R.id.btnRecharge)
        rechargeBtn.setOnClickListener {
            Log.v("ActionsActivity", "recarga")
            var intent = Intent(this, RechargeActivity::class.java)
            startActivity(intent);
        }


        val historyBtn = findViewById<Button>(R.id.btnHistory)
        historyBtn.setOnClickListener {
            Log.v("ActionsActivity", "historial")
        }


    }
}