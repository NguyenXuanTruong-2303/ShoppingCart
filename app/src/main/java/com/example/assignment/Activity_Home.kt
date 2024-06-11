package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Activity_Home : AppCompatActivity() {
    lateinit var btn_phongban : Button
    lateinit var btn_nhanvien : Button
    lateinit var btn_exit : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        btn_phongban = findViewById(R.id.btn_phongban)
        btn_nhanvien = findViewById(R.id.btn_nhanvien)
        btn_exit = findViewById(R.id.btn_exit)

        btn_phongban.setOnClickListener {
            var intent : Intent = Intent(this, Activity_PhongBan::class.java)
            startActivity(intent)
        }

        btn_nhanvien.setOnClickListener {
            var intent : Intent = Intent (this, Activity_Staff::class.java)
            startActivity(intent)
        }

    }
}