package com.icc2024.recargapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.icc2024.recargapp.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        var btn = findViewById<Button>(R.id.btnLogin)
        btn.setOnClickListener {
            val intent = Intent(this, ActionsActivity::class.java )
            val user = findViewById<EditText>(R.id.etUsername)

            if(user.text.toString() == "demo") {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME)
                startActivity(intent);
                finish()
            }else {
                Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
            }


        }
    }
}