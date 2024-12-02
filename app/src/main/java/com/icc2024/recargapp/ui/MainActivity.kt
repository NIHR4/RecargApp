package com.icc2024.recargapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.icc2024.recargapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val prefs = this.getSharedPreferences(getString(R.string.app_preference_file), Context.MODE_PRIVATE )
        val logged = prefs.getBoolean(getString(R.string.pref_logged_in_key), false);

        if(logged) {

        }else{
            var loginIntent = Intent(this, LoginActivity::class.java)
            loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME)
            startActivity(loginIntent)

        }
        finish()
    }
}