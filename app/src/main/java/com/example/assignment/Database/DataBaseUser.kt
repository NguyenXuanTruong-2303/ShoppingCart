package com.example.assignment.Database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.assignment.Model.LoginUserModel
import org.jetbrains.annotations.Nullable

private const val USER_TABLE = "USER_TABLE"

private const val COLUMN_USER_ID = "ID"

private const val COLUMN_USER_NAME = "USER_NAME"

private const val COLUMN_USER_PASSWORD = "USER_PASSWORD"

class DataBaseUser(context : Context) : SQLiteOpenHelper(context,"user.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableStatement : String = "CREATE TABLE $USER_TABLE ($COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_USER_NAME TEXT, $COLUMN_USER_PASSWORD TEXT)"
        db?.execSQL(createTableStatement)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun addOne(loginUser: LoginUserModel) : Boolean {
        val db : SQLiteDatabase = writableDatabase
        val cv : ContentValues = ContentValues()

        cv.put(COLUMN_USER_NAME,loginUser.username)
        cv.put(COLUMN_USER_PASSWORD,loginUser.password)

        val insert : Long = db.insert(USER_TABLE, null ,cv)

        if ( insert == (-1).toLong()){
            return false
        } else {
            return true
        }
    }

    fun getUser(username : String) : LoginUserModel {
        var queryString : String = "SELECT * FROM $USER_TABLE WHERE $COLUMN_USER_NAME  = '$username' "
        var db : SQLiteDatabase = readableDatabase

        var cursor : Cursor = db.rawQuery(queryString, null)


        cursor.moveToFirst()
        var user_id : Int = cursor.getInt(0)
        var user_name : String = cursor.getString(1)
        var user_password : String = cursor.getString(2)

        var user : LoginUserModel = LoginUserModel(user_id, user_name, user_password)

        cursor.close()
        db.close()

        return user
    }

//    fun getEveryOne(): List<LoginUserModel> {
//        val returnList = mutableListOf<LoginUserModel>()
//        val queryString = "SELECT * FROM $USER_TABLE"
//
//        val db = readableDatabase
//        val cursor = db.rawQuery(queryString, null)
//
//        if (cursor.moveToFirst()) {
//            do {
//                val customerID = cursor.getInt(0)
//                val customerName = cursor.getString(1)
//                val customerAge = cursor.getString(2)
//
//                val newCustomer = LoginUserModel(customerID, customerName, customerAge)
//                returnList.add(newCustomer)
//            } while (cursor.moveToNext())
//        }
//
//        cursor.close()
//        db.close()
//
//        return returnList
//    }
}