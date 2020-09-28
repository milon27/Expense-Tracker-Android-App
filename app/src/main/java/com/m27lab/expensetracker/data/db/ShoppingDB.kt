package com.m27lab.expensetracker.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object{
        @Volatile
        var instance:ShoppingDB?=null

        //get instance with invoke
        private val LOCK=Any()
        operator fun invoke(context: Context)= instance?: synchronized(LOCK){
            instance?: createDB(context).also { instance=it }
        }

        private fun createDB(context:Context):ShoppingDB {
            return Room.databaseBuilder(context.applicationContext,ShoppingDB::class.java,"shopping_db").build();
        }
    }

}