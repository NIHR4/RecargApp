package com.icc2024.recargapp.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.icc2024.recargapp.R


class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_history)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar: Toolbar = findViewById(com.icc2024.recargapp.R.id.toolbar)
        setSupportActionBar(toolbar)


        // Enable the back button
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        // Change the back button icon
        //supportActionBar!!.setHomeAsUpIndicator(com.icc2024.recargapp.R.drawable.ic_circle_left)
    }
}