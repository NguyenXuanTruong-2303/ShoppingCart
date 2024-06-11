package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment.Database.DataBaseStaff
import com.example.assignment.Model.StaffModel

class Activity_Add_Staff : AppCompatActivity() {
    lateinit var btn_add : Button
    lateinit var btn_back : Button
    lateinit var txt_staff_name : EditText
    lateinit var txt_staff_id  : EditText
    lateinit var txt_staff_room : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_staff)

        btn_add = findViewById(R.id.btn_staff_add)
        btn_back = findViewById(R.id.btn_back_staff)
        txt_staff_id = findViewById(R.id.edt_staff_id)
        txt_staff_name = findViewById(R.id.edt_staff_name)
        txt_staff_room = findViewById(R.id.edt_staff_room)

        btn_add.setOnClickListener {
            var staff : StaffModel
            try {
                staff = StaffModel(txt_staff_id.getText().toString(),txt_staff_name.getText().toString(),txt_staff_room.getText().toString())
                Toast.makeText(this, staff.toString(),Toast.LENGTH_LONG).show();
                var dataBaseStaff = DataBaseStaff(this)
                var succes : Boolean = dataBaseStaff.addOne(staff)
                val i = Intent (this,Activity_Staff::class.java)
                startActivity(i)
            }
            catch (e : Exception) {
                Toast.makeText(this,"Error creating staff",Toast.LENGTH_LONG).show();
            }

        }

        btn_back.setOnClickListener {
            val i = Intent (this,Activity_Staff::class.java)
            startActivity(i)
        }

    }
}