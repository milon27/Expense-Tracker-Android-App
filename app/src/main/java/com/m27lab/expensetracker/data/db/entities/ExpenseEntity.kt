package com.m27lab.expensetracker.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "expense_table")
data class ExpenseEntity(
    var title:String,
    var price:Double,
    @ColumnInfo(name = "transaction_date")
    var transactionDate:Date,
    var purchased:Boolean=false
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
    var description:String=""
}