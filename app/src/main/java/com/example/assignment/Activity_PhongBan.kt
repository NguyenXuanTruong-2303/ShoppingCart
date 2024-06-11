package com.example.assignment

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment.Adapter.AdapterRoom
import com.example.assignment.Model.RoomModel


class Activity_PhongBan : AppCompatActivity() {
    lateinit var listRoom: ListView
    lateinit var customerArrayAdapter: AdapterRoom
    lateinit var backHome : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_phong_ban)

        listRoom = findViewById(R.id.listViewRoom)
        backHome = findViewById(R.id.back_room_home)

        val myArrayList = ArrayList<RoomModel>()

        var room1 = RoomModel(R.drawable.icon_pb,"Nhân Sự")
        var room2 = RoomModel(R.drawable.icon_pb,"Hành Chính")
        var room3 = RoomModel(R.drawable.icon_pb,"Đào Tạo")

        myArrayList.add(room1)
        myArrayList.add(room2)
        myArrayList.add(room3)


        customerArrayAdapter = AdapterRoom(this, myArrayList)
        listRoom.adapter = customerArrayAdapter




        backHome.setOnClickListener {
            val intent = Intent( this , Activity_Home::class.java)
            startActivity(intent)
        }

    }
}