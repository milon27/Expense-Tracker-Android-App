package com.m27lab.expensetracker.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.m27lab.expensetracker.data.db.dao.ExpenseDAO
import com.m27lab.expensetracker.data.db.entities.ExpenseEntity

@Database(
    entities = [ExpenseEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ShoppingDB : RoomDatabase(){
    //get dao
    abstract fun getExpenseDAO():ExpenseDAO

}