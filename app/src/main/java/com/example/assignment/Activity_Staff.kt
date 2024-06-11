package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment.Adapter.AdapterStaff
import com.example.assignment.Database.DataBaseStaff
import com.example.assignment.Model.StaffModel


class Activity_Staff : AppCompatActivity() {
    lateinit var listViewStaff: ListView
    lateinit var customerArrayAdapter: AdapterStaff
    lateinit var backHome : ImageView
    lateinit var btn_add : Button
    lateinit var dataBaseStaff: DataBaseStaff

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_staff)

        listViewStaff = findViewById(R.id.listViewStaff)
        backHome = findViewById(R.id.back_staff_home)
        btn_add = findViewById(R.id.btn_add_staff)

        dataBaseStaff = DataBaseStaff(this@Activity_Staff )
        ShowCustomersOnListView(dataBaseStaff)


        btn_add.setOnClickListener {
            val intent = Intent( this , Activity_Add_Staff::class.java)
            startActivity(intent)
        }


        backHome.setOnClickListener {
            val intent = Intent( this , Activity_Home::class.java)
            startActivity(intent)
        }

    }


    private fun ShowCustomersOnListView( dataBaseStaff: DataBaseStaff) {
        customerArrayAdapter = AdapterStaff(this, dataBaseStaff.getEveryOne())
        listViewStaff.adapter = customerArrayAdapter

    }
    
}