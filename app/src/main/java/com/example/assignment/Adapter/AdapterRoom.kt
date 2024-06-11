package com.example.assignment.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.assignment.Model.RoomModel
import com.example.assignment.R

class AdapterRoom(val context : Context,val list : List<RoomModel>) : BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view  : View

        if (convertView == null) {
            view = inflater.inflate(R.layout.item_phongban,parent,false)
        } else {
            view = convertView
        }

        val item = getItem(position) as RoomModel

        val textView = view.findViewById<TextView>(R.id.name_room)
        val imgView  = view.findViewById<ImageView>(R.id.img_roon)

        textView.text = item.nameRoom
        imgView.setImageResource(item.img)

        return view
    }

}