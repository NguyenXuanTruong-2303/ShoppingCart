package com.example.assignment.Database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.assignment.Model.StaffModel


private const val STAFF_TABLE = "STAFF_TABLE"

private const val COLUMN_STAFF_ID = "STAFF_ID"

private const val COLUMN_STAFF_NAME = "STAFF_NAME"

private const val COLUMN_STAFF_ROOM = "STAFF_ROOM"

class DataBaseStaff(context : Context) : SQLiteOpenHelper(context,"staff.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableStatement : String = "CREATE TABLE $STAFF_TABLE ( $COLUMN_STAFF_ID TEXT PRIMARY KEY , $COLUMN_STAFF_NAME TEXT , $COLUMN_STAFF_ROOM TEXT) "
        db?.execSQL(createTableStatement)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun addOne(staff: StaffModel) : Boolean {
        val db: SQLiteDatabase = writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(COLUMN_STAFF_ID, staff.id)
        cv.put(COLUMN_STAFF_NAME, staff.staffName)
        cv.put(COLUMN_STAFF_ROOM, staff.staffRoom)

        val insert: Long = db.insert(STAFF_TABLE, null, cv)

        if (insert == (-1).toLong()) {
            return false
        } else {
            return true
        }
    }

    fun updateOne(staff: StaffModel): Boolean {
        val db: SQLiteDatabase = writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(COLUMN_STAFF_ID, staff.id)
        cv.put(COLUMN_STAFF_NAME, staff.staffName)
        cv.put(COLUMN_STAFF_ROOM, staff.staffRoom)

        val whereClause = "STAFF_ID = ?"
        val whereArgs = staff.id

        val update: Int = db.update(STAFF_TABLE,cv,whereClause, arrayOf(whereArgs))

        if (update == 0) {
            return false
        } else {
            return true
        }
    }

    fun deleteOne(staff: StaffModel): Boolean {
        val db: SQLiteDatabase = writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(COLUMN_STAFF_ID, staff.id)
        cv.put(COLUMN_STAFF_NAME, staff.staffName)
        cv.put(COLUMN_STAFF_ROOM, staff.staffRoom)

        val whereClause = "STAFF_ID = ?"
        val whereArgs = staff.id

        val update: Int = db.delete(STAFF_TABLE,whereClause, arrayOf(whereArgs))

        if (update == 0) {
            return false
        } else {
            return true
        }
    }

    fun getEveryOne(): List<StaffModel> {
        val returnList  = ArrayList<StaffModel>()
        val queryString : String = "SELECT * FROM $STAFF_TABLE"

        val db : SQLiteDatabase = this.readableDatabase
        var cursor : Cursor = db.rawQuery(queryString, null)

        if (cursor.moveToFirst()) {
            do {
                var staffID = cursor.getString(0)
                var staffName = cursor.getString(1)
                var staffRoom = cursor.getString(2)

                var newStaff = StaffModel(staffID, staffName, staffRoom)
                returnList.add(newStaff)
            } while (cursor.moveToNext())
        } else {
            //
        }
        cursor.close()
        db.close()
        return returnList
    }

}