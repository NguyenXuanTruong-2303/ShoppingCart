package com.example.assignment.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.assignment.Activity_Add_Staff
import com.example.assignment.Activity_Home
import com.example.assignment.Activity_Staff
import com.example.assignment.Activity_Staff_Update
import com.example.assignment.Database.DataBaseStaff
import com.example.assignment.Model.StaffModel
import com.example.assignment.R
import java.io.Serializable

class AdapterStaff(val context: Context, var list: List<StaffModel>): BaseAdapter() {

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): StaffModel {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view  : View

        if (convertView == null) {
            view = inflater.inflate(R.layout.item_staff,parent,false)
        } else {
            view = convertView
        }

        val item = getItem(position) as StaffModel

        val textViewId = view.findViewById<TextView>(R.id.txt_staff_id)
        val textViewName  = view.findViewById<TextView>(R.id.txt_staff_name)
        val textViewRoom = view.findViewById<TextView>(R.id.txt_staff_room)
        val imgEdit = view.findViewById<ImageView>(R.id.imgEdit)
        val imgDelete = view.findViewById<ImageView>(R.id.imgDelete)

        textViewId.text = item.id
        textViewName.text = item.staffName
        textViewRoom.text = item.staffRoom
        imgEdit.setImageResource(R.drawable.pencil)
        imgDelete.setImageResource(R.drawable.trash)

        imgEdit.setOnClickListener {
            val intent = Intent(context, Activity_Staff_Update::class.java)
            intent.putExtra("updateStaff", getItem(position) as StaffModel)
            context.startActivity(intent)
        }

        imgDelete.setOnClickListener {
            var staff : StaffModel
            try {
                staff = getItem(position)
                var dataBaseStaff = DataBaseStaff(context)
                var succes : Boolean = dataBaseStaff.deleteOne(staff)

                var intent = Intent(context, Activity_Home::class.java)
                context.startActivity(intent)

                Toast.makeText(context,"Xóa thành công", Toast.LENGTH_LONG).show();
            }
            catch (e : Exception) {
                Toast.makeText(context,"Error", Toast.LENGTH_LONG).show();
            }
        }

        return view
    }


}