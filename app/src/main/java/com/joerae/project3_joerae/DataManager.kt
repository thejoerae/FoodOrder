package com.joerae.project3_joerae

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASENAME = "JOES GRUB"
val TABLENAME = "Orders"
class DataManager(var context: Context) : SQLiteOpenHelper(context, DATABASENAME, null,1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLENAME + " (Id integer PRIMARY KEY autoincrement, FoodCategory text not null, FoodName text not null, FoodExtras text not null, Quanity int)"
        db?.execSQL(createTable)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //onCreate(db);
    }

    fun insertData(food: Food) {
        val database = this.writableDatabase

        val contentValues = ContentValues()
        // don't need to add the Id value because it's auto assigned when we create the food object
        contentValues.put("FoodCategory", food.category)
        contentValues.put("FoodName", food.item)
        contentValues.put("FoodExtras", food.extras)
        contentValues.put("Quanity", food.quantity)

        val result = database.insert(TABLENAME, null, contentValues)

        if (result == (0).toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    // let's read the data from the database
    fun readData(): MutableList<Food> {
        val list: MutableList<Food> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLENAME"

        val result = db.rawQuery(query, null)

        if (result.moveToFirst()) {
            do {
                val food = Food()

                food.id = result.getInt(result.getColumnIndex("Id"))
                food.category = result.getString(result.getColumnIndex("FoodCategory"))
                food.item = result.getString(result.getColumnIndex("FoodName"))
                food.extras = result.getString(result.getColumnIndex("FoodExtras"))
                food.quantity = result.getInt(result.getColumnIndex("Quanity"))

                list.add(food)
            }
            while (result.moveToNext())
        }
        return list
    }
}