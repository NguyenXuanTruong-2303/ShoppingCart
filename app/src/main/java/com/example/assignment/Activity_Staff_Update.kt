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

class Activity_Staff_Update : AppCompatActivity() {
    lateinit var btn_staff_update : Button
    lateinit var btn_back_update : Button
    lateinit var edt_update_name : EditText
    lateinit var edt_update_id  : EditText
    lateinit var edt_update_room : EditText

    lateinit var staffData: StaffModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_staff_update)

        btn_staff_update = findViewById(R.id.btn_staff_update)
        btn_back_update = findViewById(R.id.btn_back_update)
        edt_update_name = findViewById(R.id.edt_update_name)
        edt_update_id = findViewById(R.id.edt_update_id)
        edt_update_room = findViewById(R.id.edt_update_room)

        val intent = intent
        if (intent.hasExtra("updateStaff")) {
            staffData = intent.getSerializableExtra("updateStaff") as StaffModel
            edt_update_id.setText(staffData.id.toString())
            edt_update_name.setText(staffData.staffName.toString())
            edt_update_room.setText(staffData.staffRoom.toString())
        }

        btn_back_update.setOnClickListener {
            val i = Intent (this,Activity_Staff::class.java)
            startActivity(i)
        }

        btn_staff_update.setOnClickListener {
            var staff : StaffModel
            try {
                staff = StaffModel(edt_update_id.getText().toString(), edt_update_name.getText().toString(),edt_update_room.getText().toString())
                var dataBaseStaff = DataBaseStaff(this)
                var succes : Boolean = dataBaseStaff.updateOne(staff)
                val i = Intent (this,Activity_Staff::class.java)
                startActivity(i)
            }
            catch (e : Exception) {
                Toast.makeText(this,"Error update` staff",Toast.LENGTH_LONG).show();
            }
        }

    }
}